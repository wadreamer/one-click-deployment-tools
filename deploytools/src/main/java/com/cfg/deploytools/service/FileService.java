package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.FileMapper;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: FileService
 * Description:
 * date: 2020/6/8 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    /*
     * @Author wadreamer
     * @Description //TODO 文件上传
     * @Date 9:23 2020/6/10
     * @Param [file, fullPath]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult upload(Object file, String fullPath) {
        File f = null;
        String content = "";
        try {
            // 读取文件内容
            if (file instanceof String) {
                content = (String) file;
            } else {
                content = FileUtils.readFileByChars(((MultipartFile) file).getInputStream());
            }
            // 自定义的 File 类
            f = new File();
            // 设置文件全路径
            f.setFullPath(fullPath);
            // 获取文件后缀名
            String suffixName = fullPath.substring(fullPath.lastIndexOf(".")).toLowerCase();

            // 判断文件类型
            if (suffixName.equals(".sql")) {
                f.setSqlData(content);
                f.setType("sql");
            } else {
                f.setFileData(content);
                f.setType("file");
            }
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }

        int result = fileMapper.insertSelectiveFile(f);

        return result > 0 ? AjaxResult.success(200, "上传成功") : AjaxResult.error("上传失败，请稍后重试");

    }

    /*
     * @Author wadreamer
     * @Description //TODO 删除 cfg_file 的多条记录 ok
     * @Date 10:35 2020/6/9
     * @Param [fileId]
     * @return int
     **/
    public int deleteFile(List<TaskFile> taskFileList) {
        return fileMapper.deleteFileByFileIdArray(taskFileList);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据文件的全路径，获取当前任务正在使用的最新文件 ok
     * @Date 14:41 2020/6/9
     * @Param [taskFileList]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getFileListNewest(List<TaskFile> taskFileList) {
        List<File> resultList = new ArrayList<>();
        for (TaskFile tf : taskFileList) {
            File f = fileMapper.queryFileNewestByFullPath(tf);
            if (f != null) {
                resultList.add(f);
            }
        }
        return resultList;
    }


    public File getFileNewest(TaskFile taskFile) {
        File file = fileMapper.queryFileNewestByFullPath(taskFile);
        return file;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取当前任务正在使用的文件信息 ok
     * @Date 16:51 2020/6/9
     * @Param [taskId]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getCurrentFileListByTaskId(int taskId) {
        return fileMapper.queryCurrentAllFileByTaskId(taskId);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取某个文件的历史版本文件 ok
     * @Date 16:50 2020/6/9
     * @Param [fileId]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getFileHistory(int fileId) {
        return fileMapper.queryFileHistoryByFullPath(fileId);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取文件内容 ok
     * @Date 10:25 2020/6/11
     * @Param [taskFile]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult getFileContent(TaskFile taskFile) {
        File file = fileMapper.queryFileContent(taskFile);
        return file != null ? AjaxResult.success(200, file) : AjaxResult.error("操作失败，请稍后重试");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据 文件全路径 或 任务主键 判断上传的文件是否存在冲突 ok
     * @Date 10:23 2020/6/11
     * @Param [fullPath, taskId]
     * @return boolean
     **/
    public boolean checkConflict(String fullPath, int taskId) {
        if (fileMapper.checkConflictWithTime(fullPath, taskId).size() > 0 ||
                fileMapper.checkConflictWithTaskStatus(fullPath).size() > 0) {
            return true;
        } else {
            return false;
        }
    }

}
