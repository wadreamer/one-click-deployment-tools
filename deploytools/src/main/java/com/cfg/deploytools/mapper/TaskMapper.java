package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.Task;
import org.apache.ibatis.annotations.Param;

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
    List<Task> getTaskListByProjectId(@Param("projectId") Integer projectId);

    List<Task> getTaskListByProjectId2(@Param("projectId") Integer projectId);
}
