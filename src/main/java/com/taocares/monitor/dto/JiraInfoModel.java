package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 10:06 2019/7/9
 */
@Getter
@Setter
public class JiraInfoModel {

    List<String> jiraCommentsBody;
    DateTime jiraCreateTime;
    String description;
    String summary;
    String reporter;
    ArrayList<String> assignees;
    String status;
    String issueType;
    ArrayList<String> modules;
    ArrayList<String> qianduans;
    ArrayList<String> developers;
    String product;
    String start_develop_time;
    String UE_start_time;
    String UE_end_time;
    String UI_start_time;
    String UI_end_time;
    String app_start_time;
    String app_end_time;
    String qianduan_start_time;
    String qianduan_end_time;
    String develop_start_time;
    String develop_end_time;
    String liantiao_start_time;
    String liantiao_end_time;
    String test_start_time;
    String test_end_time;

}
