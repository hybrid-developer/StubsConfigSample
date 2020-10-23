![OrderPay Android Build Pipeline](https://github.com/Checkfer-Limited/OrderPay-Android/workflows/OrderPay%20Android%20Build%20Pipeline/badge.svg)

# OrderPay-Android

1. Pull in the OrderPay-Stubs submodule:

```
git submodule init
git submodule update
```

After you download the `OrderPay-Stubs`, copy `OrderPay-Android/.git/hooks/commit-msg`
to  `OrderPay-Android/.git/modules/OrderPay-Stubs/hooks/commit-msg`.
You need this so if you do any changes in the `OrderPay-Stubs`, it will create a change id when you commit the changes.
