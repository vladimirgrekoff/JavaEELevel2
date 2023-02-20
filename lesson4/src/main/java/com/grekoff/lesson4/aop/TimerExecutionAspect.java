package com.grekoff.lesson4.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
//@Aspect
//@Component
public class TimerExecutionAspect {
    @Pointcut("@annotation(TimerExecution)")
        public void targetPointcut() {
    }


    @Around("targetPointcut()")
    public Object executionTimer(ProceedingJoinPoint pjp) throws Throwable {
        Class<?> beanClass = pjp.getTarget().getClass();
        String methodName = pjp.getSignature().getName();
        long begin = System.currentTimeMillis();
        Object out = pjp.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        log.info("ВРЕМЯ ВЫПОЛНЕНИЯ {}#{} : {} ms", beanClass.getName(), methodName, duration);
        return out;
    }
}
