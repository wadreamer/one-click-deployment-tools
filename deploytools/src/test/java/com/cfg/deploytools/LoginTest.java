package com.cfg.deploytools;

import com.cfg.deploytools.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {
    @Autowired
    UserService userService;


    @Test
    public void testLog(){
        System.out.println("用户账户：" + userService.queryUserByAccount("linww").getAccount());
        System.out.println("用户密码：" + userService.queryUserByAccount("linww").getPassword());
    }
}
