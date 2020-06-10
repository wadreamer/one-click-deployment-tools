package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.service.FileService;
import com.cfg.deploytools.service.TaskFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: APIController
 * Description:
 * date: 2020/6/9 11:09
 *
 * @author CFG
 * @since JDK 1.8
 */
@RequestMapping("/API")
@RestController
public class APIController {

    @Autowired
    private TaskFileService taskFileService;

    @Autowired
    private FileService fileService;

    @RequestMapping("/taskFileList")
    public AjaxResult getTaskFileList(int taskId){
        List<TaskFile> list = taskFileService.getTaskFileListByTaskId(taskId);
        return list.size() > 0 ? AjaxResult.success(200,list) : AjaxResult.error("操作失败");
    }

    @RequestMapping("/deleteFileByFileIdArray")
    public AjaxResult deleteFileByFileIdArray(int taskId){
        List<TaskFile> list = taskFileService.getTaskFileListByTaskId(taskId);
        int result = fileService.deleteFile(list);
        return result > 0 ? AjaxResult.success("操作成功") : AjaxResult.error("操作失败");
    }

    // @RequestMapping("/queryFileNewestByFullPath")
    // public AjaxResult queryFileNewestByFullPath(int taskId){
    //     List<TaskFile> list = taskFileService.getTaskFileListByTaskId(taskId);
    //     List<File> list1 = fileService.getFileListNewest(list);
    //     return list1.size() > 0 ? AjaxResult.success(200,list1) : AjaxResult.error("操作失败");
    // }

}
