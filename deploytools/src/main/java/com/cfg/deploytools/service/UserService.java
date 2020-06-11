package com.cfg.deploytools.service;

import com.cfg.deploytools.mapper.UserMapper;
import com.cfg.deploytools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: UserService
 * Description:
 * date: 2020/6/11 13:55
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserByAccount(String account) {
        User user = userMapper.queryUserByAccount(account);
        return user;
    }

}



