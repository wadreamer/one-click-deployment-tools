package com.cfg.deploytools.service;

import com.cfg.deploytools.mapper.TaskFileMapper;
import com.cfg.deploytools.model.TaskFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: TaskFileService
 * Description:
 * date: 2020/6/8 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class TaskFileService {

    @Autowired
    private TaskFileMapper taskFileMapper;

    /*
     * @Author wadreamer
     * @Description //TODO 从 cfg_taskFile 表中获取当前正在使用的所有任务文件
     * @Date 13:50 2020/6/9
     * @Param [taskId]
     * @return java.util.List<com.cfg.deploytools.model.TaskFile>
     **/
    public List<TaskFile> getTaskFileListByTaskId(int taskId){
        return taskFileMapper.getTaskFileListByTaskId(taskId);
    }

    @Transactional
    public int updateTaskFile(TaskFile taskFile){
        return taskFileMapper.updateTaskFile(taskFile);
    }

    @Transactional
    public int deleteTaskFile(List<TaskFile> taskFile){
        return taskFileMapper.deleteTaskFile(taskFile);
    }

}
