package com.cn.cdtgxd.util.aop;

import com.cn.cdtgxd.service.StrsqlResLogService;
import com.cn.cdtgxd.util.exception.AuthorizeException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class MyAspect {

    @Autowired
    StrsqlResLogService strsqlResLogService;

    /**
     *     * 前置通知：目标方法执行之前执行以下方法体的内容      * @param jp    
     */
    @Before("execution(* com.cn.cdtgxd.service.*.*(..))")
    public void beforeMethod(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
    }
    /**
     *     * controller前置通知
     */
    @Before("execution(* com.cn.cdtgxd.controller.*.*(..))&& !execution(public * com.cn.cdtgxd.controller.LoginController.*(..))")
    public void beforeMethodController(JoinPoint jp) {
        String methodName = jp.getSignature().getName();
        System.out.println("【controller前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));

        if( strsqlResLogService.findAlldesc().size()>0 && String.valueOf(strsqlResLogService.findAlldesc().get(0)).equals("0")){
            System.out.println("【controller前置通知】:正在校验中。。。");
            throw new AuthorizeException();
        }

    }

    /**
     *     * 返回通知：目标方法正常执行完毕时执行以下代码     * @param jp     * @param result    
     */
    @AfterReturning(value = "execution(* com.cn.cdtgxd.service.*.*(..))", returning = "result")
    public void afterReturningMethod(JoinPoint jp, Object result) {
        String methodName = jp.getSignature().getName();
        System.out.println("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
    }


    /**
     *     * 后置通知：目标方法执行之后执行以下方法体的内容，不管是否发生异常。     * @param jp    
     */
    @After("execution(* com.cn.cdtgxd.service.*.*(..))")
    public void afterMethod(JoinPoint jp) {
        System.out.println("【后置通知】this is a afterMethod advice...");
    }


    /**
     *     * 异常通知：目标方法发生异常的时候执行以下代码    
     */
    @AfterThrowing(value = "execution(* com.cn.cdtgxd.service.*.*(..))", throwing = "e")
    public void afterThorwingMethod(JoinPoint jp, NullPointerException e) {
        String methodName = jp.getSignature().getName();
        System.out.println("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
    }


}


