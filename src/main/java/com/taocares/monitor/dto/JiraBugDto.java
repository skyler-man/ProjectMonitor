package com.taocares.monitor.dto;

import com.taocares.commons.beans.annotation.Mapping;
import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:17 2019/7/23
 */
@Getter
@Setter
public class JiraBugDto {

    private String id;

    @Mapping(field = "jiraProject.id")
    private String jiraProjectId;

    private String statusName;

    private Integer number;

}
