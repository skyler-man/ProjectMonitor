package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:28 2019/7/24
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_project_relation")
public class ProjectRelation {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private JiraProject jiraProject;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private SonarProject sonarProject;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private JenkinsJob jenkinsJob;

}
