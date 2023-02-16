package com.grekoff.lesson3.aop;


import com.grekoff.lesson3.dtos.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Aspect
@Component
public class AppLoggingAspect {


    @Before("execution(public * com.grekoff.lesson3.controllers.*.*(..))")
    public void beforeAnyMethodInConverters(JoinPoint jp) {
        Class<?> beanClass = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        String arg = "";
        if (args.length > 0) {
            for (Object o : args) {
                arg = arg + o + ", " ;
            }
        }
        log.info("В ПРИЛОЖЕНИИ ВЫЗВАН МЕТОД: {}#{}. АРГУМЕНТЫ: {}", beanClass.getName(), methodName, arg);
    }

    @AfterReturning(
            pointcut = "execution(public * com.grekoff.lesson3.controllers.*.*(..)))",
            returning = "result")
    public void afterGetBobInfo(JoinPoint joinPoint, Page<ProductDto> result) {
        log.info("РЕЗУЛЬТАТ: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(public * com.grekoff.lesson3.controllers.*.*(..))",
            throwing = "exc")
    public void afterThrowing(JoinPoint jp, Throwable exc) {
        Class<?> beanClass = jp.getTarget().getClass();
        String methodName = jp.getSignature().getName();
        log.error("В МЕТОДЕ {}#{} ПРОИЗОШЛО ИСКЛЮЧЕНИЕ:  {}", beanClass.getName(), methodName, String.valueOf(exc));
    }

    @After("execution(public * com.grekoff.lesson3.controllers.*.*(..))")
    public void afterMethod() {
        log.info("ВЫЗОВ ЗАВЕРШЕН");
    }

}
