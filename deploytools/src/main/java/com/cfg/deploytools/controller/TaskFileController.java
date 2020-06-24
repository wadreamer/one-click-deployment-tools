package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.service.TaskFileService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName: TaskFileController
 * Description:
 * date: 2020/6/23 15:09
 *
 * @author CFG
 * @since JDK 1.8
 */
@CrossOrigin//跨域问题
@Api("任务文件")
@RequestMapping("/cfg_dt/taskfile")
@Controller
public class TaskFileController {

    @Autowired
    private TaskFileService taskFileService;

    @RequestMapping("/EnableOrNot")
    @ResponseBody
    public AjaxResult fileDisabledOrEnabled(String taskId, String fullPath, String fileId) {
        return taskFileService.disableFileWithUpdateZero(taskId, fullPath, fileId);
    }

}
