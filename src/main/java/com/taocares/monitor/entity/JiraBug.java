package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description: 项目Bug状态及数量
 * @Author: LiuYiQiang
 * @Date: 14:49 2019/7/9
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jira_bug")
public class JiraBug {

    @Id
    @GenericGenerator(name = "id",strategy = "assigned")
    @GeneratedValue(generator = "id")
    @Column(name = "ID", unique = true, nullable = false, precision = 20)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECTID", nullable = false)
    private JiraProject jiraProject;

    @Column(name = "status_name", length = 50)
    private String statusName;

    @Column(name="number",precision = 4, scale = 0)
    private Integer number;

}
