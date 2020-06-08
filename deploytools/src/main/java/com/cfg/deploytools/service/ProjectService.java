package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.ProjectMapper;
import com.cfg.deploytools.model.Project;
import com.cfg.deploytools.model.TableParse;
import com.cfg.deploytools.model.Task;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: ProjectService
 * Description:
 * date: 2020/6/8 11:29
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    /*
     * @Author wadreamer
     * @Description //TODO 获取项目工程列表
     * @Date 14:37 2020/6/8
     * @Param []
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult getProjectList() {
        List<Project> projectList = projectMapper.getProjectList();

        if (projectList != null) {
            return AjaxResult.success(200, projectList);
        } else {
            return AjaxResult.error("操作失败，请稍后重试");
        }
    }

}
