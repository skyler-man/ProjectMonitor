package com.taocares.monitor.controller;

import com.taocares.monitor.dto.JiraProjectNameDto;
import com.taocares.monitor.service.IJiraService;
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
@RequestMapping(value = "/jira")
public class JiraController {

    @Autowired
    private IJiraService jiraService;

    @RequestMapping(value = "/getJiraProjectNames", method = RequestMethod.GET)
    public List<JiraProjectNameDto> getJiraProjectNames(){
        return jiraService.getJiraProjectNames();
    }

}
