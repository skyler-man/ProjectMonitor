package com.taocares.monitor.scheduler;

import com.alibaba.fastjson.JSON;
import com.taocares.monitor.common.HttpUtil;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.common.SonarMeasureEnum;
import com.taocares.monitor.common.StringUtil;
import com.taocares.monitor.dto.SonarInfoJsonDto;
import com.taocares.monitor.dto.SonarProjectJsonDto;
import com.taocares.monitor.entity.SonarMeasureInfo;
import com.taocares.monitor.entity.SonarProject;
import com.taocares.monitor.repository.SonarProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SonarProjectRepository sonarProjectRepository;

    /**
     * @Description: 第一次延迟一秒后执行，每60分钟执行一次,
     * @Author: LiuYiQiang
     * @Date: 8:37 2019/7/10
     */
//    @Scheduled(initialDelay=1000, fixedRate=60 * 60 * 1000)
    public void getSonarInfo() throws URISyntaxException, ExecutionException, InterruptedException {
        log.info("Sonar数据统计任务开始...");
        Long startTime = System.currentTimeMillis();
        //获取所有项目名称（p:pageindex ps:pagesize）
        String getAllProjectUrl = "http://192.168.163.234:9000/api/components/search?qualifiers=TRK&p=1&ps=200";
        String allProject = HttpUtil.doGet(getAllProjectUrl,"3bbf21a122746a4c2c34579ef5c95f7be17db54b");
        if(StringUtil.empty(allProject)){
            return;
        }
        SonarProjectJsonDto sonarProjectDto = JSON.parseObject(allProject,SonarProjectJsonDto.class);
        List<SonarProject> sonarProjects = generateSonarProjectList(sonarProjectDto);
        setSonarMeasureInfo(sonarProjects);
        if(ListUtils.isNotEmpty(sonarProjects)){
            sonarProjectRepository.deleteAll();
            sonarProjectRepository.saveAll(sonarProjects);
        }
        Long endTime = System.currentTimeMillis();
        log.info("Sonar数据统计完成，总耗时：{}",endTime - startTime);
    }

    private void setSonarMeasureInfo(List<SonarProject> sonarProjects){
        if(ListUtils.isEmpty(sonarProjects)){
            return;
        }
        String metricKeys = "metricKeys=bugs,complexity,coverage,duplicated_blocks,duplicated_lines_density,violations,code_smells,vulnerabilities,test_success_density,blocker_violations,critical_violations,major_violations,minor_violations,info_violations,sqale_debt_ratio,comment_lines_density";
        for(SonarProject sonarProject : sonarProjects){
            String getSonarInfoUrl = "http://192.168.163.234:9000/api/measures/component?component=" + sonarProject.getProjectKey() + "&" + metricKeys;
            String sonarInfo = HttpUtil.doGet(getSonarInfoUrl,"3bbf21a122746a4c2c34579ef5c95f7be17db54b");
            SonarInfoJsonDto sonarInfoDto = JSON.parseObject(sonarInfo,SonarInfoJsonDto.class);
            dealWithSonarMeasure(sonarProject,sonarInfoDto);
        }
    }

    private void dealWithSonarMeasure(SonarProject sonarProject,SonarInfoJsonDto sonarInfoDto){
        if(sonarInfoDto.getComponent() == null || ListUtils.isEmpty(sonarInfoDto.getComponent().getMeasures())){
            return;
        }
        List<SonarMeasureInfo> sonarMeasureInfos = new ArrayList<>();
        for(SonarInfoJsonDto.ComponentBean.MeasuresBean measuresBean : sonarInfoDto.getComponent().getMeasures()){
            SonarMeasureInfo measureInfo = new SonarMeasureInfo();
            measureInfo.setSonarProject(sonarProject);
            measureInfo.setNameEn(measuresBean.getMetric());
            if(measuresBean.getMetric() != null && SonarMeasureEnum.valueOfCode(measuresBean.getMetric()) != null){
                measureInfo.setNameCn(SonarMeasureEnum.valueOfCode(measuresBean.getMetric()).getDesc());
            }
            measureInfo.setMeasure(measuresBean.getValue());
            sonarMeasureInfos.add(measureInfo);
        }
        sonarProject.getSonarMeasureInfos().addAll(sonarMeasureInfos);
    }

    private List<SonarProject> generateSonarProjectList(SonarProjectJsonDto sonarProjectDto){
        List<SonarProjectJsonDto.ComponentsBean> sonarProjects = sonarProjectDto.getComponents();
        if(ListUtils.isEmpty(sonarProjects) || sonarProjectDto.getPaging().getTotal() == 0){
            return new ArrayList<>();
        }
        List<SonarProject> projects = new ArrayList<>();
        for(SonarProjectJsonDto.ComponentsBean project : sonarProjects){
            SonarProject sonarProject = new SonarProject();
            sonarProject.setProjectId(project.getId());
            sonarProject.setProjectKey(project.getKey());
            sonarProject.setProjectName(project.getName());
            projects.add(sonarProject);
        }
        return projects;
    }

}
