package com.xh.routine.dpac.strategy;

import com.xh.routine.dpac.mapper.DpacTicketMarkMapper;
import com.xh.routine.dpac.service.DpacTicketInfoService;
import com.xh.routine.dpac.service.DpacTicketMarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 多线程数据迁移
 */
@Component(value = "dataMigrationMultiThreadStrategy")
public class DpacDataMigrationMultiThreadStrategy extends AbstractDataMigrationStrategy {

    private static final Logger log = LoggerFactory.getLogger(DpacDataMigrationMultiThreadStrategy.class);

    @Autowired
    private DpacTicketInfoService dpacTicketInfoService;
    @Autowired
    private DpacTicketMarkService dpacTicketMarkService;
    @Autowired
    private DpacTicketMarkMapper dpacTicketMarkMapper;

    @Override
    public void execProcess() {

    }

    @Override
    public void synchronizeData() {

    }

    @Override
    public void checkClodData() {

    }

    @Override
    public void migrateColdData() {

    }

    @Override
    public void removeHotData(Long ticketId) {

    }
}
