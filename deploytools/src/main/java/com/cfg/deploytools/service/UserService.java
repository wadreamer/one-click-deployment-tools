package com.cfg.deploytools.service;

import com.cfg.deploytools.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/6/8 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


}
