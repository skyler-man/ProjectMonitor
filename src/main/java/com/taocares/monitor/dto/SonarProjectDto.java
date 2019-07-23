package com.taocares.monitor.dto;

import com.taocares.commons.beans.annotation.Nested;
import com.taocares.monitor.entity.SonarMeasureInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:17 2019/7/23
 */
@Getter
@Setter
public class SonarProjectDto {

    private String id;

    private String projectId;

    private String projectKey;

    private String projectName;

    @Nested(thisClass = SonarMeasureInfoDto.class, thatClass = SonarMeasureInfo.class)
    private List<SonarMeasureInfoDto> sonarMeasureInfoDtos;

}
