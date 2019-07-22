package com.taocares.monitor.scheduler;

import com.atlassian.jira.rest.client.domain.Project;
import com.taocares.monitor.common.JiraUtil;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.entity.JiraBug;
import com.taocares.monitor.entity.JiraMember;
import com.taocares.monitor.entity.JiraProject;
import com.taocares.monitor.repository.JiraProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:36 2019/7/9
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class JiraInfoSchedule {

    @Autowired
    private JiraProjectRepository jiraProjectRepository;

    /**
     * @Description: 第一次延迟一秒后执行，每60分钟执行一次,
     * @Author: LiuYiQiang
     * @Date: 8:37 2019/7/10
     */
    @Scheduled(initialDelay=1000, fixedRate=60 * 60 * 1000)
    public void getJiraInfo() throws URISyntaxException, ExecutionException, InterruptedException {
        Long startTime = System.currentTimeMillis();
        ArrayList<String> allProjects = JiraUtil.getAllProjects();
        List<JiraProject> jiraProjects = new ArrayList<>();
        for(String projectKey : allProjects){
            Project project = JiraUtil.getProject(projectKey);
            JiraProject jiraProject = dealWithJiraInfo(project,projectKey);
            if(jiraProject != null){
                jiraProjects.add(jiraProject);
            }
        }
        if(ListUtils.isNotEmpty(jiraProjects)){
            jiraProjectRepository.deleteAll();
            jiraProjectRepository.saveAll(jiraProjects);
        }
        Long endTime = System.currentTimeMillis();
        log.info("Jira数据统计完成，总耗时：{}",endTime - startTime);
    }

    private JiraProject dealWithJiraInfo(Project project,String projectKey) throws ExecutionException, InterruptedException {
        if(project == null){
            return null;
        }
        JiraProject jiraProject = new JiraProject();
        jiraProject.setProjectName(project.getName());
        List<JiraMember> jiraMembers = new ArrayList<>();
        JiraMember jiraMember = new JiraMember();
        jiraMember.setJiraProject(jiraProject);
        jiraMember.setLeader(true);
        jiraMember.setName(project.getLead() != null ? project.getLead().getDisplayName() : null);
        jiraMembers.add(jiraMember);
        List<JiraBug> jiraBugs = new ArrayList<>();

        String jql = "project = " + projectKey + " AND created >= 2018-07-01";
        Map<String,Integer> jiraBugInfo = JiraUtil.search_jql(jql,projectKey);

        for(Map.Entry<String,Integer> entry : jiraBugInfo.entrySet()){
            JiraBug jiraBug = new JiraBug();
            jiraBug.setNumber(entry.getValue());
            jiraBug.setStatusName(entry.getKey());
            jiraBug.setJiraProject(jiraProject);
            jiraBugs.add(jiraBug);
        }
        jiraProject.getJiraBugs().addAll(jiraBugs);
        jiraProject.getJiraMembers().addAll(jiraMembers);

        return jiraProject;
    }

}
