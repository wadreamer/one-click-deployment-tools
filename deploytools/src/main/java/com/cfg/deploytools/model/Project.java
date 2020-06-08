package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * ClassName: Project
 * Description:
 * date: 2020/6/8 11:09
 *
 * @author CFG
 * @since JDK 1.8
 */
public class Project implements Serializable {

    private static final long serialVersionUID = -3955537104443305360L;

    private Integer projectId;

    private String projectName;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
