package com.mobile.config.sqlite

import org.sqlite.SQLiteJDBCLoader
import org.sqlite.jdbc4.JDBC4Connection
import java.io.File
import java.io.IOException
import java.nio.file.FileSystems
import java.nio.file.Files
import java.nio.file.Path
import java.sql.SQLException
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.collections.HashMap

class Stub2SqliteGenerator {

    fun generateSqlite(stubFolder: String, sqliteFile: String) {
        try {
            SQLiteJDBCLoader.initialize()
            val root =
                FileSystems.getDefault().getPath(stubFolder)
            //ensure all folder structure is created
            File(sqliteFile).parentFile.mkdirs()

            val files: List<Path> = Files
                .walk(root)
                .filter { path ->
                    !Files.isDirectory(path) && !isHidden(path)
                }
                .collect(Collectors.toList())

            System.out.printf(
                "Files in '%s' to save: %s'\n",
                root.toFile().absolutePath,
                files.size
            )
            val filePerContentId: HashMap<Path, Int> =
                HashMap()
            val idToContent: TreeMap<Int, String> =
                TreeMap()
            fillData(files, filePerContentId, idToContent)

            // create a database connection
            // it must be exact object specification
            // usage of DriverManager.getConnection("..."); was failing in AS
            val sqlConnection =
                JDBC4Connection("", sqliteFile, Properties())
            val statement = sqlConnection.createStatement()
            statement.executeUpdate("drop table if exists stub")
            statement.executeUpdate("drop table if exists content")
            fillContentTable(sqlConnection, idToContent)
            fillStubTable(sqlConnection, root, filePerContentId)
            System.out.printf(
                "Completed %s\nFiles: %s\tUnique content values: %s\n",
                sqliteFile,
                filePerContentId.size,
                idToContent.size
            )
            sqlConnection.close()
        } catch (e: Throwable) {
            System.err.println(e.message)
            throw Error(e)
        }
    }

    private fun fillData(
        files: List<Path>,
        outFilePerContentId: HashMap<Path, Int>,
        outIdToContent: TreeMap<Int, String>
    ) {
        val contentPerFiles: MutableMap<String, MutableSet<Path>> =
            HashMap()
        files.forEach(Consumer { path: Path ->
            val content: String = readText(path) as String
            if (!contentPerFiles.containsKey(content)) {
                contentPerFiles[content] = mutableSetOf()
            }
            contentPerFiles[content]!!.add(path)
        })
        val index = intArrayOf(1)
        contentPerFiles.forEach { (content: String, paths: Set<Path>) ->
            outIdToContent[index[0]] = content
            paths.forEach(Consumer { path: Path ->
                outFilePerContentId[path] = index[0]
            })
            index[0]++
        }
    }

    @Throws(SQLException::class)
    private fun fillStubTable(
        sqlConnection: JDBC4Connection,
        root: Path,
        filePerContentId: Map<Path, Int>
    ) {
        sqlConnection
            .createStatement()
            .executeUpdate(
                "CREATE TABLE `stub` (" +
                        "`url` TEXT PRIMARY KEY, " +
                        "`content_id` INTEGER NOT NULL, " +
                        "FOREIGN KEY(content_id) REFERENCES content(id) " +
                        ");"
            )
        filePerContentId.forEach { (filePath: Path, id: Int?) ->
            try {
                val ps =
                    sqlConnection.prepareStatement("INSERT INTO `stub` (url, content_id) VALUES (?, ?)")
                val relativePath =
                    filePath.toString().replace(root.toString(), "").substring(1)
                //let's be sure we have everything lowercase, file system didn't care, sqlite does
                ps.setString(1, lowerCasePath(relativePath))
                ps.setInt(2, id)
                ps.executeUpdate()
            } catch (e: SQLException) {
                throw java.lang.IllegalStateException(e)
            }
        }
    }

    @Throws(SQLException::class)
    private fun fillContentTable(
        sqlConnection: JDBC4Connection,
        idToContent: Map<Int, String>
    ) {
        sqlConnection
            .createStatement()
            .executeUpdate(
                "CREATE TABLE `content` (" +
                        "`id` INTEGER PRIMARY KEY, " +
                        "`value` TEXT NOT NULL" +
                        ");"
            )
        idToContent.forEach { (id: Int?, content: String?) ->
            try {
                val ps = sqlConnection
                    .prepareStatement("INSERT INTO `content` (id, value) VALUES (?, ?)")
                ps.setInt(1, id)
                ps.setString(2, content)
                ps.executeUpdate()
            } catch (e: SQLException) {
                throw java.lang.IllegalStateException(e)
            }
        }
    }

    private fun readText(file: Path): String? {
        return try {
            String(Files.readAllBytes(file), charset("utf-8"))
        } catch (e: IOException) {
            throw java.lang.IllegalStateException(e)
        }
    }

    //avoid loading anything what is system/hidden like .DS_Store file
    //it is actually breaking something in StubResponseFetcher
    private fun isHidden(path: Path): Boolean {
        return try {
            Files.isHidden(path)
        } catch (e: IOException) {
            throw IllegalStateException(e)
        }
    }

    private val FOLDER_SEP = "/"

    private fun lowerCasePath(value: String?): String? {
        if (value == null) {
            return null
        }
        val sb = StringBuilder()
        val parts =
            value.split(FOLDER_SEP).toTypedArray()
        for (part in parts) {
            if (part.contains("=")) {
                //this is part of queryString agrs
                sb.append(part)
            } else {
                sb.append(part.toLowerCase(Locale.UK))
            }
            sb.append(FOLDER_SEP)
        }
        // split of '/a/b/' is ["", "a", "b"]
        // so if we have '/' at the end just keep it there
        if (sb.isNotEmpty() && !value.endsWith(FOLDER_SEP)) {
            sb.setLength(sb.length - 1)
        }
        return sb.toString()
    }
}