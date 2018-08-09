package com.bmw.azure.oap.aspect;

import com.bmw.azure.oap.common.utils.JSONMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Tongshan.Han@partner.bmw.com
 * @Description:
 * @date 2018/8/9 10:10
 */
@Slf4j
@Aspect
@Component
public class HttpAspect {

        @Pointcut(value = "execution(public * com.bmw.azure.oap.controller.*Controller.*(..))")
        public void log(){
        }

        /**
         * Aspect拦截Http请求,依赖spring-boot-starter-web,如果使用spring-boot-starter-webflux则不能实现拦截请求
         * url=http://localhost:8096/user/list,
         * method=GET,
         * ip=0:0:0:0:0:0:0:1,
         * class_method=com.bmw.azure.oap.controller.UserController.list,
         * args={},
         *
         * */
        @Before("log()")
        public void doBefore(JoinPoint joinPoint){
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();
            //url
            log.info("url={}", request.getRequestURL());
            //method
            log.info("method={}", request.getMethod());
            //ip
            log.info("ip={}", request.getRemoteAddr());
            //类方法
            log.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
            //参数
            log.info("args={}", joinPoint.getArgs());
        }

        @After("log()")
        public void doAfter(){
            log.info("333333333333333333333333");
        }

        /**
         * 获取拦截的http请求的返回值
         * response={"1":{"id":"1","name":"zhangsan","email":"lxm@bmw.com"},"3":{"id":"2","name":"zhangsan","email":"lxm@bmw.com"},"5":{"id":"4","name":"zhangsan","email":"lxm@bmw.com"}}
         * */
        @AfterReturning(returning = "object", pointcut = "log()")
        public void doReturn(Object object){
            log.info("response={}", JSONMapper.writeObjectAsString(object));
        }



}
