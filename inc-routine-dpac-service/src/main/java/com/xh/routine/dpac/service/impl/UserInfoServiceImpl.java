package com.xh.routine.dpac.service.impl;

import com.xh.routine.dpac.dto.UserInfoDTO;
import com.xh.routine.dpac.exception.DpacUserInfoNotFoundException;
import com.xh.routine.dpac.service.UserInfoService;
import com.xh.routine.dpac.utils.RedisUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private RedisUtil redisUtil;


    public UserInfoDTO getCurrUserInfo() throws DpacUserInfoNotFoundException {
        // TODO 从redis中获取userInfo
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        if (ObjectUtils.isEmpty(userInfoDTO) || !StringUtils.hasText(userInfoDTO.getUsername())) {
            throw new DpacUserInfoNotFoundException("redis中未找到用户登录信息");
        }
        userInfoDTO.setId(1L);
        userInfoDTO.setUsername("system");
        userInfoDTO.setGroupId(1L);
        return userInfoDTO;
    }
}
