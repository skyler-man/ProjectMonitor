package com.taocares.monitor.service;

import com.taocares.monitor.dto.ProjectRelationDto;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:24 2019/7/24
 */
public interface IProjectRelationService {

    void editProjectRelation(List<ProjectRelationDto> projectRelationDtos);

}
