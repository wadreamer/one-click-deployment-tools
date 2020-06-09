package com.cfg.deploytools.shiro.config;
import com.cfg.deploytools.shiro.service.UserRealm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ClassName: ShiroConfig
 * Description:
 * date: 2020/6/8 19:09
 *
 * @author CFG
 * @since JDK 1.8
 */
@Configuration
public class ShiroConfig {
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManage") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        factoryBean.setSecurityManager(defaultWebSecurityManager);
        //添加shiro内置过滤器
//        Map<String, String> filterMap = new LinkedHashMap<>();
//        filterMap.put("/cfg/home", "authc");
//        factoryBean.setFilterChainDefinitionMap(filterMap);
//        factoryBean.setLoginUrl("/cfg/login");
        return factoryBean;
    }

    @Bean(name = "securityManage")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }


    //创建Realm对象  需要自己定义
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

}
