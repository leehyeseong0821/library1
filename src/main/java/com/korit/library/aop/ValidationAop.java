package com.korit.library.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BeanPropertyBindingResult;

@Aspect
@Component
public class ValidationAop {
                            //ValidAspect 어노테이션의 경로를 넣어준다.
    @Pointcut("@annotation(com.korit.library.aop.annotation.ValidAspect)")
    private void pointCut(){}

    @Around("pointCut")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        벨리데이션체크
            Object[] args = proceedingJoinPoint.getArgs();

        BeanPropertyBindingResult bindingResult = null;

            for(Object arg : args){
                if(arg.getClass() == BeanPropertyBindingResult.class){
                    bindingResult = (BeanPropertyBindingResult) arg; //다운캐스팅
                }
            }

            //에러를 가지고 있으면
            if(bindingResult.hasErrors()){

            }

        return  proceedingJoinPoint.proceed();
    }
}
