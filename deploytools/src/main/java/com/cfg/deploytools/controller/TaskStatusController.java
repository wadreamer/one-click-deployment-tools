package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskStatusMapper;
import com.cfg.deploytools.model.TaskStatus;
import com.cfg.deploytools.service.TaskStatusService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TaskStatusController
 * Description:
 * date: 2020/6/8 17:49
 *
 * @author CFG
 * @since JDK 1.8
 */
@Api(value = "任务状态")
@RequestMapping("/cfg_dt/task_status")
@Controller
public class TaskStatusController {

    @Autowired
    private TaskStatusService taskStatusService;

    /*
     * @Author wadreamer
     * @Description //TODO 修改任务的测试状态
     * @Date 18:06 2020/6/8
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("updateStatus")
    public AjaxResult updateTaskStatus(TaskStatus taskStatus) {
        return taskStatusService.updateTaskStatus(taskStatus);
    }


}