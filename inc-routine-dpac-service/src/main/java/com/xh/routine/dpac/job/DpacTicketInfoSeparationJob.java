package com.xh.routine.dpac.job;

import com.xh.routine.dpac.enums.DpacDataMigrationStrategyEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 工单数据冷热分离
 */
@Component
public class DpacTicketInfoSeparationJob {

    private static final Logger log = LoggerFactory.getLogger(DpacTicketInfoSeparationJob.class);
    private String dataMigrationType;


    @Autowired
    private ApplicationContext applicationContext;


    public void execute(String dataMigrationType) {
        dataMigrationType = dataMigrationType;
        applicationContext.getBean(DpacDataMigrationStrategyEnum.getStrategyByType(dataMigrationType)).execProcess();
    }
}
