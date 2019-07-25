package com.taocares.monitor.controller;

import com.taocares.monitor.dto.JenkinsJobNameDto;
import com.taocares.monitor.service.IJenkinsService;
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
@RequestMapping(value = "/jenkins")
public class JenkinsController {

    @Autowired
    private IJenkinsService jenkinsService;

    @RequestMapping(value = "/getJenkinsProjectNames", method = RequestMethod.GET)
    public List<JenkinsJobNameDto> getJiraProjectNames(){
        return jenkinsService.getJenkinsJobNames();
    }

}
