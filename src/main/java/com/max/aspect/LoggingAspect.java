package com.max.aspect;

import com.max.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.max.dao.*.*(..))")
    public void daoPointcut() {}

    @Pointcut("execution(* com.max.dao.*.get*(..))")
    public void daoGetMethodsPointcut() {}

    @Pointcut("execution(java.util.List com.max.dao.UserDAO.getUsers())")
    public void daoListUsers() {}

    @Before("daoPointcut()")
    public void beforeDaoMethods(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        System.out.println("> @Before " + signature.toString());
    }

    @Before("daoPointcut() && !daoGetMethodsPointcut()")
    public void beforeUpdatingDatabase() {
        System.out.println("> @Before some updates in db");
    }

    @AfterReturning(value = "daoListUsers()", returning = "users")
    public void afterListingUsers(JoinPoint joinPoint, List<User> users) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        System.out.println("> @AfterReturning " + signature.toString() + ":");
        users.forEach(System.out::println);
    }
}
