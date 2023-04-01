package com.xh.routine.dpac.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xh.routine.dpac.base.BaseResult;
import com.xh.routine.dpac.entity.DpacTicketInfoEntity;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;


/**
*
*/
public interface DpacTicketInfoService extends IService<DpacTicketInfoEntity> {

    /**
     * 分页查询工单列表
     * @param dpacTicketInfoVO
     * @return
     */
    BaseResult listTickets(DpacTicketInfoVO dpacTicketInfoVO);

    /**
     * 处理订单
     * @param dpacTicketInfoVO
     * @return
     */
    BaseResult assignTicket(DpacTicketInfoVO dpacTicketInfoVO) throws Exception;

    /**
     * 更新工单状态
     * @param dpacTicketInfoVO
     * @return
     * @throws Exception
     */
    BaseResult updateTicketStatus(DpacTicketInfoVO dpacTicketInfoVO) throws Exception;

    /**
     * 根据工单id查询工单信息
     * @param id
     * @return
     */
    DpacTicketInfoEntity getOneById(Long id);
}
