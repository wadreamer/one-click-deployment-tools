package com.cfg.deploytools.common.dataSources;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: DataSource
 * Description: // TODO 自定义数据源注解，默认为主数据源
 * date: 2020/6/5 10:26
 *
 * @author CFG
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceType value() default DataSourceType.MASTER;
}
