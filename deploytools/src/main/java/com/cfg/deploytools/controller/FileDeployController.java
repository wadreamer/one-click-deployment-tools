package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.service.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.OutputStream;

@CrossOrigin
@Controller
@RequestMapping("/cfg_dt")
public class FileDeployController {

    @Autowired
    private FileService fileService;

    @RequestMapping("/deployFile")
    public AjaxResult deployFile(int fileId){
        File deployFile = fileService.getFileById(fileId);
        return deployFile != null ? AjaxResult.success(200, deployFile) : AjaxResult.error("操作失败，请稍后重试");
    }
}
