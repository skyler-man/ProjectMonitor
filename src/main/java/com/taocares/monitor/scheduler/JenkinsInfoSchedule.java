package com.taocares.monitor.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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


    @Scheduled(initialDelay=1000, fixedRate=5 * 60 * 1000)
    public void getJiraInfo() throws URISyntaxException, ExecutionException, InterruptedException {

    }



}
