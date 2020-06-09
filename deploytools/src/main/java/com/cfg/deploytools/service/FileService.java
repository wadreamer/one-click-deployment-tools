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

    public AjaxResult upload(MultipartFile file, String fullPath) {
        File f = null;
        try {
            // 读取文件内容
            String content = FileUtils.readFileByChars(file.getInputStream());
            f = new File();
            // 设置文件全路径
            f.setFullPath(fullPath);
            // 获取文件后缀名
            String suffixName = fullPath.substring(fullPath.lastIndexOf(".")).toLowerCase();
            // 判断文件类型
            if (suffixName.equals(".sql")) {
                f.setSqlData(content);
                f.setType(1);
            } else {
                f.setFileData(content);
                f.setType(0);
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

}
