package com.taocares.monitor.generator.config;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * 数据库参数配置
 *
 * @author lin
 * @date 2018年11月11日
 */
public class StaticConfig {

    // 数据库地址
    public static String DATA_SOURCE_RUL = "jdbc:mysql://rm-m5e0503y2wul76t36to.mysql.rds.aliyuncs.com:3306/project_monitor?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
    // 数据库驱动
    public static String DATA_DRIVER = "com.mysql.jdbc.Driver";
    // 数据库账号
    public static String DATA_USER = "monitor";
    // 数据库密码
    public static String DATA_PASSWORD = "Liuyiqiangniubi666";
    // 公用service类地址
    public static String BASE_SERVICE = "com.taocares.monitor.service.IBaseService";
    // 公用serviceImpl类地址
    public static String BASE_SERVICE_IMPL = "com.taocares.monitor.service.impl.BaseServiceImpl";
    // 数据库类型
    public static DbType DATA_TYPE = DbType.MYSQL;

}
