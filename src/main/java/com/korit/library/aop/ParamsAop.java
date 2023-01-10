package com.korit.library.aop;


import com.korit.library.exception.CustomValidationException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@Aspect
@Component
public class ParamsAop {
                            //ValidAspect 어노테이션의 경로를 넣어준다.
    @Pointcut("@annotation(com.korit.library.aop.annotation.ParamsAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        벨리데이션체크
        Object[] args = proceedingJoinPoint.getArgs();


        for(Object arg : args) {
            log.info("{}", arg);
        }

        return proceedingJoinPoint.proceed();
    }

}
