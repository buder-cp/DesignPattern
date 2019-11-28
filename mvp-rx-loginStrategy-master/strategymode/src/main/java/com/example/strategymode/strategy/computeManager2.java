package com.example.strategymode.strategy;

public class computeManager2 {

    /**
     * 方式二 begin：不需要在这里选择使用的策略，方便不同策略回调后走相同的处理流程
     */
    private void doDiffComputeStrategy() {
        Strategy strategy = new StrategyWrapper();
        strategy.doOperation(1, 2, new Strategy.StrategyCallback() {
            @Override
            public void onSuccess() {
                System.out.println("manager 2 success");
            }

            @Override
            public void onFail() {
                System.out.println("manager 2 failed");
            }
        });
    }
}
