package com.cfg.deploytools.common.conf;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.cfg.deploytools.common.dataSources.DataSourceType;
import com.cfg.deploytools.common.dataSources.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*
 * @Author wadreamer
 * @Description //TODO Mybatis多数据源配置 参考文章：https://www.cnblogs.com/geekdc/p/10963476.html
 * @Date 14:01 2020/6/8
 * @Param 
 * @return 
 **/
@Configuration
@MapperScan(basePackages = "com.cfg.deploytools.mapper")
public class MybatisConfig {
	
	@Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.salve")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.salve", name = "enabled", havingValue = "true")
    public DataSource salveDataSource()
    {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource salveDataSource)
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
    	targetDataSources.put(DataSourceType.SALVE.name(),salveDataSource);
        return new DynamicDataSource(masterDataSource(), targetDataSources);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dynamicDataSource);
//        factoryBean.setTypeAliasesPackage();
        // 设置mapper.xml的位置路径
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:mybatis/*.xml");
        factoryBean.setMapperLocations(resources);
        return factoryBean.getObject();
    }
    
    /**
     * 配置@Transactional注解事务
     * @param dynamicDataSource
     * @return
     * @author fuce
     * @Date 2019年12月7日 上午11:31:33
     */
    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }
}
