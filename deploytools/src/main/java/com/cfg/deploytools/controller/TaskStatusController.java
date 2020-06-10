package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskStatusMapper;
import com.cfg.deploytools.model.TaskStatus;
import com.cfg.deploytools.service.TaskStatusService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "任务状态相关")
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
    @ApiOperation(value = "将任务状态从 待测试 --> 测试中", notes = "将任务状态从 待测试 --> 测试中")
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
    @ApiOperation(value = "同步修改任务状态， 禅道 已完成 --> 已关闭， 新建的表 测试中 --> 测试通过",
            notes = "同步修改任务状态， 禅道 已完成 --> 已关闭， 新建的表 测试中 --> 测试通过")
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
    @ApiOperation(value = "驳回任务，禅道 已完成 --> 进行中，新建的表 删除cfg_taskstatus cfg_file cfg_taskfile 相关数据")
    @ResponseBody
    @RequestMapping("/reject")
    public AjaxResult rejectTask(TaskStatus taskStatus) {
        return taskStatusService.updateTaskStatusFoeReject(taskStatus);
    }

}