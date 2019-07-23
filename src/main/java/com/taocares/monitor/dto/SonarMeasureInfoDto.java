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
public class SonarMeasureInfoDto {

    private String id;

    @Mapping(field = "sonarProject.id")
    private String sonarProjectId;

    private String nameEn;

    private String nameCn;

    private String measure;

}
