package com.xh.routine.dpac.service;

import com.xh.routine.dpac.entity.DpacTicketMarkEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.session.ResultHandler;

/**
 *
 */
public interface DpacTicketMarkService extends IService<DpacTicketMarkEntity> {

    void listTicketsStream(DpacTicketMarkEntity dpacTicketMarkEntity, ResultHandler<DpacTicketMarkEntity> resultHandler);
}
