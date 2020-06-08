package com.cfg.deploytools.mapper;

import com.cfg.deploytools.model.Project;
import com.cfg.deploytools.model.Task;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: ProjectMapper
 * Description:
 * date: 2020/6/8 14:22
 *
 * @author CFG
 * @since JDK 1.8
 */
public interface ProjectMapper {

    List<Project> getProjectList();

    // List<Task> getTaskListByProjectId(@Param("id") Integer id);

}
