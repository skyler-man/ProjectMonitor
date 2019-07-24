package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JenkinsJob;
import com.taocares.monitor.entity.JenkinsJobBuildInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JenkinsJobBuildInfoRepository extends JpaRepository<JenkinsJobBuildInfo, String>, JpaSpecificationExecutor<JenkinsJobBuildInfo> {
}
