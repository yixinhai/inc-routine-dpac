package com.xh.routine.dpac.service.impl;

import com.xh.routine.dpac.dto.DpacUserInfoDTO;
import com.xh.routine.dpac.exception.DpacUserInfoNotFoundException;
import com.xh.routine.dpac.service.DpacUserInfoService;
import com.xh.routine.dpac.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class DpacUserInfoServiceImpl implements DpacUserInfoService {

    private RedisUtil redisUtil;


    public DpacUserInfoDTO getCurrUserInfo() throws DpacUserInfoNotFoundException {
        // TODO 从redis中获取userInfo
        DpacUserInfoDTO dpacUserInfoDTO = new DpacUserInfoDTO();
        if (ObjectUtils.isEmpty(dpacUserInfoDTO) || !StringUtils.hasText(dpacUserInfoDTO.getUsername())) {
            throw new DpacUserInfoNotFoundException("redis中未找到用户登录信息");
        }
        dpacUserInfoDTO.setId(1L);
        dpacUserInfoDTO.setUsername("system");
        dpacUserInfoDTO.setGroupId(1L);
        return dpacUserInfoDTO;
    }
}
