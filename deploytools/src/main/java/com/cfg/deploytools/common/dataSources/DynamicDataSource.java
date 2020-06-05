package com.cfg.deploytools.common.dataSources;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * ClassName: DynamicDataSource
 * Description: TODO 动态数据源
 * date: 2020/6/5 10:52
 *
 * @author CFG
 * @since JDK 1.8
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    public DynamicDataSource(javax.sql.DataSource dataSource, Map<Object, Object> targetDataSources){
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSource();
    }
}
