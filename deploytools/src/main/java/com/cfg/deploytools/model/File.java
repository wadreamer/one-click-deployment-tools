package com.cfg.deploytools.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ClassName: File
 * Description:
 * date: 2020/6/8 8:46
 *
 * @author CFG
 * @since JDK 1.8
 */
public class File implements Serializable {

    private static final long serialVersionUID = -5460382475282217061L; // 序列化ID，后续用于 redis 缓存对象

    private Integer fileId; // 文件主键

    private String fullPath; // 文件全路径

    private String fileData; // 代码二进制文件

    private String sqlData; // SQL内容

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp uploadTime; // 文件提交时间

    private String type; // 文件类型 0表示文件 1表示SQL内容

    public File() {
    }

    public File(String fullPath, String fileData, String sqlData, String type) {
        this.fullPath = fullPath;
        this.fileData = fileData;
        this.sqlData = sqlData;
        this.type = type;
    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileData() {
        return fileData;
    }

    public void setFileData(String fileData) {
        this.fileData = fileData;
    }

    public String getSqlData() {
        return sqlData;
    }

    public void setSqlData(String sqlData) {
        this.sqlData = sqlData;
    }

    public Timestamp getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Timestamp updateTime) {
        this.uploadTime = updateTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
