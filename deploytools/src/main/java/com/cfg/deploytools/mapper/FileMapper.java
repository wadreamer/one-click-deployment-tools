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

    List<File> queryFileHistoryByFullPath(String fullPath);

    // int insertSelectiveFile(File file);
    int insertSelectiveFile(List<File> list);

    File queryFileContent(TaskFile taskFile);

    List<File> checkConflictWithTime(@Param("fullPath") String fullPath, @Param("taskId") int taskId); // 检测时间上的冲突

    List<File> checkConflictWithTaskStatus(@Param("fullPath") String fullPath); // 检测任务状态上的冲突

    File queryFileById(int fileId);

}
