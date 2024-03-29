package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.service.FileService;
import com.cfg.deploytools.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: TaskController
 * Description:
 * date: 2020/6/5 9:17
 *
 * @author CFG
 * @since JDK 1.8
 */
@CrossOrigin//跨域问题
@Api("任务相关")
@RequestMapping("/cfg_dt/task")
@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private FileService fileService;

    /*
     * @Author wadreamer
     * @Description //TODO 获取任务当前使用的文件 ok
     * @Date 11:39 2020/6/8
     * @Param [taskId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "获取当前任务正在使用的所有文件",notes = "获取当前任务正在使用的所有文件")
    @ResponseBody
    @RequestMapping("/{taskId}")
    public AjaxResult getTaskCurrentFile(@PathVariable("taskId") String taskId) {
        List<File> list = fileService.getCurrentFileListByTaskId(Integer.parseInt(taskId));
        return list != null ? AjaxResult.success(200, list) : AjaxResult.error("操作失败，请稍后重试");
    }


    /*
     * @Author wadreamer
     * @Description //TODO 根据项目工程主键，获取该项目工程下的任务列表 Ok
     * @Date 16:42 2020/6/8
     * @Param [tableParse, projectId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "获取某个项目工程下的所有任务", notes = "获取某个项目工程下的所有任务")
    @ResponseBody
    @RequestMapping("/list/{projectId}")
    public AjaxResult getTaskList(TableParse tableParse, @PathVariable("projectId") String projectId) {
        return taskService.getTaskListByProjectId(tableParse, Integer.parseInt(projectId));
    }

    /**
     * 根据taskID查找file
     * @param taskId
     * @return 文件列表
     */
    @RequestMapping("/deployTask")
    @ResponseBody
    public AjaxResult deployTask(Integer taskId) {
        List<File> files = fileService.getFilesByTaskID(taskId);
        return AjaxResult.success(200,files);
    }

}
