package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ClassName: ProjectController
 * Description:
 * date: 2020/6/8 11:29
 *
 * @author CFG
 * @since JDK 1.8
 */
@RequestMapping("/cfg_dt/project")
@Controller
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    
    /*
     * @Author wadreamer
     * @Description //TODO 根据项目工程主键，获取该项目工程下的任务列表
     * @Date 11:31 2020/6/8
     * @Param [projectId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @RequestMapping("/{projectId}")
    public AjaxResult getTaskList(String projectId){
        
        return null;
    }


}
