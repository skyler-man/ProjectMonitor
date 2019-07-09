package com.taocares.monitor.scheduler;

import com.atlassian.jira.rest.client.NullProgressMonitor;
import com.atlassian.jira.rest.client.domain.BasicProject;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.Project;
import com.taocares.monitor.common.JiraUtil;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.core.Is;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:36 2019/7/9
 */
@Slf4j
@Component
public class JiraInfoSchedule {

    @Scheduled(cron = "0/10 * * * * *")
    public void getJiraInfo() throws URISyntaxException, ExecutionException, InterruptedException {

        final NullProgressMonitor pm = new NullProgressMonitor();
        ArrayList<String> allProjects = JiraUtil.getAllProjects();
        for(String projectKey : allProjects){
            String jql = "project = " + projectKey + " AND created >= 2018-07-01 AND created <= 2019-12-31 ORDER BY priority DESC, updated DESC";
            ArrayList<String> s = JiraUtil.search_jql1(jql);
            for(String a : s){
                System.out.println(a);
            }
        }

    }

}
