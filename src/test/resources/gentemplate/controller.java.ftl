package com.taocares.monitor.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.taocares.monitor.dto.Json;

import com.taocares.monitor.service.${table.serviceName};
import com.taocares.monitor.entity.${entity};
import com.taocares.monitor.common.*;

/**
 * <p>
 * ${table.comment!} 前端控制器
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@RestController
@Slf4j
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
public class ${table.controllerName} {

    @Autowired
    private ${table.serviceName} ${table.entityPath}Service;

    /**
     * 增加接口
     */
    @PostMapping("/insert")
    public Json<String> insert(${entity} ${table.entityPath}) {
        test = this.${table.entityPath}Service.saveOne(${table.entityPath});
        log.info("增加{}成功，增加的id为{}", "${table.entityPath}", ${table.entityPath}.getId());
        return new Json<>("增加成功");
    }

    /**
     * 更新接口
     */
    @PostMapping("/updateById")
    public Json<String> updateById(${entity} ${table.entityPath}) {
        test = this.${table.entityPath}Service.saveOne(${table.entityPath});
        log.info("更新{}成功，更新的id为{}", "${table.entityPath}", ${table.entityPath}.getId());
        return new Json<>("更新成功");
    }

    /**
     * 删除接口
     */
    @PostMapping("/deleteById")
    public Json<String> deleteById(String id) {
        if (id == null) {
            throw new RestException(RestStatusEnum.ID_NOTNULL);
        }
        this.${table.entityPath}Service.deleteById(id);
        log.info("删除{}成功，删除的id为{}", "${table.entityPath}", id);
        return new Json<>("删除成功");
    }

     /**
      * 根据id查询接口
      */
     @GetMapping("/findById")
     public Json<${entity}> findById(String id) {
         if (id == null) {
            throw new RestException(RestStatusEnum.ID_NOTNULL);
         }
         return new Json<>(this.${table.entityPath}Service.getOne(id));
     }

}

