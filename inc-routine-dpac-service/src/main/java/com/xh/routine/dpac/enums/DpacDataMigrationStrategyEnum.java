package com.xh.routine.dpac.enums;

import com.xh.routine.dpac.base.BaseDataMigrationStrategy;
import com.xh.routine.dpac.strategy.DpacDataMigrationMultiThreadStrategy;
import com.xh.routine.dpac.strategy.DpacDataMigrationSingleTheadStrategy;
import org.springframework.util.StringUtils;

public enum DpacDataMigrationStrategyEnum {
    SINGLE_THREAD("single", DpacDataMigrationSingleTheadStrategy.class),
    MULTI_THREAD("multi", DpacDataMigrationMultiThreadStrategy.class)
    ;

    private String name;
    private Class<? extends BaseDataMigrationStrategy> strategyClass;

    DpacDataMigrationStrategyEnum(String name, Class<? extends BaseDataMigrationStrategy> strategyClass) {
        this.name = name;
        this.strategyClass = strategyClass;
    }

    public static Class<? extends BaseDataMigrationStrategy> getStrategyByType(String type) {
        if (!StringUtils.hasText(type)) {
            return null;
        }
        for (DpacDataMigrationStrategyEnum strategy : DpacDataMigrationStrategyEnum.values()) {
            if (strategy.getName().equals(type)) {
                return strategy.getStrategyClass();
            }
        }
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class<? extends BaseDataMigrationStrategy> getStrategyClass() {
        return strategyClass;
    }

    public void setStrategyClass(Class<? extends BaseDataMigrationStrategy> strategyClass) {
        this.strategyClass = strategyClass;
    }
}
