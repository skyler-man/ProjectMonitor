package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JenkinsJob;
import com.taocares.monitor.entity.JenkinsJobBuildMonInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JenkinsJobBuildMonInfoRepository extends JpaRepository<JenkinsJobBuildMonInfo, String>, JpaSpecificationExecutor<JenkinsJobBuildMonInfo> {


    @Query(value = "select jobid from monitor_jenkins_build_month_info where info_time >= ?1 group by jobid order by sum(all_number) desc", nativeQuery = true)
    List<String> findMostBuildJodId(String beginTime);

    @Query(value = "select * from monitor_jenkins_build_month_info where jobid = ?1 order by info_time asc;", nativeQuery = true)
    List<JenkinsJobBuildMonInfo> findBuildInfoByJobId(String jobId);
}
