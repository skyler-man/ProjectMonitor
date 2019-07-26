package com.taocares.monitor.service;

import com.taocares.monitor.dto.ProjectRelationDto;
import com.taocares.monitor.dto.RelationJsonDto;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:24 2019/7/24
 */
public interface IProjectRelationService {

    void editProjectRelation(List<RelationJsonDto> projectRelationDtos);

    Map<String,ProjectRelationDto> findProjectRelation();
}
