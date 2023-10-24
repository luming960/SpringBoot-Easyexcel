package com.wlb.file.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author: zk
 * @create 2022-10-09 8:55
 */
@Aspect
@Slf4j
@Component
public class MapperAspect {
    @AfterReturning("execution(* com.wlb.file.mapper.*Mapper.*(..))")
    public void logServiceAccess(JoinPoint joinPoint) {
        log.info("Completed: " + joinPoint);
    }


    /**
     * 监控com.lsj.xcjfs.dao..*Mapper包及其子包的所有public方法
     */
    @Pointcut("execution(* com.wlb.file.mapper.*Mapper.*(..))")
    private void pointCutMethod() {
    }

    /**
     * 声明环绕通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long begin = System.nanoTime();
        Object obj = pjp.proceed();
        long end = System.nanoTime();

        log.info("调用Mapper方法：{}，参数：{}，执行耗时：{}纳秒，耗时：{}毫秒",
                pjp.getSignature().toString(), Arrays.toString(pjp.getArgs()),
                (end - begin), (end - begin) / 1000000);
        return obj;
    }

}
