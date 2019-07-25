package com.taocares.monitor.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 13:05 2019/7/25
 */
@Getter
@Setter
public class ProjectRateDto {

    /**
     * 单元测试覆盖率
     */
    private String coverage;

    /**
     * 代码重复率
     */
    private String duplicatedRate;

    /**
     * 单元测试成功率  测试成功密度=（单元测试 - （单元测试错误 + 单元测试失败））/ 单元测试 * 100
     */
    private String testSuccessDensity;

    /**
     * 技术债务比率  修复成本/（开发1行代码的代价*代码行数）
     */
    private String sqaleDebtRatio;

    /**
     * 注释覆盖率 注释行的密度= 注释行 /（代码行 + 注释行）* 100（50％表示代码行数等于注释行数）
     */
    private String commentLinesDensity;
}
