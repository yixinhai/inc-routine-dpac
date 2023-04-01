package com.xh.routine.dpac.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xh.routine.dpac.base.BaseResult;
import com.xh.routine.dpac.dto.UserInfoDTO;
import com.xh.routine.dpac.entity.DpacTicketInfoEntity;
import com.xh.routine.dpac.enums.DpacTicketStatusEnum;
import com.xh.routine.dpac.service.DpacTicketInfoService;
import com.xh.routine.dpac.mapper.DpacTicketInfoMapper;
import com.xh.routine.dpac.service.UserInfoService;
import com.xh.routine.dpac.utils.BeanUtil;
import com.xh.routine.dpac.vo.DpacTicketInfoVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;


/**
* 工单信息服务类
*/
@Service
public class DpacTicketInfoServiceImpl extends ServiceImpl<DpacTicketInfoMapper, DpacTicketInfoEntity> implements DpacTicketInfoService{

    private static final Logger log = LoggerFactory.getLogger(DpacTicketInfoServiceImpl.class);

    @Autowired
    private DpacTicketInfoMapper dpacTicketInfoMapper;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private DpacTicketInfoService dpacTicketInfoService;

    @Override
    public BaseResult<IPage<DpacTicketInfoEntity>> listTickets(DpacTicketInfoVO dpacTicketInfoVO) {
        if (ObjectUtils.isEmpty(dpacTicketInfoVO)) {
            log.info("输入参数为null");
        }
        int pageNum = ObjectUtils.isEmpty(dpacTicketInfoVO.getPageNum()) ? 1 : dpacTicketInfoVO.getPageNum();
        int pageSize = ObjectUtils.isEmpty(dpacTicketInfoVO.getPageSize()) ? 20 : dpacTicketInfoVO.getPageSize();
        QueryWrapper<DpacTicketInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.hasText(dpacTicketInfoVO.getStatus()), "status", dpacTicketInfoVO.getStatus());
        queryWrapper.eq(StringUtils.hasText(dpacTicketInfoVO.getConsumerEmail()), "consumer_email", dpacTicketInfoVO.getConsumerEmail());
        queryWrapper.eq(!ObjectUtils.isEmpty(dpacTicketInfoVO.getAssignedUserId()), "assigned_user_id", dpacTicketInfoVO.getAssignedUserId());
        queryWrapper.orderByDesc("create_time");
        Page<DpacTicketInfoEntity> ticketEntityList = dpacTicketInfoMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return BaseResult.success().data(ticketEntityList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult assignTicket(DpacTicketInfoVO dpacTicketInfoVO) throws Exception {
        UserInfoDTO currUserInfo = userInfoService.getCurrUserInfo();
        if (ObjectUtils.isEmpty(dpacTicketInfoVO)) {
            log.info("工单处理失败，未传入待处理工单信息，userInfo:[{}]", currUserInfo);
            return BaseResult.defaultFail("工单处理失败，未传入工单信息");
        }
        DpacTicketInfoEntity ticketInfoEntity = getOneById(dpacTicketInfoVO.getId());
        if (ObjectUtils.isEmpty(ticketInfoEntity)) {
            log.info("工单处理失败，未找到当前工单信息，ticket:[{}], userInfo:[{}]", dpacTicketInfoVO, currUserInfo);
            return BaseResult.defaultFail("工单处理失败，当前工单不存在");
        }
        // TODO 执行处理逻辑
        // 更新工单信息
        dpacTicketInfoVO.setStatus(DpacTicketStatusEnum.FILE.getCode());
        return dpacTicketInfoService.updateTicketStatus(dpacTicketInfoVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseResult updateTicketStatus(DpacTicketInfoVO dpacTicketInfoVO) throws Exception {
        if (ObjectUtils.isEmpty(dpacTicketInfoVO)) {
            log.info("更新工单状态失败，未传入工单信息");
            return BaseResult.defaultFail("更新工单状态失败");
        }
        UserInfoDTO currUserInfo = userInfoService.getCurrUserInfo();
        DpacTicketInfoEntity ticketInfoEntity = getOneById(dpacTicketInfoVO.getId());
        if (ObjectUtils.isEmpty(ticketInfoEntity)) {
            log.info("更新工单失败，未根据id找到当前工单信息，dpacTicketInfoVO:[{}], userInfo:[{}]", dpacTicketInfoVO, currUserInfo);
            return BaseResult.defaultFail("工单更新失败，请刷新后重试");
        }
        ticketInfoEntity = BeanUtil.copyProperties(dpacTicketInfoVO, DpacTicketInfoEntity.class);
        ticketInfoEntity.setAssignedUserId(currUserInfo.getId());
        ticketInfoEntity.setAssignedUserGroupId(currUserInfo.getGroupId());
        ticketInfoEntity.setProcessTime(new Date());
        if (dpacTicketInfoMapper.updateById(ticketInfoEntity) < 1) {
            log.info("更新工单状态时数据库更新失败，dpacTicketInfoVO:[{}], userInfo:[{}]", dpacTicketInfoVO, userInfoService);
            return BaseResult.defaultFail("工单更新失败，请联系管理员");
        }
        return BaseResult.success();
    }

    @Override
    public DpacTicketInfoEntity getOneById(Long id) {
        QueryWrapper<DpacTicketInfoEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(!ObjectUtils.isEmpty(id), "id", id);
        List<DpacTicketInfoEntity> ticketInfoEntityList = dpacTicketInfoMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(ticketInfoEntityList)) {
            return null;
        }
        return ticketInfoEntityList.get(0);
    }
}
