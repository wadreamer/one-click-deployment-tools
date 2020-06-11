package com.cfg.deploytools;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeploytoolsApplicationTests {

    @Test
    void contextLoads() {
        String name = "proc_dasdas()";
        System.out.println(name.substring(0,name.indexOf("_")).toLowerCase());
    }

}
