package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * ClassName: TaskFile
 * Description:
 * date: 2020/6/8 8:46
 *
 * @author CFG
 * @since JDK 1.8
 */
public class TaskFile implements Serializable {


    private static final long serialVersionUID = -8183054984593426116L;

    private Integer taskId; // 任务主键

    private String fullPath; // 文件全路径

    private Integer fileId; // 文件主键

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }
}
