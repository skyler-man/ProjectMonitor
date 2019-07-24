package com.taocares.monitor.service.impl;

import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.ProjectRelationDto;
import com.taocares.monitor.entity.JenkinsJob;
import com.taocares.monitor.entity.JiraProject;
import com.taocares.monitor.entity.ProjectRelation;
import com.taocares.monitor.entity.SonarProject;
import com.taocares.monitor.repository.JenkinsJobRepository;
import com.taocares.monitor.repository.JiraProjectRepository;
import com.taocares.monitor.repository.ProjectRelationRepository;
import com.taocares.monitor.repository.SonarProjectRepository;
import com.taocares.monitor.service.IProjectRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:11 2019/7/23
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class ProjectRelationServiceImpl implements IProjectRelationService {

    @Autowired
    private JenkinsJobRepository jenkinsJobRepository;
    @Autowired
    private SonarProjectRepository sonarProjectRepository;
    @Autowired
    private JiraProjectRepository jiraProjectRepository;
    @Autowired
    private ProjectRelationRepository projectRelationRepository;

    @Override
    public void editProjectRelation(List<ProjectRelationDto> projectRelationDtos) {
        if(ListUtils.isEmpty(projectRelationDtos)){
            log.info("参数为空");
            return;
        }
        Map<String,JiraProject> jiraProjectMap = new HashMap<>();
        Map<String,SonarProject> sonarProjectMap = new HashMap<>();
        Map<String,JenkinsJob> jenkinsJobMap = new HashMap<>();
        List<JiraProject> jiraProjects = jiraProjectRepository.findAll();
        List<SonarProject> sonarProjects = sonarProjectRepository.findAll();
        List<JenkinsJob> jenkinsJobs = jenkinsJobRepository.findAll();
        if(ListUtils.isNotEmpty(jiraProjects)){
            for(JiraProject project : jiraProjects){
                jiraProjectMap.put(project.getId(),project);
            }
        }
        if(ListUtils.isNotEmpty(sonarProjects)){
            for(SonarProject project : sonarProjects){
                sonarProjectMap.put(project.getId(),project);
            }
        }
        if(ListUtils.isNotEmpty(jenkinsJobs)){
            for(JenkinsJob project : jenkinsJobs){
                jenkinsJobMap.put(project.getId(),project);
            }
        }
        List<ProjectRelation> projectRelations = new ArrayList<>();
        for(ProjectRelationDto projectRelationDto : projectRelationDtos){
            ProjectRelation projectRelation = new ProjectRelation();
            projectRelation.setJenkinsJob(jenkinsJobMap.get(projectRelationDto.getJenkinsId()));
            projectRelation.setJiraProject(jiraProjectMap.get(projectRelationDto.getJiraId()));
            projectRelation.setSonarProject(sonarProjectMap.get(projectRelationDto.getSonarId()));
            projectRelations.add(projectRelation);
        }
        if(ListUtils.isNotEmpty(projectRelations)){
            projectRelationRepository.deleteAll();
            projectRelationRepository.saveAll(projectRelations);
        }
    }
}
