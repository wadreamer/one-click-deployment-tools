package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.TaskFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: FileMapper
 * Description: TODO 上传文件
 * date: 2020/6/8 9:22
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface FileMapper {

    int deleteFileByFileIdArray(@Param("taskFiles") List<TaskFile> taskFiles);

    File queryFileNewestByFullPath(TaskFile taskFile);

    List<File> queryCurrentAllFileByTaskId(int taskId);

    List<File> queryFileHistoryByFullPath(int fileId);

    int insertSelectiveFile(File file);
}
