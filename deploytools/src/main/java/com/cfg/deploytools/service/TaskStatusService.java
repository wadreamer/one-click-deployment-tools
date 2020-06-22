package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskStatusMapper;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.model.TaskStatus;
import com.cfg.deploytools.shiro.utils.ShiroUtils;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
    private TaskFileService taskFileService;

    @Autowired
    private FileService fileService;

    @Autowired
    private TaskStatusMapper taskStatusMapper;

    /*
     * @Author wadreamer
     * @Description //TODO 根据任务主键，修改任务测试状态
     * @Date 18:07 2020/6/8
     * @Param [taskStatus]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @Transactional
    public AjaxResult updateTaskStatusForTest(TaskStatus taskStatus, boolean flag) {

        String operPerson = ShiroUtils.getUser().getAccount();

        if (taskStatusMapper.updateTaskStatusForTesting(taskStatus) > 0 && !flag) {
            return AjaxResult.success(200, "操作成功");
        }

        if (taskStatusMapper.updateTaskStatusForPass(taskStatus, operPerson) > 0 && flag) {
            return AjaxResult.success(200, "操作成功");
        }

        return AjaxResult.error("操作失败，请稍后重试");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 测试不通过，驳回任务
     * @Date 9:37 2020/6/9
     * @Param []
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    @Transactional
    public AjaxResult updateTaskStatusFoeReject(TaskStatus taskStatus) {
        // 1. 找出该任务当前在使用的所有任务文件实体
        // 2. 删除 cfg_file 中的所有文件
        // 3. 重新在 cfg_file 中找到最新的文件
        // 1. 修改 zt_task 中的 任务状态 为 doing
        // 2. 修改 指派人 为 任务提交人
        // 3. 清空 任务完成人 任务完成时间

        List<TaskFile> taskFileList = taskFileService.getTaskFileListByTaskId(taskStatus.getTaskId()); // 获取当前任务正在使用的任务文件实体

        int result_del_file = fileService.deleteFile(taskFileList); // 删除正在使用的文件

        int result_del_taskstatus = taskStatusMapper.deleteTaskStatusForReject(taskStatus.getTaskId()); // 删除 cfg_taskstatus 表中的记录

        int result_del_taskfile = taskFileService.deleteTaskFile(taskFileList);

        int result_zt_task = taskStatusMapper.updateTaskStatusForReject(taskStatus); // 修改禅道数据库中的任务状态

        return (result_del_file > 0 && result_del_taskstatus > 0 && result_del_taskfile > 0 && result_zt_task > 0) ?
                AjaxResult.success(200, "操作成功") : AjaxResult.error("操作失败");
    }

}
