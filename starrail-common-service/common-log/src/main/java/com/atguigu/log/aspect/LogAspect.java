package com.atguigu.log.aspect;


import com.atguigu.log.annoation.Log;
import com.atguigu.log.service.AsyncOperLogService;
import com.atguigu.model.common.OperLog;
import com.atguigu.log.utils.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@ComponentScan(basePackages = {"com.atguigu.log"})
public class LogAspect {

    @Autowired
    private AsyncOperLogService asyncOperLogService;


    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint, Log sysLog) {
        // 构建前置参数
        OperLog sysOperLog = new OperLog();

        LogUtil.beforeHandleLog(sysLog, joinPoint, sysOperLog);

        Object proceed = null;

        try {
            // 执行业务方法
            proceed = joinPoint.proceed();
            // 构建响应结果参数
            LogUtil.afterHandleLog(sysLog, proceed, sysOperLog, 0, null);
        } catch (Throwable e) {

            // 问题：如果在这个AOP操作中，
            // 出现异常，而这个异常没有向外抛出
            // 就可能会因为切面类的等级问题，导致
            // 异常被压在高优先级的AOP方法内部，而导致
            // 基于AOP的事务控制机制无法正常检测到方法
            // 执行时出现的异常，造成事务无法正常回滚


            // 代码执行进入到catch中，业务方法执行产生异常

            // --- Spring事务控制的原理 ---
            // Spring的事务控制也是基于AOP实现的，即在框架底层定义了
            // 一个事务切面类，这个事务切面类的会负责关闭MySql的自动提交，
            // 以及在异常情况下回滚事务和正常情况下进行事务提交
            // 即回滚事务，打印日志，抛出异常是事务切面类的异常处理方式

            // 当系统中存在多个切面类时，Spring框架会按照@Order注解的值
            // 对切面进行排序，@Order的值越小优先级越高，
            // @Order的值越大优先级越低。优先级越高的切面类越优先执行，
            // 当我们没有给切面类指定排序值的时候，我们自定义的切面类
            // 的优先级和aop切面类的优先级相同，那么此时事务切面类的
            // 优先级要高于自定义切面类

            // 即在没有明确指定排序值的情况下，
            // 贸然使用自定义切面类将异常拦截，并不向上抛出
            // 可能会导致事务无法正常回滚

            // 解决方法：
            // 一：明确@Order排序值,提高value值
            // 二：在自定义类中抛出异常

            // 个人建议：使用@Order排序值，容易被忽略，造成管理上的混乱
            // 直接抛出异常，先交给Spring框架处理，完成事务回滚，
            // 在向上抛出，交给其他业务逻辑进行处理

            throw new RuntimeException(e);
        }

        // 保存日志数据
        asyncOperLogService.saveSysOperLog(sysOperLog);

        return proceed;
    }




}
