package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * ClassName: TaskStatue
 * Description:
 * date: 2020/6/8 8:45
 *
 * @author CFG
 * @since JDK 1.8
 */
public class TaskStatus implements Serializable {


    private static final long serialVersionUID = -513119512795172680L;

    private Integer taskId; // 任务主键

    private Integer projectId; // 项目工程主键

    private String status; // 任务状态

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
