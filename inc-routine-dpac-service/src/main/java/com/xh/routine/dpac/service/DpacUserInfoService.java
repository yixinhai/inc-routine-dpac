package com.xh.routine.dpac.service;

import com.xh.routine.dpac.dto.DpacUserInfoDTO;
import com.xh.routine.dpac.exception.DpacUserInfoNotFoundException;

public interface DpacUserInfoService {

    /**
     * 从redis中获取当前登录用户信息，用户信息在用户登录时存入redis
     * @return
     */
    DpacUserInfoDTO getCurrUserInfo() throws DpacUserInfoNotFoundException;
}
