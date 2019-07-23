package com.taocares.monitor.service.impl;

import com.taocares.commons.beans.BeanUtils;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.JiraBugDto;
import com.taocares.monitor.dto.JiraMemberDto;
import com.taocares.monitor.dto.JiraProjectDto;
import com.taocares.monitor.dto.JiraProjectNameDto;
import com.taocares.monitor.entity.JiraBug;
import com.taocares.monitor.entity.JiraMember;
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

    @Override
    public List<JiraProjectDto> getJiraProjects() {
        List<JiraProject> jiraProjects = jiraProjectRepository.findAll();
        if(ListUtils.isEmpty(jiraProjects)){
            return new ArrayList<>();
        }
        List<JiraProjectDto> jiraProjectDtos = new ArrayList<>();
        for(JiraProject jiraProject : jiraProjects){
            JiraProjectDto jiraProjectDto = new JiraProjectDto();
            jiraProjectDto.setId(jiraProject.getId());
            jiraProjectDto.setProjectName(jiraProject.getProjectName());
            //组装JiraMemberDto
            if(ListUtils.isNotEmpty(jiraProject.getJiraMembers())){
                List<JiraMemberDto> jiraMemberDtos = new ArrayList<>();
                for(JiraMember jiraMember : jiraProject.getJiraMembers()){
                    JiraMemberDto jiraMemberDto = new JiraMemberDto();
                    jiraMemberDto.setId(jiraMember.getId());
                    jiraMemberDto.setName(jiraMember.getName());
                    jiraMemberDto.setLeader(jiraMember.isLeader());
                    jiraMemberDto.setJiraProjectId(jiraProject.getId());
                    jiraMemberDtos.add(jiraMemberDto);
                }
                jiraProjectDto.setJiraMemberDtos(jiraMemberDtos);
            }
            //组装JiraBug
            if(ListUtils.isNotEmpty(jiraProject.getJiraBugs())){
                List<JiraBugDto> jiraBugDtos = new ArrayList<>();
                for(JiraBug jiraBug : jiraProject.getJiraBugs()){
                    JiraBugDto jiraBugDto = new JiraBugDto();
                    jiraBugDto.setId(jiraBug.getId());
                    jiraBugDto.setStatusName(jiraBug.getStatusName());
                    jiraBugDto.setNumber(jiraBug.getNumber());
                    jiraBugDto.setJiraProjectId(jiraProject.getId());
                    jiraBugDtos.add(jiraBugDto);
                }
                jiraProjectDto.setJiraBugDtos(jiraBugDtos);
            }
            jiraProjectDtos.add(jiraProjectDto);
        }
        return jiraProjectDtos;
    }
}
