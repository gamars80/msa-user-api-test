package com.example.msauserapitest.logging;

import com.example.msauserapitest.exception.CustomException;
import org.slf4j.MDC;
import org.springframework.core.NestedRuntimeException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class MDCExceptionLogger {
    public static void getStringStringMap(Exception ex, String errorOrigin) {
        MDC.put("error_originated", errorOrigin);
        MDC.put("server_message", ex.getMessage()); //error_detail
        MDC.put("stack_trace",  ex.getStackTrace()[0].toString()); //error_trace
        if(ex.getCause() != null) {
            MDC.put("cause", ex.getCause().toString()); //error_cause
        }

        if (ex instanceof CustomException ce && ce.getClientMessage() != null) {
            MDC.put("client_message", ce.getClientMessage());
        }

        if (ex instanceof NestedRuntimeException nre && nre.getRootCause() != null) {
            MDC.put("root_cause", nre.getRootCause().toString()); //error_root_cause
        }

        Class<?> clazz = ex.getClass();
        try {
            Method method = clazz.getDeclaredMethod("getReason");
            if (method.invoke(ex) != null) {
                MDC.put("reason", method.invoke(ex).toString());
            }
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }
}
