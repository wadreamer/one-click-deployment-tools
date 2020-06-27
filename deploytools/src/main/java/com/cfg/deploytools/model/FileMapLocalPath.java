package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * @ClassName FileMapLocalPath
 * @Description TODO
 * @Author bear
 * @Date 2020/6/27 21:22
 * @Version 1.0
 **/
public class FileMapLocalPath implements Serializable {

    private static final long serialVersionUID = -941496510828076243L;

    private String name;

    private String localPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocalPath() {
        return localPath;
    }

    public void setLocalPath(String localPath) {
        this.localPath = localPath;
    }
}
