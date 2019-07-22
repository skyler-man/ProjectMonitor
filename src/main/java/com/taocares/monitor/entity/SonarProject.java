package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Sonar项目实体
 * @Author: LiuYiQiang
 * @Date: 14:36 2019/7/9
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_sonar_project")
public class SonarProject {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "project_id", length = 50)
    private String projectId;

    @Column(name = "project_key", length = 50)
    private String projectKey;

    @Column(name = "project_name", length = 50)
    private String projectName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sonarProject",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<SonarMeasureInfo> sonarMeasureInfos = new ArrayList<>();

}
