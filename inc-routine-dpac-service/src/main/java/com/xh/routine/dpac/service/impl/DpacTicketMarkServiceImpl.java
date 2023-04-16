package com.xh.routine.dpac.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.routine.dpac.entity.DpacTicketMarkEntity;
import com.xh.routine.dpac.mapper.DpacTicketMarkMapper;
import com.xh.routine.dpac.service.DpacTicketMarkService;
import org.apache.ibatis.session.ResultHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class DpacTicketMarkServiceImpl extends ServiceImpl<DpacTicketMarkMapper, DpacTicketMarkEntity> implements DpacTicketMarkService {

    @Autowired
    private DpacTicketMarkMapper dpacTicketMarkMapper;

    @Override
    public void listTicketsStream(DpacTicketMarkEntity dpacTicketMarkEntity, ResultHandler<DpacTicketMarkEntity> resultHandler) {
        dpacTicketMarkMapper.selectAllSingleStream(dpacTicketMarkEntity, resultHandler);
    }
}




