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

    private String taskStatus; // 任务状态

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }
}
