package com.cfg.deploytools.common.conf;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * ClassName: PageHelperConf
 * Description:
 * date: 2020/6/5 10:24
 *
 * @author CFG
 * @since JDK 1.8
 */
@Configuration
public class PageHelperConf {

    /*
     * @Author wadreamer
     * @Description //TODO 分页插件配置
     * @Date 10:34 2020/6/5
     * @Param []
     * @return com.github.pagehelper.PageHelper
     **/
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("offsetAsPageNum", "true");
        properties.setProperty("rowBoundsWithCount", "true");
        properties.setProperty("reasonable", "true");
        properties.setProperty("dialect", "mysql");    //配置mysql数据库的方言
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
