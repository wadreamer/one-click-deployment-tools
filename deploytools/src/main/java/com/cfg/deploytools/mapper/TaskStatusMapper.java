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

    /*
     * @Author wadreamer
     * @Description //TODO 根据任务主键，更新任务状态
     * @Date 10:10 2020/6/8
     * @Param [taskId, status]
     * @return int 表示受影响的数据库记录数
     **/
    int updateTaskStatusByTaskId(TaskStatus taskStatus);

}
