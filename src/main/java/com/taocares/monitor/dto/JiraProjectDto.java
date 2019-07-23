package com.taocares.monitor.dto;

import com.taocares.commons.beans.annotation.Nested;
import com.taocares.monitor.entity.JiraBug;
import com.taocares.monitor.entity.JiraMember;
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
public class JiraProjectDto {

    private String id;

    private String projectName;

    @Nested(thisClass = JiraMemberDto.class, thatClass = JiraMember.class)
    private List<JiraMemberDto> jiraMemberDtos;

    @Nested(thisClass = JiraBugDto.class, thatClass = JiraBug.class)
    private List<JiraBugDto> jiraBugDtos;
}
