package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TaskController
 * Description:
 * date: 2020/6/5 9:17
 *
 * @author CFG
 * @since JDK 1.8
 */
@RequestMapping("/cfg_dt/task")
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    /*
     * @Author wadreamer
     * @Description //TODO 根据任务列表，获取任务详情
     * @Date 11:39 2020/6/8
     * @Param [taskId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("/{taskId}")
    public AjaxResult getTaskCurrentFile(@PathVariable("taskId") String taskId) {

        return null;
    }

    
    /*
     * @Author wadreamer
     * @Description //TODO 根据项目工程主键，获取该项目工程下的任务列表
     * @Date 16:42 2020/6/8
     * @Param [tableParse, projectId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("/list/{projectId}")
    public AjaxResult getTaskList(TableParse tableParse, @PathVariable("projectId") String projectId) {
        return taskService.getTaskListByProjectId(tableParse, Integer.parseInt(projectId));
    }


}
