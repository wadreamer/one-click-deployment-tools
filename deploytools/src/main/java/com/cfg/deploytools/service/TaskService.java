package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskMapper;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.model.Task;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: TaskService
 * Description:
 * date: 2020/6/8 11:24
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;

    /*
     * @Author wadreamer
     * @Description //TODO 根据项目工程主键，获取该项目工程下的任务列表
     * @Date 16:49 2020/6/8
     * @Param [tableParse, projectId]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult getTaskListByProjectId(TableParse tableParse, int projectId) {
        List<Task> taskList = taskMapper.getTaskListByProjectId(projectId);
        List<Task> taskList2 = taskMapper.getTaskListByProjectId2(projectId);
        taskList.addAll(taskList2);
        System.out.println(taskList.size());

        if (taskList != null) {
            PageHelper.startPage(tableParse.getPageNum(), tableParse.getPageSize());
            PageInfo<Task> pageInfo = new PageInfo<>(taskList);
            return AjaxResult.success(200, pageInfo);
        } else {
            return AjaxResult.error("操作失败，请稍后重试");
        }
    }
}
