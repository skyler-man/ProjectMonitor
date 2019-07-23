package com.taocares.monitor.controller;

import com.taocares.monitor.dto.SonarProjectDto;
import com.taocares.monitor.dto.SonarProjectNameDto;
import com.taocares.monitor.service.ISonarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:23 2019/7/23
 */
@RestController
@RequestMapping(value = "/sonar")
public class SonarController {

    @Autowired
    private ISonarService sonarService;

    @RequestMapping(value = "/getSonarProjectNames", method = RequestMethod.GET)
    public List<SonarProjectNameDto> getSonarProjectNames(){
        return sonarService.getSonarProjectNames();
    }

    @RequestMapping(value = "/getSonarProjects", method = RequestMethod.GET)
    public List<SonarProjectDto> getSonarProjects(){
        return sonarService.getSonarProjects();
    }

}
