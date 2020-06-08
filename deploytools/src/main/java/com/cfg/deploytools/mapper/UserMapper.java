package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.User;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: UserMapper
 * Description: TODO 用户登录
 * date: 2020/6/8 9:22
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface UserMapper {
    User queryUserByAccount(@Param("account") String account); // 根据用户名查找用户

}
