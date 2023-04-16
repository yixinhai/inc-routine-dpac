package com.xh.routine.dpac.controller;

import com.xh.routine.dpac.base.BaseResult;
import com.xh.routine.dpac.job.DpacTicketInfoSeparationJob;
import com.xh.routine.dpac.service.DpacTicketInfoService;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class DpacTicketController {

    @Autowired
    private DpacTicketInfoService dpacTicketInfoService;
    @Autowired
    private DpacTicketInfoSeparationJob dpacTicketInfoSeparationJob;


    /**
     * 分页查询工单信息
     * @param dpacTicketInfoVO
     * @return
     */
    @PostMapping("/pageList")
    public BaseResult ListTickets(@RequestBody DpacTicketInfoVO dpacTicketInfoVO) {
        return dpacTicketInfoService.listTickets(dpacTicketInfoVO);
    }

    /**
     * 处理工单，更改工单状态
     * @param dpacTicketInfoVO
     * @return
     * @throws Exception
     */
    @PostMapping("assign-ticket")
    public BaseResult assignTicket(@RequestBody DpacTicketInfoVO dpacTicketInfoVO) throws Exception {
        return dpacTicketInfoService.assignTicket(dpacTicketInfoVO);
    }

    /**
     * 迁移冷数据
     * @return
     */
    @GetMapping("cold-data-migration")
    public BaseResult coldDataMigration() {
        String dataMigrationType = "single";
        dpacTicketInfoSeparationJob.execute(dataMigrationType);
        return BaseResult.success();
    }
}
