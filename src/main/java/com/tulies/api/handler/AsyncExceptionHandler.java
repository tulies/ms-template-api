package com.tulies.api.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 异步调用异常处理控制
 */
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(AsyncExceptionHandler.class);

    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("Async method has uncaught exception, params:{}" + Arrays.toString(params));
        log.error("asyncException:"  + ex.getMessage());
        log.error("Exception :", ex);
        for (Object param : params) {
            log.info("Parameter value - " + param);
        }
    }
}
