package com.max.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.max.dao.*.*(..))")
    public void DAOPointCut() {}

    @Before("DAOPointCut()")
    public void beforeSavingUserAdvice() {
        System.out.println("> @Before executed");
    }

    @Before("DAOPointCut()")
    public void beforeSavingUserAdvice2() {
        System.out.println("> @Before 2 executed");
    }
}
