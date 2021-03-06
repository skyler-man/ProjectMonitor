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
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "project_name", length = 50)
    private String projectName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jiraProject",cascade = CascadeType.ALL)
    private List<JiraMember> jiraMembers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "jiraProject",cascade = CascadeType.ALL)
    private List<JiraBug> jiraBugs = new ArrayList<>();

}
