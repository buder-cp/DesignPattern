package com.example.strategymode.strategy;

public class computeManager {

    private void doDiffComputeStrategy() {
        /**
         * 方式一 begin：在这里选择使用的策略，方便不同策略回调不同结果
         */
        StrategyWrapper wrapper = new StrategyWrapper(new OperationAdd());
        wrapper.executeStrategy(1, 2, new Strategy.StrategyCallback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onFail() {

            }
        });

    }
}
