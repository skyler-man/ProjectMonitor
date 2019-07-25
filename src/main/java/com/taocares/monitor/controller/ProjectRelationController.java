package com.taocares.monitor.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taocares.monitor.common.ListUtils;
import com.taocares.monitor.dto.ProjectRelationDto;
import com.taocares.monitor.dto.RelationJsonDto;
import com.taocares.monitor.service.IProjectRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 9:02 2019/7/24
 */
@RestController
@RequestMapping(value = "/relation")
public class ProjectRelationController {

    @Autowired
    private IProjectRelationService projectRelationService;

    @RequestMapping(value = "/set", method = RequestMethod.POST)
    public void editProjectRelation(@RequestParam(value = "content") String json){
        List<JSONObject> relationSetJsonDtos = JSON.parseObject(json,ArrayList.class);
        if(ListUtils.isEmpty(relationSetJsonDtos)){
            return;
        }
        List<RelationJsonDto> relationJsonDtos = new ArrayList<>();
        for(JSONObject jsonObject : relationSetJsonDtos){
            RelationJsonDto relationJsonDto = new RelationJsonDto();
            relationJsonDto.setJenkinsId((String) jsonObject.get("jenkinsId"));
            relationJsonDto.setJiraId((String) jsonObject.get("jiraId"));
            relationJsonDto.setSonarId((String) jsonObject.get("sonarId"));
            relationJsonDtos.add(relationJsonDto);
        }
        projectRelationService.editProjectRelation(relationJsonDtos);
    }

}
