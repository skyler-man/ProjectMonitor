package com.taocares.monitor.service.impl;

import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.JiraProjectNameDto;
import com.taocares.monitor.entity.JiraProject;
import com.taocares.monitor.repository.JiraProjectRepository;
import com.taocares.monitor.service.IJiraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:11 2019/7/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class JiraServiceImpl implements IJiraService{

    @Autowired
    private JiraProjectRepository jiraProjectRepository;

    @Override
    public List<JiraProjectNameDto> getJiraProjectNames() {
        List<JiraProject> jiraProjects = jiraProjectRepository.findAll();
        if(ListUtils.isEmpty(jiraProjects)){
            return new ArrayList<>();
        }
        List<JiraProjectNameDto> jiraProjectNameDtos = new ArrayList<>();
        for(JiraProject project : jiraProjects){
            JiraProjectNameDto jiraProjectNameDto = new JiraProjectNameDto();
            jiraProjectNameDto.setId(project.getId());
            jiraProjectNameDto.setProjectName(project.getProjectName());
            jiraProjectNameDtos.add(jiraProjectNameDto);
        }
        return jiraProjectNameDtos;
    }
}
