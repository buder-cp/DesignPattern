package com.example.strategymode.strategy;

public class OperationSubstract implements Strategy {

    @Override
    public int doOperation(int num1, int num2, StrategyCallback callback) {
        if (num1 - num2 < 0) {
            callback.onFail();
            return -1;
        } else {
            callback.onSuccess();
            return num1 - num2;
        }
    }
}
