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

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
