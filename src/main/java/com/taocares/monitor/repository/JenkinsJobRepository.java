package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JenkinsJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JenkinsJobRepository extends JpaRepository<JenkinsJob, String>, JpaSpecificationExecutor<JenkinsJob> {
}
