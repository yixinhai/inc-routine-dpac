package com.xh.routine.dpac.utils;

import org.springframework.beans.BeanUtils;

public class BeanUtil {

    /**
     * bean的深拷贝
     * @param source
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source, Class<T> target) {
        T t = null;
        try {
            t = target.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }
}
