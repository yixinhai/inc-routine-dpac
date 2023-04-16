package com.xh.routine.dpac.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xh.routine.dpac.entity.DpacTicketInfoEntity;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

/**
* @Entity com.xh.routine.dpac.domain.DpacTicketInfo
*/
public interface DpacTicketInfoMapper extends BaseMapper<DpacTicketInfoEntity> {

    void selectAllStream(@Param("record") DpacTicketInfoVO record, ResultHandler<DpacTicketInfoEntity> resultHandler);

    void selectAllColdStream(@Param("record") DpacTicketInfoVO record, ResultHandler<DpacTicketInfoEntity> resultHandler);
}
