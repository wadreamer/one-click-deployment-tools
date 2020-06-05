package com.cfg.deploytools.common.dataSources;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * ClassName: DataSourceAspect
 * Description:
 * date: 2020/6/5 10:30
 *
 * @author CFG
 * @since JDK 1.8
 */
@Aspect
@Component
@Order(1)
public class DataSourceAspect {

    @Pointcut("@annotation(com.cfg.deploytools.common.dataSources.DataSource)")
    public void dsPointCut(){}

    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();

        Method method = signature.getMethod();

        DataSource dataSource = method.getAnnotation(DataSource.class);

        if(null != dataSource){
            DataSourceContextHolder.setDataSource(dataSource.value().name());
        }

        try {
            return joinPoint.proceed();
        }finally {
            // 执行方法后，销毁数据源
            DataSourceContextHolder.clearDataSource();
        }
    }

}
