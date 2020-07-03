package com.cfg.deploytools.model;

import java.io.Serializable;

/**
 * @ClassName SQLFile
 * @Description TODO
 * @Author bear
 * @Date 2020/6/27 17:46
 * @Version 1.0
 **/
public class SQLFile implements Serializable {
    private static final long serialVersionUID = -8216534303180232035L;

    private String name;

    private String path;

    private String content;

    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
