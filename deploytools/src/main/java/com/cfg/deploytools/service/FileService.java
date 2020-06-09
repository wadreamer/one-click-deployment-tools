package com.cfg.deploytools.service;

import com.cfg.deploytools.mapper.FileMapper;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
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
     * @Description //TODO 根据文件的全路径，获取当前任务正在使用的最新文件
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

    public File getFileNewest(TaskFile taskFile){
        File file = fileMapper.queryFileNewestByFullPath(taskFile);
        return file;
    }

    public List<File> getCurrentFileListByTaskId(int taskId){
        return fileMapper.queryCurrentAllFileByTaskId(taskId);
    }

}
