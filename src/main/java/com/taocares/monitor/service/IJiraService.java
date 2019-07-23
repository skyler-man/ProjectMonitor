package com.taocares.monitor.service;

import com.taocares.monitor.dto.JiraProjectDto;
import com.taocares.monitor.dto.JiraProjectNameDto;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:10 2019/7/23
 */
public interface IJiraService {

    /**
     * @Description: 获取所有Jira项目名称
     * @Author: LiuYiQiang
     * @Date: 8:15 2019/7/23
     */
    List<JiraProjectNameDto> getJiraProjectNames();

    List<JiraProjectDto> getJiraProjects();

}
