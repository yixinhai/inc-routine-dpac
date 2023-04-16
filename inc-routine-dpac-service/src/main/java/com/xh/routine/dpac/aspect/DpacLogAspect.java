package com.xh.routine.dpac.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DpacLogAspect {

    private static final Logger log = LoggerFactory.getLogger(DpacLogAspect.class);

    @Pointcut(value = "@annotation(com.xh.routine.dpac.annotation.Log)")
    public void logPointCut() {

    }


    @Before(value = "logPointCut()")
    public void before(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getName();
        String targetMethodName = joinPoint.getSignature().getName();
        // 获取参数名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameters = joinPoint.getArgs();
        log.info(targetClassName + "." + targetMethodName + " start. args:[{}]", formatParameters(parameterNames, parameters));
    }

    @AfterReturning(value = "logPointCut()")
    public void after(JoinPoint joinPoint) {
        String targetClassName = joinPoint.getTarget().getClass().getName();
        String targetMethodName = joinPoint.getSignature().getName();
        log.info(targetClassName + "." + targetMethodName + " end.");
    }

    private String formatParameters(String[] parameterNames, Object[] parameters) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < parameterNames.length; i++) {
            builder.append(parameterNames[i]).append(":").append(parameters[i]).append(",");
        }
        if (builder.length() > 0) {
            builder.deleteCharAt(builder.length() - 1);
        }
        return builder.toString();
    }
}
