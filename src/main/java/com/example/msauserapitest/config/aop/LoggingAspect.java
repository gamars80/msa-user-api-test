package com.example.msauserapitest.config.aop;

import com.example.msauserapitest.utils.AppLogger;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final AppLogger logger;

//    @Pointcut("within(com.example.msauserapitest.user.service.*)" + " || within(com.example.msauserapitest.user.controller.*)")
//    public void applicationPackagePointcut() {
//    }

    @Pointcut("within(com.example.msauserapitest.user.service.*)")
    public void applicationPackagePointcut() {
    }

    @Before("applicationPackagePointcut()")
    public void logMethodBefore(JoinPoint joinPoint) {
        logger.log(getParameters(joinPoint).toString(), joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "applicationPackagePointcut()", returning = "returnValue")
    public void logMethodAfter(JoinPoint joinPoint, Object returnValue) {
        logger.log(returnValue.toString(), joinPoint.getSignature().toShortString());
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        logger.log(e.toString(), joinPoint.getSignature().toShortString());
    }

    private Map<String, Object> getParameters(JoinPoint joinPoint) {
        CodeSignature signature = (CodeSignature) joinPoint.getSignature();

        HashMap<String, Object> map = new HashMap<>();
        String[] parameterNames = signature.getParameterNames();
        for (int i = 0; i < parameterNames.length; i++) {
            map.put(parameterNames[i], joinPoint.getArgs()[i]);
        }
        return map;
    }
}
