package com.taocares.monitor.service;

import com.taocares.monitor.dto.RelationJsonDto;

import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:24 2019/7/24
 */
public interface IProjectRelationService {

    void editProjectRelation(List<RelationJsonDto> projectRelationDtos);

}
