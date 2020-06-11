package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: ProjectController
 * Description:
 * date: 2020/6/8 11:29
 *
 * @author CFG
 * @since JDK 1.8
 */
@CrossOrigin//跨域问题
@Api("项目工程相关")
@RequestMapping("/cfg_dt/project")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    /*
     * @Author wadreamer
     * @Description //TODO 获取项目工程列表 OK
     * @Date 14:27 2020/6/8
     * @Param []
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "获取所有的项目工程数据", notes = "获取所有的项目工程数据")
    @ResponseBody
    @RequestMapping("/list")
    public AjaxResult getProjectList() {
        return projectService.getProjectList();
    }

}
