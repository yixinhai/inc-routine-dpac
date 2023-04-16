package com.xh.routine.dpac.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     * @return
     */
    String title() default "";

    /**
     * 是否保存请求的参数
     * @return
     */
    boolean isSaveRequestData() default true;
}
