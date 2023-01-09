package com.korit.library.aop;


import com.korit.library.exception.CustomValidationException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class ValidationAop {
                            //ValidAspect 어노테이션의 경로를 넣어준다.
    @Pointcut("@annotation(com.korit.library.aop.annotation.ValidAspect)")
    private void pointCut(){}

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        벨리데이션체크
        Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

        for(Object arg : args) {
            if(arg.getClass() == BeanPropertyBindingResult.class) {
                bindingResult = (BeanPropertyBindingResult) arg; //다운캐스팅
            }
        }

            //에러를 가지고 있으면 에러들을 꺼내서 에러맵에다가 넣어주기.
        if(bindingResult.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            bindingResult.getFieldErrors().forEach(error -> {
                errorMap.put(error.getField(), error.getDefaultMessage());
            });
                //여기서 에러뜨면 커스텀 어드바이스에서 에러를 낚아챈다.
            throw new CustomValidationException(errorMap);
        }

        return proceedingJoinPoint.proceed();
    }

}
