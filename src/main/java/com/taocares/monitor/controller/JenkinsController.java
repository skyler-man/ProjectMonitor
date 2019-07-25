package com.taocares.monitor.controller;

import com.taocares.monitor.dto.JenkinsMonthAnalysisDto;
import com.taocares.monitor.service.IJenkinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * jenkins相关方法
 *
 * @author lin
 * @since 2019年7月25日
 */
@RestController
@RequestMapping(value = "/jenkins")
public class JenkinsController {

    @Autowired
    private IJenkinsService jenkinsService;

    @RequestMapping(value = "/getJenkinsMonthAnalysis", method = RequestMethod.GET)
    public List<JenkinsMonthAnalysisDto> getJenkinsMonthAnalysis(Integer size) {
        return this.jenkinsService.getJenkinsMonthAnalysisDtoInThisYear(size);
    }

}
