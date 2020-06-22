package com.cfg.deploytools.shiro.service;

import com.cfg.deploytools.model.User;
import com.cfg.deploytools.service.UserService;
import com.google.gson.Gson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权执行了");
        return null;
    }


    //用户认证模块
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //前端获取的数据
        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //真实数据
        User user = userService.queryUserByAccount(userToken.getUsername());
        String password = new String((char[]) token.getCredentials());
        if (user == null) {
            System.out.println("In realm, user is null");
            return null;    //抛出异常UnknownAccountException
        }

        System.out.println(new Gson().toJson(user));
        //密码验证shiro自己做
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }

    //
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }
}
