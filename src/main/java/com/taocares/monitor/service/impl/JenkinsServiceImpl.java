package com.taocares.monitor.service.impl;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Build;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.taocares.monitor.entity.JenkinsJob;
import com.taocares.monitor.entity.JenkinsJobBuildDetail;
import com.taocares.monitor.entity.JenkinsJobBuildInfo;
import com.taocares.monitor.repository.JenkinsJobBuildDetailRepository;
import com.taocares.monitor.repository.JenkinsJobBuildInfoRepository;
import com.taocares.monitor.repository.JenkinsJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * jenkins 对应的serviceImpl
 *
 * @author lin
 * @since 2019年7月23日
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class JenkinsServiceImpl implements com.taocares.monitor.service.IJenkinsService {

    @Autowired
    private JenkinsJobRepository jobRepository;

    @Autowired
    private JenkinsJobBuildInfoRepository buildInfoRepository;

    @Autowired
    private JenkinsJobBuildDetailRepository buildDetailRepository;

    @Value("${jenkins.url}")
    private String jenkinsUrl;

    @Override
    public void getJenkinsInfoForSchedule() throws URISyntaxException, IOException {
        URI serverURI = new URI("http://" + jenkinsUrl);
        JenkinsServer jenkins = new JenkinsServer(serverURI, "shaolin", "Sl123456");
        jobRepository.deleteAll();
        buildInfoRepository.deleteAll();
        buildDetailRepository.deleteAll();
        saveAllProject(jenkins);
    }

    public void saveAllProject(JenkinsServer jenkins) throws IOException {
        Map<String, Job> jobMgr = jenkins.getJobs();
        List<JenkinsJob> jobList = new ArrayList<>();
        List<JenkinsJobBuildInfo> buildInfoList = new ArrayList<>();
        List<JenkinsJobBuildDetail> buildDetailList = new ArrayList<>();
        Iterator<String> it = jobMgr.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            // 工程主要信息
            JenkinsJob jenkinsJob = new JenkinsJob();
            Job job = jobMgr.get(key);
            jenkinsJob.setUrl(job.getUrl());
            jenkinsJob.setName(job.getName());
            jobList.add(jenkinsJob);
            JobWithDetails details = job.details();
            // 工程构建信息(最后一次，最后一次失败，最后一次成功)
            JenkinsJobBuildInfo jenkinsJobBuildInfo = new JenkinsJobBuildInfo();
            jenkinsJobBuildInfo.setJenkinsJob(jenkinsJob);
            jenkinsJobBuildInfo.setLastBuildNum(String.valueOf(details.getLastBuild().getNumber()));
            jenkinsJobBuildInfo.setLastBuildUrl(details.getLastBuild().getUrl());
            jenkinsJobBuildInfo.setLastCompletedBuildNum(String.valueOf(details.getLastCompletedBuild().getNumber()));
            jenkinsJobBuildInfo.setLastCompletedBuildNumUrl(details.getLastCompletedBuild().getUrl());
            jenkinsJobBuildInfo.setLastFailedBuildNum(String.valueOf(details.getLastFailedBuild().getNumber()));
            jenkinsJobBuildInfo.setLastFailedBuildUrl(details.getLastFailedBuild().getUrl());
            jenkinsJobBuildInfo.setLastSuccessfulBuildName(String.valueOf(details.getLastSuccessfulBuild().getNumber()));
            jenkinsJobBuildInfo.setLastSuccessfulBuildUrl(details.getLastSuccessfulBuild().getUrl());
            buildInfoList.add(jenkinsJobBuildInfo);
            // 工程构建逐条信息
            List<Build> builds = details.getBuilds();
            for (Build build : builds) {
                BuildWithDetails buildWithDetails = build.details();
                JenkinsJobBuildDetail jenkinsJobBuildDetail = new JenkinsJobBuildDetail();
                jenkinsJobBuildDetail.setJenkinsJob(jenkinsJob);
                jenkinsJobBuildDetail.setNumber(String.valueOf(buildWithDetails.getNumber()));
                jenkinsJobBuildDetail.setUrl(buildWithDetails.getUrl());
                jenkinsJobBuildDetail.setResult(buildWithDetails.getResult().name());
                buildDetailList.add(jenkinsJobBuildDetail);
            }
        }
        this.jobRepository.saveAll(jobList);
        this.buildInfoRepository.saveAll(buildInfoList);
        this.buildDetailRepository.saveAll(buildDetailList);
    }
}
