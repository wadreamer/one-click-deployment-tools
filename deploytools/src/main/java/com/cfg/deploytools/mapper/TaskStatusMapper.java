package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.TaskStatus;
import org.apache.ibatis.annotations.Param;

/**
 * ClassName: TaskStatusMapper
 * Description: TODO 修改任务状态
 * date: 2020/6/8 9:22
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface TaskStatusMapper {

    int updateTaskStatusForTesting(TaskStatus taskStatus);

    int insertTaskStatusForUpload(TaskStatus taskStatus);

    int updateTaskStatusForPass(@Param("taskStatus") TaskStatus taskStatus, @Param("account") String account);

    int updateTaskStatusForReject(TaskStatus taskStatus);

    int deleteTaskStatusForReject(@Param("taskId") int taskId);

}
