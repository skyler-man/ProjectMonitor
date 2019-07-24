package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * jenkins job_build_info
 *
 * @author lin
 * @since 2019年7月24日
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jenkins_build_info")
public class JenkinsJobBuildInfo {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOBID", nullable = false)
    private JenkinsJob jenkinsJob;

    @Column(name = "last_build_num", length = 5)
    private String lastBuildNum;

    @Column(name = "last_build_url", length = 200)
    private String lastBuildUrl;

    @Column(name = "last_completed_build_num", length = 5)
    private String lastCompletedBuildNum;

    @Column(name = "last_completed_build_num_url", length = 200)
    private String lastCompletedBuildNumUrl;

    @Column(name = "last_failed_build_num", length = 5)
    private String lastFailedBuildNum;

    @Column(name = "last_failed_build_url", length = 200)
    private String lastFailedBuildUrl;

    @Column(name = "last_successful_build_name", length = 5)
    private String lastSuccessfulBuildName;

    @Column(name = "last_successful_build_url", length = 200)
    private String lastSuccessfulBuildUrl;

}
