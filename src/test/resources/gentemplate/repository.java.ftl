package com.taocares.monitor.repository;

import com.taocares.monitor.entity.${entity};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * <p>
 * ${table.comment!} Repository 接口
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
public interface ${entity}Repository extends JpaRepository<${entity}, String>, JpaSpecificationExecutor<${entity}> {

}
