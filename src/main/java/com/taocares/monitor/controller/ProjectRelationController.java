package com.taocares.monitor.controller;

import com.taocares.monitor.dto.ProjectRelationDto;
import com.taocares.monitor.dto.SonarProjectNameDto;
import com.taocares.monitor.service.IProjectRelationService;
import com.taocares.monitor.service.ISonarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:02 2019/7/24
 */
@RestController
@RequestMapping(value = "/relation")
public class ProjectRelationController {

    @Autowired
    private IProjectRelationService projectRelationService;

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public void editProjectRelation(List<ProjectRelationDto> projectRelationDtos){
        projectRelationService.editProjectRelation(projectRelationDtos);
    }

}
