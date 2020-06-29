package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.FileMapLocalPath;
import com.cfg.deploytools.model.SQLFile;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.service.FileService;
import com.cfg.deploytools.utils.StringUtils;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.AnnotationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName: FileController
 * Description:
 * date: 2020/6/8 11:43
 *
 * @author CFG
 * @since JDK 1.8
 */
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
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public AjaxResult fileUpload(MultipartFile[] files,
                                 String sqlFiles,
                                 String taskId,
                                 String fileMapLocalPath,
                                 String configurationPath, boolean flag) {
        String[] sqlFileArr = sqlFiles != null ? StringUtils.JsonStringHandler(sqlFiles) : null;
        String[] configurationPathArr = fileMapLocalPath != null ? StringUtils.JsonStringHandler(fileMapLocalPath) : null;

        return fileService.upload(files, sqlFileArr, Integer.parseInt(taskId), configurationPathArr, configurationPath);
        // return null;
    }

    @ApiOperation(value = "检测冲突", notes = "检测冲突")
    @ResponseBody
    @RequestMapping(value = "/checkConflict")
    public AjaxResult checkConflict(String fullPath, String taskId) {
        System.out.println(fullPath);
        System.out.println(taskId);
        // return null;
        return fileService.checkConflict(fullPath, Integer.parseInt(taskId)).size() > 0 ?
                AjaxResult.error("文件或存储过程" +  fullPath +"存在冲突，是否继续上传？") : AjaxResult.success("无冲突");
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
    @RequestMapping("/history")
    public AjaxResult fileHistory(String fullPath) {
        List<File> list = fileService.getFileHistory(fullPath);
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


    @RequestMapping("/deployFile")
    @ResponseBody
    public AjaxResult deployFile(int fileId) {
        File deployFile = fileService.getFileById(fileId);
        return deployFile.getFileData() != null ? AjaxResult.success(200, deployFile) : AjaxResult.error("操作失败，请稍后重试");
    }

}
