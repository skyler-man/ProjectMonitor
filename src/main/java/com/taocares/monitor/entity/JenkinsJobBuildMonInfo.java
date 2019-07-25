package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * jenkins job_build_detail
 *
 * @author lin
 * @since 2019年7月24日
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jenkins_build_month_info")
public class JenkinsJobBuildMonInfo {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOBID", nullable = false)
    private JenkinsJob jenkinsJob;

    @Column(name="all_number",precision = 4, scale = 0)
    private Integer allNumber;

    @Column(name="success_number",precision = 4, scale = 0)
    private Integer successNumber;

    @Column(name = "info_time", length = 10)
    private String infoTime;
}
