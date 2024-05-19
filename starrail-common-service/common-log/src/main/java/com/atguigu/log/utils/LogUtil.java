package com.atguigu.log.utils;


import com.alibaba.fastjson.JSON;
import com.atguigu.log.annoation.Log;
import com.atguigu.commonservice.utils.AuthContext;
import com.atguigu.model.common.OperLog;
import com.atguigu.model.pojo.UserBasic;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.http.HttpMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;

public class LogUtil {
    // 操作执行之后调用
    public static void afterHandleLog(Log sysLog, Object proceed, OperLog sysOperLog, int status, String errorMsg) {

        // 判断是否需要保存响应数据
        if (sysLog.isSaveResponseData()) {
            // 设置响应数据
            sysOperLog.setJsonResult(JSON.toJSONString(proceed));
        }

        // 设置操作状态
        sysOperLog.setStatus(status);
        // 设置错误信息
        sysOperLog.setErrorMsg(errorMsg);

    }

    //操作执行之前调用
    public static void beforeHandleLog(Log sysLog, ProceedingJoinPoint joinPoint, OperLog sysOperLog) {

        // 从注解获取模块名称，并存入日志
        sysOperLog.setTitle(sysLog.title());
        sysOperLog.setOperatorType(sysLog.operatorType().name());

        // 从切入点获取目标方法签名
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 根据方法签名获取方法名Method
        Method method = methodSignature.getMethod();
        // 根据方法对象获取定义该方法的类Class的类名
        sysOperLog.setMethod(method.getDeclaringClass().getName());

        // 获取请求相关参数

        /*
         * RequestContextHolder是Spring框架提供的一个工具类，
         * 用于在多线程环境中存储和访问当前请求的信息。
         * 基于一个内置的线程级别变量ThreadLocal
         * 它用于在Spring Web应用程序中获取和传递请求的上下文。
         * 在Web应用程序中，每个请求都会创建一个线程来处理。
         * RequestContextHolder允许您从任何地方访问当前请求的上下文，
         * 而无需显式地将请求对象传递给每个方法。
         * 它使用一个ThreadLocal变量来存储和管理每个线程的请求上下文。
         * */

        // 从请求上下文存储器中获取当前请求对象
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();

        // 设置请求方法
        sysOperLog.setRequestMethod(request.getMethod());
        // 设置请求URL
        sysOperLog.setOperUrl(request.getRequestURI());
        // 设置请求IP
        sysOperLog.setOperIp(request.getRemoteAddr());


        // 保存请求数据
        if (sysLog.isSaveRequestData()) {
            // 获取请求的方法类型，例如Post、Delete、Get、Put
            String requestMethod = sysOperLog.getRequestMethod();
            // 对于PUT和POST请求，保存请求参数
            if (HttpMethod.PUT.name().equals(requestMethod) || HttpMethod.POST.name().equals(requestMethod)) {
                String params = Arrays.toString(joinPoint.getArgs());
                // 保存请求参数
                sysOperLog.setOperParam(params);
            }
        }

        // 实在优雅
        // 从ThreadLocal中获取当前线程的用户信息

        UserBasic userBasic = AuthContext.getAuthContext();
        if (userBasic != null){
            sysOperLog.setOperName(userBasic.getName());
        }else {
            sysOperLog.setOperName("未登录");
        }

    }
}
