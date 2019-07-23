package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * jenkins job
 *
 * @author lin
 * @since 2019年7月23日
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_jenkins_job")
public class JenkinsJob {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "url", length = 200)
    private String url;

}
