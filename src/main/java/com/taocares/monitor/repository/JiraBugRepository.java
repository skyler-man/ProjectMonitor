package com.taocares.monitor.repository;

import com.taocares.monitor.entity.JiraBug;
import com.taocares.monitor.entity.JiraProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 14:54 2019/7/9
 */
public interface JiraBugRepository extends JpaRepository<JiraBug, String>, JpaSpecificationExecutor<JiraBug> {
}
