package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: Jira项目实体
 * @Author: LiuYiQiang
 * @Date: 14:36 2019/7/9
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jira_project")
public class JiraProject {

    @Id
    @GenericGenerator(name = "id",strategy = "assigned")
    @GeneratedValue(generator = "id")
    @Column(name = "ID", unique = true, nullable = false, precision = 20)
    private String id;

    @Column(name = "project_name", length = 50)
    private String projectName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jiraProject",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<JiraMember> jiraMembers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jiraProject",cascade = {CascadeType.MERGE,CascadeType.REMOVE})
    private List<JiraBug> jiraBugs = new ArrayList<>();

}
