package com.cfg.deploytools.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.FileMapLocalPath;
import com.cfg.deploytools.model.SQLFile;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.service.FileService;
import com.google.gson.Gson;
import com.mysql.cj.jdbc.SuspendableXAConnection;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
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
    public AjaxResult fileUpload(@RequestParam("files") MultipartFile[] files,
                                 @RequestParam("SQLFiles") String[] sqlFiles,
                                 @RequestParam("taskId") String taskId,
                                 @RequestParam("fileMapLocalPath") String[] fileMapLocalPath,
                                 @RequestParam("configurationPath") String configurationPath, boolean flag) {

        // 将所有的文件以 文件名 - 文件 的形式存储到一个 map 中
        HashMap<String, MultipartFile> map = new HashMap<>();
        for (MultipartFile file : files) {
            map.put(file.getOriginalFilename(), file);
        }

        // 将 文件名 - 文件路径 转成对象, 并保存到 ArrayList 中
        // 并检测文件是否存在冲突
        List<FileMapLocalPath> fileMapLocalPathList = new ArrayList<>();
        FileMapLocalPath fmp;
        for (String filePath : fileMapLocalPath) {
            fmp = new Gson().fromJson(filePath.replaceAll("\\\\", "\\\\\\\\"), FileMapLocalPath.class);

            int end = fmp.getLocalPath().length();
            int start = configurationPath.length();
            fmp.setLocalPath(fmp.getLocalPath().substring(start, end - 1));
            fileMapLocalPathList.add(fmp);

            if (fileService.checkConflict(fmp.getLocalPath(), Integer.parseInt(taskId)) && !flag) {
                return AjaxResult.error("上传的文件存在冲突的可能，是否要继续上传");
            }
        }

        // 检测 存储过程proc_ 是否存在冲突

        for (String sql : sqlFiles) {
            System.out.println(sql);
            SQLFile sqlFile = new Gson().fromJson(sql, SQLFile.class);

            int end = sqlFile.getPath().length();
            int start = configurationPath.length();
            sqlFile.setPath(sqlFile.getPath().substring(start, end)); // 没有传入 path，path为空


            System.out.println(sqlFile.getPath());
        }


        //for (MultipartFile f : files) {
        //    System.out.println(new Gson().toJson(f));
        //}
        //
        //for (MultipartFile f : files) {
        //    System.out.println(f.getOriginalFilename());
        //}
        //

        // 存储过程的前缀名 proc_
        //String firstName = fullPath.indexOf("_") != -1 ? fullPath.substring(0, fullPath.indexOf("_")).toLowerCase() : "";
        //for(MultipartFile file : files) {
        //    if (!file.isEmpty()) {
        //        if ((fileService.checkConflict(fullPath, Integer.parseInt(taskId)) || firstName.equals("proc")) && !flag) {
        //            return AjaxResult.error("上传的文件：" + fullPath + "存在冲突，是否继续上传");
        //        }
        //        return fileService.upload(file, fullPath);
        //    } else {
        //        return fileService.upload(content, fullPath);
        //    }
        //}
        return null;
    }

    // @ApiOperation(value = "上传文件", notes = "上传文件")
    // @ResponseBody
    // @PostMapping("/upload")
    // public AjaxResult fileUpload(MultipartFile file, String content, String fullPath, String taskId, boolean flag) {
    //     // 存储过程的前缀名 proc_
    //     String firstName = fullPath.indexOf("_") != -1 ? fullPath.substring(0, fullPath.indexOf("_")).toLowerCase() : "";
    //     if (!file.isEmpty()) {
    //         if ((fileService.checkConflict(fullPath, Integer.parseInt(taskId)) || firstName.equals("proc")) && !flag) {
    //             return AjaxResult.error("上传的文件：" + fullPath + "存在冲突，是否继续上传");
    //         }
    //         return fileService.upload(file, fullPath);
    //     } else {
    //         return fileService.upload(content, fullPath);
    //     }
    // }

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
