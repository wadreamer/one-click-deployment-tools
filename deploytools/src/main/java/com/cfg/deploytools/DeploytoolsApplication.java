package com.cfg.deploytools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class DeploytoolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeploytoolsApplication.class, args);
    }

}
