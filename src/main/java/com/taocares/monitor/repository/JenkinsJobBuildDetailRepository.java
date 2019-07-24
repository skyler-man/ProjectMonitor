package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JenkinsJobBuildDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JenkinsJobBuildDetailRepository extends JpaRepository<JenkinsJobBuildDetail, String>, JpaSpecificationExecutor<JenkinsJobBuildDetail> {
}
