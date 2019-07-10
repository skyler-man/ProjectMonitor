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
public class JiraInfoSchedule {

    @Autowired
    private JiraProjectRepository jiraProjectRepository;

    @Scheduled(cron = "0/10 * * * * *")
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

//        for(JiraStatuEnum jiraStatuEnum : JiraStatuEnum.values()){
//            String jql = "project = " + projectKey + " AND status = " + jiraStatuEnum.getCode() + " AND created >= 2018-07-01 AND created <= 2019-12-31 ORDER BY priority DESC, updated DESC";
//            int num = JiraUtil.search_jql1(jql);
//            JiraBug jiraBug = new JiraBug();
//            jiraBug.setNumber(num);
//            jiraBug.setStatusName(jiraStatuEnum.getCode());
//            jiraBug.setJiraProject(jiraProject);
//            jiraBugs.add(jiraBug);
//        }
//        jiraProject.getJiraBugs().addAll(jiraBugs);
//        jiraProject.getJiraMembers().addAll(jiraMembers);
        return jiraProject;
    }

}
