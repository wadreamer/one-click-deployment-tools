package com.cfg.deploytools.controller;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.service.FileService;
import com.sun.org.apache.xpath.internal.operations.Mult;
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
    // @ResponseBody
    // @PostMapping("/upload")
    // public AjaxResult fileUpload(MultipartFile file,String fullPath) {
    //     try {
    //         if(!file.isEmpty()){
    //             int result = fileService.insertFile(file,fullPath);
    //             return result > 0 ? AjaxResult.success(200,"上传成功") :
    //         }
    //     } catch (Exception e) {
    //         return AjaxResult.error(e.getMessage());
    //     }
    //
    //     return AjaxResult.error("上传失败，请稍后重试");
    // }

    /*
     * @Author wadreamer
     * @Description //TODO 多文件下载
     * @Date 11:50 2020/6/8
     * @Param [fileId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @ResponseBody
    @PostMapping("/download/{fileId}")
    public AjaxResult fileDownload(String fileId) {

        return null;
    }

}
