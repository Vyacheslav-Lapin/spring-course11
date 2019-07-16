package ru.sbt.courses.spring.springcourse10.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* *.MyService.*(..))")
    public void serviceMethod() {
    }

    @Around("serviceMethod()")
    public Object logWebServiceCall(ProceedingJoinPoint thisJoinPoint) throws Throwable {
        String methodName = thisJoinPoint.getSignature().getName();
        Object[] methodArgs = thisJoinPoint.getArgs();

        log.info("Call method {} with args {}", methodName, methodArgs);

        Object result = thisJoinPoint.proceed();

        log.info("Method {} returns {}", methodName, result);
        return result;
    }
}
