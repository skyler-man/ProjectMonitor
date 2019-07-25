package com.taocares.monitor.service.impl;

import com.taocares.monitor.repository.JiraProjectRepository;
import com.taocares.monitor.repository.SonarProjectRepository;
import com.taocares.monitor.service.IQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 8:11 2019/7/23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class QueryServiceImpl implements IQueryService{

    @Autowired
    private SonarProjectRepository sonarProjectRepository;
    @Autowired
    private JiraProjectRepository jiraProjectRepository;



}
