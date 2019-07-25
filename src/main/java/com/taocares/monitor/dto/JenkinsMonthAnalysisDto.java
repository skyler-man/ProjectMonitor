package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * jenkins每月分析折线图dto
 *
 * @author lin
 * @since 2019年7月25日
 */
@Getter
@Setter
public class JenkinsMonthAnalysisDto {

    private String jobName;

    private List<JenkinsMonthDetailDto> jenkinsMonthDetailList;
}
