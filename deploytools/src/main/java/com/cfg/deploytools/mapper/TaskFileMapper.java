package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.TaskFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: TaskFileMapper
 * Description: TODO
 * date: 2020/6/8 9:22
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface TaskFileMapper {

    List<TaskFile> getTaskFileListByTaskId(@Param("taskId") Integer taskId);

    int updateTaskFile(TaskFile taskFile);

    int deleteTaskFile(@Param("taskFiles") List<TaskFile> taskFile);

}
