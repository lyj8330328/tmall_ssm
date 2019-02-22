package com.li.tmall.log;


import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: 98050
 * @Time: 2019-02-22 20:36
 * @Feature:
 */
@Aspect
@Component
public class LogAspectServiceApi {
    private static final Logger logger = Logger.getLogger(LogAspectServiceApi.class);

    public LogAspectServiceApi() {
        logger.info("日志功能开启");
    }

    /**
     * 申明一个切点
     */
    @Pointcut("execution(public * com.li.tmall.controller..*.*(..))")
    public void controllerAspect(){

    }

    @Before(value = "controllerAspect()")
    public void methodBefore(JoinPoint joinPoint){
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        logger.info("=============请求内容==============");
        try {
            // 打印请求内容
            logger.info("请求地址:" + request.getRequestURL().toString());
            logger.info("请求方式:" + request.getMethod());
            logger.info("请求类方法:" + joinPoint.getSignature());
            logger.info("请求类方法参数:" + Arrays.toString(joinPoint.getArgs()));
        } catch (Exception e) {
            logger.error("###LogAspectServiceApi.class methodBefore() ### ERROR:", e);
        }
        logger.info("===============请求内容===============");
    }

    @AfterReturning(value = "controllerAspect()")
    public void methodAfterReturning(){

    }
}
