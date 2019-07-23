package com.taocares.monitor.service.impl;

import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.SonarMeasureInfoDto;
import com.taocares.monitor.dto.SonarProjectDto;
import com.taocares.monitor.dto.SonarProjectNameDto;
import com.taocares.monitor.entity.SonarMeasureInfo;
import com.taocares.monitor.entity.SonarProject;
import com.taocares.monitor.repository.SonarProjectRepository;
import com.taocares.monitor.service.ISonarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:11 2019/7/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SonarServiceImpl implements ISonarService{

    @Autowired
    private SonarProjectRepository sonarProjectRepository;

    @Override
    public List<SonarProjectNameDto> getSonarProjectNames() {
        List<SonarProject> sonarProjects = sonarProjectRepository.findAll();
        if(ListUtils.isEmpty(sonarProjects)){
            return new ArrayList<>();
        }
        List<SonarProjectNameDto> sonarProjectNameDtos = new ArrayList<>();
        for(SonarProject project : sonarProjects){
            SonarProjectNameDto sonarProjectNameDto = new SonarProjectNameDto();
            sonarProjectNameDto.setId(project.getId());
            sonarProjectNameDto.setProjectName(project.getProjectName());
            sonarProjectNameDto.setProjectId(project.getProjectId());
            sonarProjectNameDto.setProjectKey(project.getProjectKey());
            sonarProjectNameDtos.add(sonarProjectNameDto);
        }
        return sonarProjectNameDtos;
    }

    @Override
    public List<SonarProjectDto> getSonarProjects() {
        List<SonarProject> sonarProjects = sonarProjectRepository.findAll();
        if(ListUtils.isEmpty(sonarProjects)){
            return new ArrayList<>();
        }
        List<SonarProjectDto> sonarProjectDtos = new ArrayList<>();
        for(SonarProject sonarProject : sonarProjects){
            SonarProjectDto sonarProjectDto = new SonarProjectDto();
            sonarProjectDto.setId(sonarProject.getId());
            sonarProjectDto.setProjectName(sonarProject.getProjectName());
            sonarProjectDto.setProjectId(sonarProject.getProjectId());
            sonarProjectDto.setProjectKey(sonarProject.getProjectKey());
            if(ListUtils.isNotEmpty(sonarProject.getSonarMeasureInfos())){
                List<SonarMeasureInfoDto> sonarMeasureInfoDtos = new ArrayList<>();
                for(SonarMeasureInfo sonarMeasureInfo : sonarProject.getSonarMeasureInfos()){
                    SonarMeasureInfoDto sonarMeasureInfoDto = new SonarMeasureInfoDto();
                    sonarMeasureInfoDto.setId(sonarMeasureInfo.getId());
                    sonarMeasureInfoDto.setMeasure(sonarMeasureInfo.getMeasure());
                    sonarMeasureInfoDto.setNameCn(sonarMeasureInfo.getNameCn());
                    sonarMeasureInfoDto.setNameEn(sonarMeasureInfo.getNameEn());
                    sonarMeasureInfoDto.setSonarProjectId(sonarProject.getId());
                    sonarMeasureInfoDtos.add(sonarMeasureInfoDto);
                }
                sonarProjectDto.setSonarMeasureInfoDtos(sonarMeasureInfoDtos);
            }
        }
        return sonarProjectDtos;
    }
}
