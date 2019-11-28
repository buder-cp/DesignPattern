package com.example.strategymode.strategy;

public class OperationMultiply implements Strategy {

    @Override
    public int doOperation(int num1, int num2, StrategyCallback callback) {
        if (num1 * num2 > 10) {
            callback.onFail();
            return 100;
        } else {
            callback.onSuccess();
            return num1 * num2;
        }
    }
}
