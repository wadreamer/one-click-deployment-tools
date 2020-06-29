package com.cfg.deploytools;

import com.cfg.deploytools.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class LoginTest {
    @Autowired
    UserService userService;


    @Test
    public void testLog() {
        String s = "{\"name\": \"test2.sql\", \"content\": \"dasdas,asdqwasd,qwead;qweljdas,dqwe; adasdas dasdas\"},{\"name\": \"test1.sql\", \"content\": \"dasdas,asdqwasd,qwead;qweljdas,dqwe; adasdas dasdas\"}";
        String pattern = "(\\{[\\s\\S]+?\\})";

        Pattern p = Pattern.compile(pattern);

        Matcher m = p.matcher(s);

        ArrayList<String> arr = new ArrayList<String >();
        while(m.find()){
            arr.add(m.group(1));
        }

        for(String s1 : arr){
            System.out.println(s1);
        }

    }

}

