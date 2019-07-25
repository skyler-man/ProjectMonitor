package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * jenkins每月分析dto
 *
 * @author lin
 * @since 2019年7月25日
 */
@Getter
@Setter
public class JenkinsMonthDetailDto {

    private String month;

    private Integer successBuildNumber;

    private Integer allBuildNumber;

    private double rate;

}
