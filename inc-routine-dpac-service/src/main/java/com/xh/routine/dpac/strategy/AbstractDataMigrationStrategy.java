package com.xh.routine.dpac.strategy;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.routine.dpac.annotation.Log;
import com.xh.routine.dpac.base.BaseDataMigrationStrategy;
import com.xh.routine.dpac.entity.DpacTicketInfoEntity;
import com.xh.routine.dpac.entity.DpacTicketMarkEntity;
import com.xh.routine.dpac.exception.DpacParamIsNullException;
import com.xh.routine.dpac.service.DpacTicketInfoService;
import com.xh.routine.dpac.service.DpacTicketMarkService;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Service
public abstract class AbstractDataMigrationStrategy implements BaseDataMigrationStrategy {

    private static final Logger log = LoggerFactory.getLogger(AbstractDataMigrationStrategy.class);

    @Autowired
    private DpacTicketMarkService dpacTicketMarkService;
    @Autowired
    private DpacTicketInfoService dpacTicketInfoService;

    @Log
    @Override
    public void synchronizeData() {
        dpacTicketInfoService.listTicketsStream(new DpacTicketInfoVO(), new ResultHandler<DpacTicketInfoEntity>() {
            @Override
            public void handleResult(ResultContext<? extends DpacTicketInfoEntity> resultContext) {
                DpacTicketInfoEntity resultObject = resultContext.getResultObject();
                DpacTicketMarkEntity getOne = dpacTicketMarkService.getOne(new QueryWrapper<DpacTicketMarkEntity>().eq("ticket_id", resultObject.getId()));
                if (!ObjectUtils.isEmpty(getOne)) {
                    log.info("synchronizeData ticket is exist, ticket:[{}]", resultObject);
                    return;
                }
                DpacTicketMarkEntity dpacTicketMarkEntity = new DpacTicketMarkEntity();
                dpacTicketMarkEntity.setTicketId(resultObject.getId());
                dpacTicketMarkService.save(dpacTicketMarkEntity);
            }
        });
    }

    @Override
    public abstract void checkClodData();

    @Override
    public abstract void migrateColdData();

    @Log
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void removeHotData(Long ticketId) throws DpacParamIsNullException {
        if (ObjectUtils.isEmpty(ticketId)) {
            throw new DpacParamIsNullException("删除冷数据时 工单id为null");
        }
        dpacTicketInfoService.removeById(ticketId);
        dpacTicketMarkService.remove(new QueryWrapper<DpacTicketMarkEntity>().eq("ticket_id", ticketId));
    }
}
