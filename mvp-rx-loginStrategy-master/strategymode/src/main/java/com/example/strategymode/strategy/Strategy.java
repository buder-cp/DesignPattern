package com.example.strategymode.strategy;

public interface Strategy {
    int doOperation(int num1, int num2, Strategy.StrategyCallback callback);

    interface StrategyCallback {
        void onSuccess();
        void onFail();
    }

}
