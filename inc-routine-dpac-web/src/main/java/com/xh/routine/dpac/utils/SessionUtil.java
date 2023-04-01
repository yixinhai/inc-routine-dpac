package com.xh.routine.dpac.utils;

import com.xh.routine.dpac.dto.DpacUserInfoDTO;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    /**
     * 从session中获取用户信息
     * @param request
     * @return
     */
    public static DpacUserInfoDTO getUserInfo(HttpServletRequest request) {
        Object userId = request.getSession().getAttribute("userId");
        Object username = request.getSession().getAttribute("username");
        if (ObjectUtils.isEmpty(userId) || ObjectUtils.isEmpty(username)) {
            return null;
        }
        DpacUserInfoDTO dpacUserInfoDTO = new DpacUserInfoDTO();
        dpacUserInfoDTO.setId((Long) userId);
        dpacUserInfoDTO.setUsername((String) username);
        return dpacUserInfoDTO;
    }
}
