package com.mobile.config.sqlite;

import org.sqlite.SQLiteJDBCLoader;
import org.sqlite.jdbc4.JDBC4Connection;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.annotation.Nullable;

//used in gradle script
@SuppressWarnings("unused")
public class Stub2SqliteGenerator {

    public static void generateSqlite(String stubFolder, String sqliteFile) {
        try {
            SQLiteJDBCLoader.initialize();

            final Path root = FileSystems.getDefault().getPath(stubFolder);
            //ensure all folder structure is created
            new File(sqliteFile).getParentFile().mkdirs();

            final List<Path> files = Files
                    .walk(root)
                    .filter(path -> !Files.isDirectory(path) && !isHidden(path))
                    .collect(Collectors.toList());

            System.out.printf("Files in '%s' to save: %s'\n",
                    root.toFile().getAbsolutePath(),
                    files.size());

            final Map<Path, Integer> filePerContentId = new HashMap<>();
            final Map<Integer, String> idToContent = new TreeMap<>();
            fillData(files, filePerContentId, idToContent);

            // create a database connection
            // it must be exact object specification
            // usage of DriverManager.getConnection("..."); was failing in AS
            JDBC4Connection sqlConnection = new JDBC4Connection("", sqliteFile, new Properties());
            Statement statement = sqlConnection.createStatement();

            statement.executeUpdate("drop table if exists stub");
            statement.executeUpdate("drop table if exists content");

            fillContentTable(sqlConnection, idToContent);
            fillStubTable(sqlConnection, root, filePerContentId);

            System.out.printf("Completed %s\nFiles: %s\tUnique content values: %s\n",
                    sqliteFile,
                    filePerContentId.size(),
                    idToContent.size()
            );

            sqlConnection.close();

        } catch (Throwable e) {
            System.err.println(e.getMessage());
            throw new Error(e);
        }
    }

    private static void fillData(List<Path> files,
                                 Map<Path, Integer> outFilePerContentId,
                                 Map<Integer, String> outIdToContent) {

        Map<String, Set<Path>> contentPerFiles = new HashMap<>();

        files.forEach(path -> {
            String content = readText(path);
            if (!contentPerFiles.containsKey(content)) {
                contentPerFiles.put(content, new HashSet<>());
            }

            contentPerFiles.get(content).add(path);
        });

        int[] index = {1};
        contentPerFiles.forEach((content, paths) -> {
            outIdToContent.put(index[0], content);
            paths.forEach(path -> outFilePerContentId.put(path, index[0]));
            index[0]++;
        });
    }

    private static void fillStubTable(JDBC4Connection sqlConnection,
                                      Path root,
                                      Map<Path, Integer> filePerContentId) throws SQLException {
        sqlConnection
                .createStatement()
                .executeUpdate(
                        "CREATE TABLE `stub` (" +
                                "`url` TEXT PRIMARY KEY, " +
                                "`content_id` INTEGER NOT NULL, " +
                                "FOREIGN KEY(content_id) REFERENCES content(id) " +
                                ");");

        filePerContentId.forEach((filePath, id) -> {
            try {
                PreparedStatement ps = sqlConnection.prepareStatement("INSERT INTO `stub` (url, content_id) VALUES (?, ?)");
                String relativePath = filePath.toString().replace(root.toString(), "").substring(1);
                //let's be sure we have everything lowercase, file system didn't care, sqlite does
                ps.setString(1, lowerCasePath(relativePath));
                ps.setInt(2, id);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }

        });
    }

    private static void fillContentTable(JDBC4Connection sqlConnection,
                                         Map<Integer, String> idToContent) throws SQLException {
        sqlConnection
                .createStatement()
                .executeUpdate(
                        "CREATE TABLE `content` (" +
                                "`id` INTEGER PRIMARY KEY, " +
                                "`value` TEXT NOT NULL" +
                                ");");

        idToContent.forEach((id, content) -> {
            try {
                PreparedStatement ps = sqlConnection
                        .prepareStatement("INSERT INTO `content` (id, value) VALUES (?, ?)");
                ps.setInt(1, id);
                ps.setString(2, content);
                ps.executeUpdate();
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private static String readText(Path file) {
        try {
            return new String(Files.readAllBytes(file), "utf-8");
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    //avoid loading anything what is system/hidden like .DS_Store file
    //it is actually breaking something in StubResponseFetcher
    private static boolean isHidden(Path path) {
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static final String FOLDER_SEP = "/";

    @Nullable
    private static String lowerCasePath(@Nullable String value) {
        if (value == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        String[] parts = value.split(FOLDER_SEP);
        for (String part : parts) {
            if (part.contains("=")) {
                //this is part of queryString agrs
                sb.append(part);
            } else {
                sb.append(part.toLowerCase(Locale.UK));
            }
            sb.append(FOLDER_SEP);
        }
        // split of '/a/b/' is ["", "a", "b"]
        // so if we have '/' at the end just keep it there
        if (sb.length() > 0 && !value.endsWith(FOLDER_SEP)) {
            sb.setLength(sb.length() - 1);
        }
        return sb.toString();
    }
}
