package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @PostMapping("/{taskId}")
    public AjaxResult getTaskDetail(String taskId){

        return null;
    }


}
