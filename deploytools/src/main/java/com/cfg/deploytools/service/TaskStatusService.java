package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskStatusMapper;
import com.cfg.deploytools.model.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: TaskStatusService
 * Description:
 * date: 2020/6/8 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class TaskStatusService {

    @Autowired
    private TaskStatusMapper taskStatusMapper;
    
    /*
     * @Author wadreamer
     * @Description //TODO 任务测试状态的改变
     * @Date 18:07 2020/6/8
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult updateTaskStatus(TaskStatus taskStatus){
        if(taskStatusMapper.updateTaskStatusByTaskId(taskStatus) >0){
            return AjaxResult.success(200,"操作成功");
        }else{
            return AjaxResult.error("操作失败");
        }
    }
}
