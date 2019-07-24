package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * jenkins job_build_detail
 *
 * @author lin
 * @since 2019年7月24日
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jenkins_build_detail")
public class JenkinsJobBuildDetail {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOBID", nullable = false)
    private JenkinsJob jenkinsJob;

    @Column(name = "number", length = 5)
    private String number;

    @Column(name = "url", length = 200)
    private String url;

    @Column(name = "result", length = 20)
    private String result;
}
