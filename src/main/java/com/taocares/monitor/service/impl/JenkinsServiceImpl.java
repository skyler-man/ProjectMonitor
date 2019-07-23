package com.taocares.monitor.service.impl;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.Job;
import com.offbytwo.jenkins.model.JobWithDetails;
import com.taocares.monitor.entity.JenkinsJob;
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
    private JenkinsJobRepository jenkinsJobRepository;

    @Value("${jenkins.url}")
    private String jenkinsUrl;

    @Override
    public void getJenkinsInfoForSchedule() throws URISyntaxException, IOException {
        URI serverURI = new URI("http://" + jenkinsUrl);
        JenkinsServer jenkins = new JenkinsServer(serverURI, "shaolin", "Sl123456");
        saveAllProject(jenkins);
    }

    public void saveAllProject(JenkinsServer jenkins) throws IOException {
        Map<String, Job> jobMgr = jenkins.getJobs();
        List<JenkinsJob> jenkinsJobList = new ArrayList<>();
        Iterator<String> it = jobMgr.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();
            Job job = jobMgr.get(key);
            JobWithDetails details = job.details();
            if ("tao-naoms-publish-repo".equals(job.getName())){
                System.out.println("a");
            }
        }
//        this.jenkinsJobRepository.saveAll(jenkinsJobList);
    }
}
