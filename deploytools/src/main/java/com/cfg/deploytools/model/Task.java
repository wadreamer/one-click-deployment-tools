package com.cfg.deploytools.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: Task
 * Description:
 * date: 2020/6/8 10:34
 *
 * @author CFG
 * @since JDK 1.8
 */
public class Task implements Serializable {

    private static final long serialVersionUID = -6537854290451339990L;

    private Integer taskId;

    private Integer projectId;

    private String name;

    private String status;

    private String openedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp openedDate;

    private String finishedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp finishedDate;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (status.equals("done")) {
            this.status = "待测试";
        }else {
            this.status = status;
        }
    }

    public String getOpenedBy() {
        return openedBy;
    }

    public void setOpenedBy(String openBy) {
        this.openedBy = openBy;
    }

    public Timestamp getOpenedDate() {
        return openedDate;
    }

    public void setOpenedDate(Timestamp openedDate) {
        this.openedDate = openedDate;
    }

    public String getFinishedBy() {
        return finishedBy;
    }

    public void setFinishedBy(String finishedBy) {
        this.finishedBy = finishedBy;
    }

    public Timestamp getFinishedDate() {
        return finishedDate;
    }

    public void setFinishedDate(Timestamp finishedDate) {
        this.finishedDate = finishedDate;
    }
}
