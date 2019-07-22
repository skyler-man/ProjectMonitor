package com.taocares.monitor.service.impl;

import com.taocares.monitor.entity.${entity};
import com.taocares.monitor.repository.${entity}Repository;
import com.taocares.monitor.service.${table.serviceName};
import ${superServiceImplClassPackage};
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * ${table.comment!} 服务实现类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class ${table.serviceImplName} extends ${superServiceImplClass}<${entity}Repository, ${entity}> implements ${table.serviceName} {

}
