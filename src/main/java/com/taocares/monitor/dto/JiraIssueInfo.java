package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 14:25 2019/7/25
 */
@Getter
@Setter
public class JiraIssueInfo {

    private Map<String,Integer> jiraBugInfo;

    private Integer bugs;
}
