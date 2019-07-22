package com.taocares.monitor.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @Description: Sonar项目质量数据
 * @Author: LiuYiQiang
 * @Date: 14:36 2019/7/9
 */
@Entity
@Data
@NoArgsConstructor
@Table(name = "monitor_sonar_measure_info")
public class SonarMeasureInfo {

    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    @Column(name = "ID", unique = true, nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROJECTID", nullable = false)
    private SonarProject sonarProject;

    @Column(name = "name_en", length = 50)
    private String nameEn;

    @Column(name = "name_cn", length = 50)
    private String nameCn;

    @Column(name = "measure", length = 50)
    private String measure;

}
