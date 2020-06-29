package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * ClassName: FileController
 * Description:
 * date: 2020/6/8 11:43
 *
 * @author CFG
 * @since JDK 1.8
 */
@CrossOrigin//跨域问题
@Api("文件相关")
@RequestMapping("/cfg_dt/file")
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    /*
     * @Author wadreamer
     * @Description //TODO 多文件上传
     * @Date 11:49 2020/6/8
     * @Param [files]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "上传文件", notes = "上传文件")
    @ResponseBody
    @PostMapping("/upload")
    public AjaxResult fileUpload(MultipartFile file, String content, String fullPath, String taskId, boolean flag) {
        // 存储过程的前缀名 proc_
        String firstName = fullPath.indexOf("_") != -1 ? fullPath.substring(0, fullPath.indexOf("_")).toLowerCase() : "";
        if (!file.isEmpty()) {
            if ((fileService.checkConflict(fullPath, Integer.parseInt(taskId)) || firstName.equals("proc")) && !flag) {
                return AjaxResult.error("上传的文件：" + fullPath + "存在冲突，是否继续上传");
            }
            return fileService.upload(file, fullPath);
        } else {
            return fileService.upload(content, fullPath);
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 多文件下载
     * @Date 11:50 2020/6/8
     * @Param [fileId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "文件下载，即部署", notes = "文件下载，即部署")
    @ResponseBody
    @PostMapping("/download/{fileId}")
    public AjaxResult fileDownload(String fileId) {

        return null;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 查看某个文件的历史版本
     * @Date 8:45 2020/6/10
     * @Param [fileId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "获取某个文件的所有历史版本", notes = "获取某个文件的所有历史版本")
    @ResponseBody
    @RequestMapping("/history/{fileId}")
    public AjaxResult fileHistory(@PathVariable("fileId") String fileId) {
        List<File> list = fileService.getFileHistory(Integer.parseInt(fileId));
        return list != null ? AjaxResult.success(200, list) : AjaxResult.error("操作失败，请稍后重试");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 预览 sql 文件内容
     * @Date 10:25 2020/6/11
     * @Param [taskFile]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ApiOperation(value = "预览文件内容", notes = "预览文件内容")
    @ResponseBody
    @RequestMapping("/sqlpreview")
    public AjaxResult SQLPreview(TaskFile taskFile) {
        return fileService.getFileContent(taskFile);
    }


    /**
     * 根据ID查找文件
     * @param fileId
     * @return
     */
    @ApiOperation(value = "部署单个文件", notes = "部署单个文件")
    @RequestMapping("/deployFile")
    @ResponseBody
    public AjaxResult deployFile(int fileId) {
        File deployFile = fileService.getFileById(fileId);
        return deployFile != null ? AjaxResult.success(200, deployFile) : AjaxResult.error("操作失败，请稍后重试");
    }
}
