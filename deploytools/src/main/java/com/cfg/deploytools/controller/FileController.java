package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * ClassName: FileController
 * Description:
 * date: 2020/6/8 11:43
 *
 * @author CFG
 * @since JDK 1.8
 */
@RequestMapping("/cfg_dt")
@Controller
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload")
    @ResponseBody
    public AjaxResult fileUpload(MultipartFile[] files){

        return null;
    }

    @PostMapping("/fileDownload/{fileId}")
    @ResponseBody
    public AjaxResult fileDownload(String fileId){

        return null;
    }

}
