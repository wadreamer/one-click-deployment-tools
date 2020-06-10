package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@Api("登录")
@RequestMapping("/cfg_dt")
@Controller
public class LoginController {

    /*
     * @Author wadreamer
     * @Description //TODO 整合 shiro 实现登录
     * @Date 11:40 2020/6/8
     * @Param [user]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "登录", notes = "登录")
    @ResponseBody
    @GetMapping("/login")
    public AjaxResult login(User user){
        System.out.println(user);
        return null;
    }


}
