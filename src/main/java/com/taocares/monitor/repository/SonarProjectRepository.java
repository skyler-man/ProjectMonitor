package com.taocares.monitor.repository;

import com.taocares.monitor.entity.SonarProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 14:54 2019/7/9
 */
public interface SonarProjectRepository extends JpaRepository<SonarProject, String>, JpaSpecificationExecutor<SonarProject> {
}
