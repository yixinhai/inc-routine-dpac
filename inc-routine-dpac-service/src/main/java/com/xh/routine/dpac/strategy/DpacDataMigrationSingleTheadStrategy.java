package com.xh.routine.dpac.strategy;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.xh.routine.dpac.annotation.Log;
import com.xh.routine.dpac.entity.DpacTicketInfoEntity;
import com.xh.routine.dpac.entity.DpacTicketMarkEntity;
import com.xh.routine.dpac.enums.DpacTicketStatusEnum;
import com.xh.routine.dpac.mapper.DpacTicketMarkMapper;
import com.xh.routine.dpac.service.DpacTicketInfoService;
import com.xh.routine.dpac.service.DpacTicketMarkService;
import com.xh.routine.dpac.utils.DateUtil;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;
import lombok.SneakyThrows;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * 单线程数据迁移
 */
@Component(value = "dataMigrationSingleTheadStrategy")
public class DpacDataMigrationSingleTheadStrategy extends AbstractDataMigrationStrategy {

    private static final Logger log = LoggerFactory.getLogger(DpacDataMigrationSingleTheadStrategy.class);
    private static String STRATEGY = "single_thread_strategy ";

    @Autowired
    private DpacTicketInfoService dpacTicketInfoService;
    @Autowired
    private DpacTicketMarkService dpacTicketMarkService;
    @Autowired
    private DpacTicketMarkMapper dpacTicketMarkMapper;
    @Autowired
    private DpacDataMigrationSingleTheadStrategy dataMigrationSingleTheadStrategy;


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void execProcess() {
        dataMigrationSingleTheadStrategy.synchronizeData();
        dataMigrationSingleTheadStrategy.checkClodData();
        dataMigrationSingleTheadStrategy.migrateColdData();
    }

    @Log
    @Override
    public void checkClodData() {
        DpacTicketInfoVO dpacTicketInfoVO = new DpacTicketInfoVO();
        dpacTicketInfoVO.setStatus(DpacTicketStatusEnum.FILE.getCode());
        dpacTicketInfoVO.setProcessTime(DateUtil.toDate(DateUtil.minus(LocalDateTime.now(), 1, ChronoUnit.MONTHS), null));
        // 流式查询
        dpacTicketInfoService.listTicketsStream(dpacTicketInfoVO, new ResultHandler<DpacTicketInfoEntity>() {
            @SneakyThrows
            @Override
            public void handleResult(ResultContext<? extends DpacTicketInfoEntity> resultContext) {
                DpacTicketInfoEntity resultObject = resultContext.getResultObject();
                log.info(STRATEGY + "checkClodData clod data:[{}]", resultObject);
                // 更新到标志表
                UpdateWrapper<DpacTicketMarkEntity> updateWrapper = new UpdateWrapper<>();
                updateWrapper.eq("ticket_id", resultObject.getId());
                updateWrapper.set("cold_flag", "1");
                dpacTicketMarkMapper.update(null, updateWrapper);
            }
        });
    }

    @Log
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void migrateColdData() {
        DpacTicketMarkEntity dpacTicketMarkEntity = new DpacTicketMarkEntity();
        dpacTicketMarkService.listTicketsStream(dpacTicketMarkEntity, new ResultHandler<DpacTicketMarkEntity>() {
            @SneakyThrows
            @Override
            public void handleResult(ResultContext<? extends DpacTicketMarkEntity> resultContext) {
                DpacTicketMarkEntity resultObject = resultContext.getResultObject();
                log.info(STRATEGY + "migrateColdData clod data:[{}]", resultObject);
                DpacTicketInfoEntity dpacTicketInfoEntity = dpacTicketInfoService.getOneById(resultObject.getTicketId());
                // TODO 插入到冷库中
                log.info(STRATEGY + "TODO 插入到冷库中");
                // 从热库中删除冷数据
                dataMigrationSingleTheadStrategy.removeHotData(dpacTicketInfoEntity.getId());
            }
        });
    }
}
