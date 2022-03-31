package com.max.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class);

    @Pointcut("execution(* com.max.*.*.*(..))")
    public void all() {}

    @Pointcut("execution(* com.max.dao.*.*(..))")
    public void dao() {}

    @Before("dao()")
    public void beforeUpdatingDatabase(JoinPoint joinPoint) {
        logger.info("Using database with {" + joinPoint.getSignature() + "}");
    }

    @AfterThrowing(value = "all()", throwing = "exception")
    public void logError(JoinPoint joinPoint, Throwable exception) {
        logger.error("Something went wrong at {" + joinPoint.getSignature() + "}");
        logger.error(exception);
    }
}
