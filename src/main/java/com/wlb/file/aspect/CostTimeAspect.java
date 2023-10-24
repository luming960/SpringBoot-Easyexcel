package com.wlb.file.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: zk
 * @create 2022-08-02 15:47
 */
@Aspect
@Slf4j
@Component
public class CostTimeAspect {

    private static final String AOP_POINTCUT = "execution (public * com.wlb.file.service.impl.*.*(..))";

    @Pointcut(value = AOP_POINTCUT)
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object costTimeAround(ProceedingJoinPoint joinPoint) {
        Object obj = null;
        try {
            long beginTime = System.currentTimeMillis();
            obj = joinPoint.proceed();
            //获取方法名称
            String method = joinPoint.getSignature().getName();
            //获取类名称
            String class1 = joinPoint.getSignature().getDeclaringTypeName();
            //计算耗时
            long cost = System.currentTimeMillis() - beginTime;
            log.info("类:[{}]，方法:[{}] 接口耗时:[{}]", class1, method, cost + "毫秒");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }

}
