package com.taocares.monitor.generator;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.taocares.monitor.generator.config.StaticConfig;
import com.taocares.monitor.generator.tools.GeneratorTools;

import java.util.ArrayList;
import java.util.List;

public class CodeGenerator {
    // 工程地址
    private static String projectPath = System.getProperty("user.dir").replace("\\", "/");

    private static String createTableName = "";

    private static String packagePath = "/src/main/java/com/taocares/monitor";

    private static String[] createTableNames = {"test"};

    public static void main(String[] args) {
        if (createTableNames.length == 0) {
            createTableName = GeneratorTools.scanner("表名");
            generator();
        } else {
            for (int i = 0; i < createTableNames.length; i++) {
                createTableName = createTableNames[i];
                generator();
            }
        }
    }

    /**
     * 生成主方法
     */
    public static void generator() {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        // 设置全局变量
        mpg.setGlobalConfig(setGlobalConfig());
        // 设置数据库配置
        mpg.setDataSource(setDataSourceConfig());
        // 设置包配置参数
        mpg.setPackageInfo(setPackageConfig());
        // 自定义配置参数
        mpg.setCfg(setInjectionConfig());
        // 模板配置
        mpg.setTemplate(setTemplateConfig());
        // 设置策略配置
        mpg.setStrategy(setStrategyConfig());
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

    /**
     * 设置全局变量
     */
    private static GlobalConfig setGlobalConfig() {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        gc.setAuthor("lin");
        gc.setOpen(false);
        gc.setSwagger2(true);
        gc.setActiveRecord(true);
        gc.setOutputDir(projectPath);
        return gc;
    }

    /**
     * 设置数据库配置
     */
    private static DataSourceConfig setDataSourceConfig() {
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // 数据库地址
        dsc.setUrl(StaticConfig.DATA_SOURCE_RUL);
        // 数据库类别
        dsc.setDbType(StaticConfig.DATA_TYPE);
        // 数据库驱动
        dsc.setDriverName(StaticConfig.DATA_DRIVER);
        // 数据库账号
        dsc.setUsername(StaticConfig.DATA_USER);
        // 数据库密码
        dsc.setPassword(StaticConfig.DATA_PASSWORD);
        return dsc;
    }

    /**
     * 设置包配置参数
     */
    private static PackageConfig setPackageConfig() {
        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(GeneratorTools.scanner("模块名"));
        pc.setParent("com.miniprogram");
        return pc;
    }

    /**
     * 自定义配置参数
     */
    private static InjectionConfig setInjectionConfig() {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(setRepositoryTemplate());
        focList.add(setServiceImplTemplate());
        focList.add(setServiceTemplate());
        focList.add(setControllerTemplate());
        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static FileOutConfig setRepositoryTemplate() {
        FileOutConfig fileOutConfig = new FileOutConfig("/gentemplate/repository.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + packagePath  + "/Repository/" + tableInfo.getEntityName() + "Repository" + StringPool.DOT_JAVA;
            }
        };
        return fileOutConfig;
    }

    private static FileOutConfig setServiceImplTemplate() {
        FileOutConfig fileOutConfig = new FileOutConfig("/gentemplate/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + packagePath + "/service/impl/" + tableInfo.getEntityName() + "ServiceImpl" + StringPool.DOT_JAVA;
            }
        };
        return fileOutConfig;
    }

    private static FileOutConfig setServiceTemplate() {
        FileOutConfig fileOutConfig = new FileOutConfig("/gentemplate/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + packagePath + "/service/I" + tableInfo.getEntityName() + "Service" + StringPool.DOT_JAVA;
            }
        };
        return fileOutConfig;
    }

    private static FileOutConfig setControllerTemplate() {
        FileOutConfig fileOutConfig = new FileOutConfig("/gentemplate/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return projectPath + packagePath + "/controller/" + tableInfo.getEntityName() + "Controller" + StringPool.DOT_JAVA;
            }
        };
        return fileOutConfig;
    }

    /**
     * 设置模板
     */
    private static TemplateConfig setTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setController(null);
        templateConfig.setXml(null);
        templateConfig.setMapper(null);
        templateConfig.setEntity(null);
        templateConfig.setService(null);
        templateConfig.setServiceImpl(null);
        return templateConfig;
    }

    /**
     * 设置策略配置
     */
    private static StrategyConfig setStrategyConfig() {
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceClass(StaticConfig.BASE_SERVICE);
        strategy.setSuperServiceImplClass(StaticConfig.BASE_SERVICE_IMPL);
        String tableName = createTableName;
        String tableNameHump = GeneratorTools.stringToHump(tableName);
        strategy.setInclude(tableName);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setSuperEntityClass(null);
        return strategy;
    }
}
