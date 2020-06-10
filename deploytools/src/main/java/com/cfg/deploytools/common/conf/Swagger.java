package com.cfg.deploytools.common.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: Swagger
 * Description:
 * date: 2020/6/5 9:21
 *
 * @author CFG
 * @since JDK 1.8
 */
@Configuration
@EnableSwagger2
public class Swagger {

    /*
     * @Author wadreamer
     * @Description //TODO 创建API自动化接口测试文档
     * @Date 9:59 2020/6/5
     * @Param []
     * @return springfox.documentation.spring.web.plugins.Docket
     **/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                // 用于创建该API接口文档的基本信息
                .apiInfo(apiInfo())
                // 设置哪些接口暴露给Swagger展示
                .select()
                // 使用所有有Api注解的controller
                .apis(RequestHandlerSelectors.basePackage("com.cfg.deploytools"))
                // 扫描所有 .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
    /*
     * @Author wadreamer
     * @Description //TODO API接口的基本信息
     * @Date 9:59 2020/6/5
     * @Param []
     * @return springfox.documentation.service.ApiInfo
     **/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                // 设置标题
                .title("one-click deployment tools API document")
                // 设置描述信息
                .description("one-click deployment tools")
                .build();
    }
}
