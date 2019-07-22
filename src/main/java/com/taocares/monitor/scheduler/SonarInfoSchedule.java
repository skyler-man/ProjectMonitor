package com.taocares.monitor.scheduler;

import com.alibaba.fastjson.JSON;
import com.taocares.monitor.common.HttpUtil;
import com.taocares.monitor.dto.SonarInfoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:36 2019/7/9
 */
@Slf4j
@Component
public class SonarInfoSchedule {

    /**
     * @Description: 第一次延迟一秒后执行，每5分钟执行一次,
     * @Author: LiuYiQiang
     * @Date: 8:37 2019/7/10
     */
    @Scheduled(initialDelay=1000, fixedRate=10000)
    public void getJiraInfo() throws URISyntaxException, ExecutionException, InterruptedException {
        //获取所有项目名称
        String getAllProjectUrl = "http://192.168.163.234:9000/api/components/search?qualifiers=TRK";
        String allProject = HttpUtil.doGet(getAllProjectUrl,"3bbf21a122746a4c2c34579ef5c95f7be17db54b");


        String url = "http://192.168.163.234:9000/api/measures/component?component=starter&metricKeys=bugs,ncloc,complexity,violations&additionalFields=metrics,periods";
        String responseJson = HttpUtil.doGet(url,"3bbf21a122746a4c2c34579ef5c95f7be17db54b");
        SonarInfoDto jsonObject = JSON.parseObject(responseJson,SonarInfoDto.class);

        System.out.println(jsonObject);
    }

}
