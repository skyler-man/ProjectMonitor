package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JenkinsJobBuildMonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JenkinsJobBuildMonInfoRepository extends JpaRepository<JenkinsJobBuildMonInfo, String>, JpaSpecificationExecutor<JenkinsJobBuildMonInfo> {
}
