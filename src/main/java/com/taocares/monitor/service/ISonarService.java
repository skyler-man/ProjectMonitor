package com.taocares.monitor.service;

import com.taocares.monitor.dto.JiraProjectDto;
import com.taocares.monitor.dto.JiraProjectNameDto;
import com.taocares.monitor.dto.SonarProjectDto;
import com.taocares.monitor.dto.SonarProjectNameDto;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:10 2019/7/23
 */
public interface ISonarService {

    /**
     * @Description: 获取所有Sonar项目名称
     * @Author: LiuYiQiang
     * @Date: 8:15 2019/7/23
     */
    List<SonarProjectNameDto> getSonarProjectNames();

    /**
     * @Description: 获取Soanr所有项目指标
     * @Author: LiuYiQiang
     * @Date: 14:58 2019/7/23
     */
    List<SonarProjectDto> getSonarProjects();

}
