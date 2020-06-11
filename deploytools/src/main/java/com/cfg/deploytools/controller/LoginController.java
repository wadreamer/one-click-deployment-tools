package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.User;
import com.cfg.deploytools.utils.MD5Util;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020/6/4 18:11
 *
 * @author CFG
 * @since JDK 1.8
 */

@CrossOrigin//跨域问题
@RequestMapping("/cfg_dt")
@Controller
public class LoginController {
    @RequestMapping("/login")
    @ResponseBody
    public AjaxResult login(User user){
        System.out.println(user);
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //将前端密码转为MD5
        String MD5_PassWord = MD5Util.encode(user.getPassword());
        //封装用户的登入数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(), MD5_PassWord);
        try{
            subject.login(token);
            return AjaxResult.success(200,"登入成功");
        }catch (UnknownAccountException e){
            return AjaxResult.error(401, "账户错误");
        }catch (IncorrectCredentialsException e){
            return AjaxResult.error(402, "密码错误");
        }
    }

}
