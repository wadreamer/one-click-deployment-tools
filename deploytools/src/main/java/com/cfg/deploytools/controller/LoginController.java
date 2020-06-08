package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: LoginController
 * Description:
 * date: 2020/6/4 18:11
 *
 * @author CFG
 * @since JDK 1.8
 */
@RequestMapping("/cfg_dt/login")
@Controller
public class LoginController {


    /*
     * @Author wadreamer
     * @Description //TODO 整合 shiro 实现登录
     * @Date 11:40 2020/6/8
     * @Param [user]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @GetMapping("/login")
    public AjaxResult login(User user){

        return null;
    }


}
