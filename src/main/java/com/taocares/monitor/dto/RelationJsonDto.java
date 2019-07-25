package com.taocares.monitor.dto;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 17:06 2019/7/25
 */
public class RelationJsonDto {


    /**
     * jiraId : 2c90819f6c27d397016c27e49c6e0000
     * sonarId : 2c90819f6c27d397016c27ee0a6c018d
     * jenkinsId : 2c9083a76c279f0a016c279fe8ae0002
     */

    private String jiraId;
    private String sonarId;
    private String jenkinsId;

    public String getJiraId() {
        return jiraId;
    }

    public void setJiraId(String jiraId) {
        this.jiraId = jiraId;
    }

    public String getSonarId() {
        return sonarId;
    }

    public void setSonarId(String sonarId) {
        this.sonarId = sonarId;
    }

    public String getJenkinsId() {
        return jenkinsId;
    }

    public void setJenkinsId(String jenkinsId) {
        this.jenkinsId = jenkinsId;
    }
}
