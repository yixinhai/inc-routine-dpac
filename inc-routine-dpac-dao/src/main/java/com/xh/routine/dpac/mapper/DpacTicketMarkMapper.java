package com.xh.routine.dpac.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xh.routine.dpac.entity.DpacTicketMarkEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

/**
 * @Entity com.xh.routine.dpac.entity.DpacTicketMarkEntity
 */
public interface DpacTicketMarkMapper extends BaseMapper<DpacTicketMarkEntity> {

    void selectAllSingleStream(@Param("record") DpacTicketMarkEntity record, ResultHandler<DpacTicketMarkEntity> resultHandler);

}




