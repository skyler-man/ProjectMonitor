package com.taocares.monitor.scheduler;

import com.alibaba.fastjson.JSON;
import com.taocares.monitor.common.HttpUtil;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.common.StringUtil;
import com.taocares.monitor.dto.SonarInfoDto;
import com.taocares.monitor.dto.SonarProjectDto;
import com.taocares.monitor.entity.SonarProject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:36 2019/7/9
 */
@Slf4j
@Component
@Transactional(rollbackFor = Exception.class)
public class SonarInfoSchedule {

    /**
     * @Description: 第一次延迟一秒后执行，每5分钟执行一次,
     * @Author: LiuYiQiang
     * @Date: 8:37 2019/7/10
     */
    @Scheduled(initialDelay=1000, fixedRate=10000)
    public void getJiraInfo() throws URISyntaxException, ExecutionException, InterruptedException {
        //获取所有项目名称（p:pageindex ps:pagesize）
        String getAllProjectUrl = "http://192.168.163.234:9000/api/components/search?qualifiers=TRK&p=1&ps=200";
        String allProject = HttpUtil.doGet(getAllProjectUrl,"3bbf21a122746a4c2c34579ef5c95f7be17db54b");
        if(StringUtil.empty(allProject)){
            return;
        }
        SonarProjectDto sonarProjectDto = JSON.parseObject(allProject,SonarProjectDto.class);
        List<SonarProject> sonarProjects = generateSonarProjectList(sonarProjectDto);

        String sonarInfo = HttpUtil.doGet(getSonarInfoUrl(sonarProjects),"3bbf21a122746a4c2c34579ef5c95f7be17db54b");
        SonarInfoDto jsonObject = JSON.parseObject(sonarInfo,SonarInfoDto.class);

        System.out.println(jsonObject);
    }

    private String getSonarInfoUrl(List<SonarProject> sonarProjects){
        String metricKeys = "metricKeys=bugs,complexity,coverage,duplicated_lines,duplicated_lines_density,violations,code_smells,vulnerabilities";
        String componentkey = "";
        String getSonarInfoUrl = "http://192.168.163.234:9000/api/measures/component?component=" + componentkey + "&" + metricKeys;
        return getSonarInfoUrl;
    }

    private List<SonarProject> generateSonarProjectList(SonarProjectDto sonarProjectDto){
        List<SonarProjectDto.ComponentsBean> sonarProjects = sonarProjectDto.getComponents();
        if(ListUtils.isEmpty(sonarProjects) || sonarProjectDto.getPaging().getTotal() == 0){
            return new ArrayList<>();
        }
        List<SonarProject> projects = new ArrayList<>();
        for(SonarProjectDto.ComponentsBean project : sonarProjects){
            SonarProject sonarProject = new SonarProject();
            sonarProject.setProjectId(project.getId());
            sonarProject.setProjectKey(project.getKey());
            sonarProject.setProjectName(project.getName());
            projects.add(sonarProject);
        }
        return projects;
    }

}
