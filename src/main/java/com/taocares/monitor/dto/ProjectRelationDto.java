package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:27 2019/7/24
 */
@Getter
@Setter
public class ProjectRelationDto {

    private String jiraId;

    private String sonarId;

    private String jenkinsId;

}
