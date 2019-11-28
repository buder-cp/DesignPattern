package com.example.strategymode.strategy;

public class StrategyWrapper implements Strategy {

    private Strategy strategy;

    /**
     * 方式一调用：从外面传入行为策略
     *
     * @param strategy
     */
    public StrategyWrapper(Strategy strategy) {
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2, Strategy.StrategyCallback callback) {
        return strategy.doOperation(num1, num2, callback);
    }
    /**
     * 方式一调用：从外面传入行为策略
     */


    /**
     * 方式二调用：直接在里面判断策略选择
     */
    private int chooseWitch = 1;
    // getInstance 获取系统需要的取值策略

    public StrategyWrapper() {
        if (chooseWitch == 1) {
            strategy = new OperationAdd();
        } else if (chooseWitch == 2) {
            strategy = new OperationSubstract();
        } else {
            strategy = new OperationMultiply();
        }
    }

    @Override
    public int doOperation(int num1, int num2, StrategyCallback callback) {
        return strategy.doOperation(num1, num2, callback);
    }
    /**
     * 方式二调用 end
     */
}
