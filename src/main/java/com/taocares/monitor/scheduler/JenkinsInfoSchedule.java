package com.taocares.monitor.scheduler;

import com.taocares.monitor.service.IJenkinsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * jenkins信息收集定时器
 *
 * @author lin
 * @since 2019年07月21日
 */
@Slf4j
@Component
public class JenkinsInfoSchedule {

    @Autowired
    private IJenkinsService iJenkinsService;

//    @Scheduled(cron = "${jenkins.schedule.time}")
    @Scheduled(initialDelay=1000, fixedRate=60 * 60 * 1000)
    public void getJiraInfo() throws IOException, URISyntaxException {
        log.info("Jenkins数据统计任务开始...");
        Long startTime = System.currentTimeMillis();
        iJenkinsService.getJenkinsInfoForSchedule();
        Long endTime = System.currentTimeMillis();
        log.info("Jira数据统计完成，总耗时：{}",endTime - startTime);
    }


}
