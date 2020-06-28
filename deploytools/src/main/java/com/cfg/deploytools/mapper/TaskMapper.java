package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.Task;
import com.cfg.deploytools.model.TaskStatus;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * ClassName: TaskMapper
 * Description:
 * date: 2020/6/8 15:58
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface TaskMapper {

    int updateStatusForUpload(@Param("taskId") Integer taskId, @Param("finishedBy") String finishedBy);

    List<Task> getTaskListByProjectId(@Param("projectId") Integer projectId);

    List<Task> getTaskListByProjectId2(@Param("projectId") Integer projectId);

    List<Task> searchByCondition(@Param("status") String status, @Param("projectId") Integer projectId, @Param("taskId") Integer taskId, @Param("start") Timestamp start, @Param("end") Timestamp end);

    Task queryProjectIdByTaskId(Integer taskId);
}
