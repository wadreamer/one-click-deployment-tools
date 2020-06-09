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
     * @Description //TODO 修改任务状态为测试中 OK
     * @Date 18:06 2020/6/8
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("/testing")
    public AjaxResult updateTaskStatusForTesting(TaskStatus taskStatus) {
        if (taskStatus.getStatus() == null) {
            return AjaxResult.error("操作失败，请稍后重试");
        }
        return taskStatusService.updateTaskStatusForTest(taskStatus, false);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 同步修改任务状态，测试通过  <--> 关闭 OK
     * @Date 9:13 2020/6/9
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("/pass")
    public AjaxResult updateTaskStatusForPass(TaskStatus taskStatus) {
        if (taskStatus.getStatus() == null) {
            return AjaxResult.error("操作失败，请稍后重试");
        }
        return taskStatusService.updateTaskStatusForTest(taskStatus, true);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 驳回任务 OK
     * @Date 15:17 2020/6/9
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @RequestMapping("/reject")
    public AjaxResult rejectTask(TaskStatus taskStatus) {
        return taskStatusService.updateTaskStatusFoeReject(taskStatus);
    }

}