package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.TaskMapper;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.model.Task;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
        PageHelper.startPage(tableParse.getPageNum(), tableParse.getPageSize());
        List<Task> taskList = taskMapper.getTaskListByProjectId(projectId);

        if (taskList != null) {
            PageInfo<Task> pageInfo = new PageInfo<Task>(taskList);
            return AjaxResult.success(200, pageInfo);
        } else {
            return AjaxResult.error("操作失败，请稍后重试");
        }
    }

    /*
     * @Author wadreamer
     * @Description //TODO 搜索，利用 SQL 的 union，求两个表的并集
     * @Date 16:56 2020/6/22
     * @Param [tableParse, status, projectId, taskId, start, end]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult searchByCondition(TableParse tableParse, String status,String projectId, String taskId, Timestamp start, Timestamp end) {

        if(projectId == null || projectId.equals("")){
            return AjaxResult.error("请先选择项目工程");
        }

        PageHelper.startPage(tableParse.getPageNum(), tableParse.getPageSize());
        List<Task> taskList = taskMapper.searchByCondition(status, Integer.parseInt(projectId), Integer.parseInt(taskId), start, end);
        if (taskList != null) {
            PageInfo<Task> pageInfo = new PageInfo<Task>(taskList);
            return AjaxResult.success(200, pageInfo);
        } else {
            return AjaxResult.error("操作失败，请稍后重试");
        }
    }
}
