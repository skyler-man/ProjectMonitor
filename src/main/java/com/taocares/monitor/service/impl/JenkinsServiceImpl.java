package com.taocares.monitor.service.impl;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.*;
import com.taocares.monitor.dto.JenkinsMonthAnalysisDto;
import com.taocares.monitor.dto.JenkinsMonthDetailDto;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.JenkinsJobNameDto;
import com.taocares.monitor.entity.JenkinsJob;
import com.taocares.monitor.entity.JenkinsJobBuildDetail;
import com.taocares.monitor.entity.JenkinsJobBuildInfo;
import com.taocares.monitor.entity.JenkinsJobBuildMonInfo;
import com.taocares.monitor.repository.JenkinsJobBuildDetailRepository;
import com.taocares.monitor.repository.JenkinsJobBuildInfoRepository;
import com.taocares.monitor.repository.JenkinsJobBuildMonInfoRepository;
import com.taocares.monitor.repository.JenkinsJobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private JenkinsJobBuildMonInfoRepository buildMonInfoRepository;

    @Value("${jenkins.url}")
    private String jenkinsUrl;

    @Override
    public void getJenkinsInfoForSchedule() throws URISyntaxException, IOException {
        URI serverURI = new URI("http://" + jenkinsUrl);
        JenkinsServer jenkins = new JenkinsServer(serverURI, "shaolin", "Sl123456");
        log.info("开始清除旧数据");
        this.buildMonInfoRepository.deleteAll();
        this.jobRepository.deleteAll();
        this.buildInfoRepository.deleteAll();
        this.buildDetailRepository.deleteAll();
        log.info("清除旧数据成功");
        log.info("开始向表中插入数据");
        saveAllProject(jenkins);
        log.info("向表中插入数据成功");

    }

    private void saveAllProject(JenkinsServer jenkins) throws IOException {
        Map<String, Job> jobMgr = jenkins.getJobs();
        List<JenkinsJob> jobList = new ArrayList<>();
        List<JenkinsJobBuildInfo> buildInfoList = new ArrayList<>();
        List<JenkinsJobBuildDetail> buildDetailList = new ArrayList<>();
        Iterator<String> it = jobMgr.keySet().iterator();
        Map<String, JenkinsJobBuildMonInfo> monInfoHashMap = new HashMap<>();
        while (it.hasNext()) {
            String key = it.next();
            // 工程主要信息
            JenkinsJob jenkinsJob = new JenkinsJob();
            Job job = jobMgr.get(key);
            jenkinsJob.setUrl(job.getUrl());
            jenkinsJob.setName(job.getName());

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
                long time = buildWithDetails.getTimestamp();
                JenkinsJobBuildDetail jenkinsJobBuildDetail = new JenkinsJobBuildDetail();
                jenkinsJobBuildDetail.setJenkinsJob(jenkinsJob);
                jenkinsJobBuildDetail.setNumber(String.valueOf(buildWithDetails.getNumber()));
                jenkinsJobBuildDetail.setUrl(buildWithDetails.getUrl());
                jenkinsJobBuildDetail.setResult(buildWithDetails.getResult().name());
                jenkinsJobBuildDetail.setBuildTime(new Date(time));
                buildDetailList.add(jenkinsJobBuildDetail);
                String monthTime = new SimpleDateFormat("yyyy-MM").format(new Date(time));
                String monthKey = jenkinsJob.getName() + monthTime;
                if (monInfoHashMap.get(monthKey) == null) {
                    JenkinsJobBuildMonInfo jenkinsJobBuildMonInfo = new JenkinsJobBuildMonInfo();
                    jenkinsJobBuildMonInfo.setAllNumber(1);
                    jenkinsJobBuildMonInfo.setInfoTime(monthTime);
                    jenkinsJobBuildMonInfo.setJenkinsJob(jenkinsJob);
                    if (buildWithDetails.getResult() == BuildResult.SUCCESS) {
                        jenkinsJobBuildMonInfo.setSuccessNumber(1);
                    } else {
                        jenkinsJobBuildMonInfo.setSuccessNumber(0);
                    }
                    monInfoHashMap.put(monthKey, jenkinsJobBuildMonInfo);
                } else {
                    JenkinsJobBuildMonInfo jenkinsJobBuildMonInfo = monInfoHashMap.get(monthKey);
                    jenkinsJobBuildMonInfo.setAllNumber(jenkinsJobBuildMonInfo.getAllNumber() + 1);
                    if (buildWithDetails.getResult() == BuildResult.SUCCESS) {
                        jenkinsJobBuildMonInfo.setSuccessNumber(jenkinsJobBuildMonInfo.getSuccessNumber() + 1);
                    }
                }
            }
            jenkinsJob.setJenkinsJobBuildInfo(jenkinsJobBuildInfo);
            jenkinsJob.getJenkinsJobBuildDetailList().addAll(buildDetailList);
            jobList.add(jenkinsJob);
        }
        List<JenkinsJobBuildMonInfo> jenkinsJobBuildMonInfoList = new ArrayList<>();
        Iterator<String> monthIt = monInfoHashMap.keySet().iterator();
        while (monthIt.hasNext()) {
            String key = monthIt.next();
            jenkinsJobBuildMonInfoList.add(monInfoHashMap.get(key));
        }
        this.jobRepository.saveAll(jobList);
        this.buildMonInfoRepository.saveAll(jenkinsJobBuildMonInfoList);
    }

    @Override
    public List<JenkinsMonthAnalysisDto> getJenkinsMonthAnalysisDtoInThisYear(Integer size) {
        if (size == null || size == 0) {
            size = 70;
        }
        String nowYearJan = new SimpleDateFormat("yyyy").format(new Date()) + "-01";
        List<String> mostJobIdList = this.buildMonInfoRepository.findMostBuildJodId(nowYearJan);
        List<JenkinsMonthAnalysisDto> jenkinsMonthAnalysisDtoList = new ArrayList<>();
        for (String jobId : mostJobIdList) {
            JenkinsMonthAnalysisDto jenkinsMonthAnalysisDto = new JenkinsMonthAnalysisDto();
            JenkinsJob jenkinsJob = this.jobRepository.getOne(jobId);
            jenkinsMonthAnalysisDto.setJobName(jenkinsJob.getName());
            List<JenkinsJobBuildMonInfo> jenkinsJobBuildMonInfoList = this.buildMonInfoRepository.findBuildInfoByJobId(jobId);
            List<JenkinsMonthDetailDto> jenkinsMonthDetailList = new ArrayList<>();
            for (JenkinsJobBuildMonInfo jenkinsJobBuildMonInfo : jenkinsJobBuildMonInfoList) {
                JenkinsMonthDetailDto jenkinsMonthDetailDto = new JenkinsMonthDetailDto();
                jenkinsMonthDetailDto.setAllBuildNumber(jenkinsJobBuildMonInfo.getAllNumber());
                jenkinsMonthDetailDto.setSuccessBuildNumber(jenkinsJobBuildMonInfo.getSuccessNumber());
                jenkinsMonthDetailDto.setMonth(jenkinsJobBuildMonInfo.getInfoTime());
                if (jenkinsMonthDetailDto.getAllBuildNumber() != 0) {
                    BigDecimal b = new BigDecimal(jenkinsMonthDetailDto.getSuccessBuildNumber() / jenkinsMonthDetailDto.getAllBuildNumber());
                    jenkinsMonthDetailDto.setRate(b.setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue());
                } else {
                    jenkinsMonthDetailDto.setRate(0);
                }
                jenkinsMonthDetailList.add(jenkinsMonthDetailDto);
            }
            jenkinsMonthAnalysisDto.setJenkinsMonthDetailList(jenkinsMonthDetailList);
            jenkinsMonthAnalysisDtoList.add(jenkinsMonthAnalysisDto);
        }
        return jenkinsMonthAnalysisDtoList;
    }

    @Override
    public List<JenkinsJobNameDto> getJenkinsJobNames() {
        List<JenkinsJob> jenkinsJobs = jobRepository.findAll();
        if(ListUtils.isEmpty(jenkinsJobs)){
            return new ArrayList<>();
        }
        List<JenkinsJobNameDto> jenkinsJobNameDtos = new ArrayList<>();
        for(JenkinsJob jenkinsJob : jenkinsJobs){
            JenkinsJobNameDto jenkinsJobNameDto = new JenkinsJobNameDto();
            jenkinsJobNameDto.setId(jenkinsJob.getId());
            jenkinsJobNameDto.setName(jenkinsJob.getName());
            jenkinsJobNameDtos.add(jenkinsJobNameDto);
        }
        return jenkinsJobNameDtos;
    }
}
