package com.xh.routine.dpac.service;

import com.xh.routine.dpac.dto.UserInfoDTO;
import com.xh.routine.dpac.exception.DpacUserInfoNotFoundException;

public interface UserInfoService {

    /**
     * 从redis中获取当前登录用户信息，用户信息在用户登录时存入redis
     * @return
     */
    UserInfoDTO getCurrUserInfo() throws DpacUserInfoNotFoundException;
}
