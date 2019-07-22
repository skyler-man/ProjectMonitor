package com.taocares.monitor.common;

import com.google.common.collect.ImmutableMap;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 14:52 2019/7/22
 */
public enum  SonarMeasureEnum {

    bugs("bugs","BUG数量"),
    complexity("complexity","代码复杂度"),
    coverage("coverage","单元测试覆盖率"),
    duplicated_lines("duplicated_lines","重复代码行数"),
    duplicated_lines_density("duplicated_lines_density","重复代码百分比"),
    violations("violations","违规数"),
    code_smells("code_smells","坏味道数量"),
    vulnerabilities("vulnerabilities","安全漏洞数量");


    private String code;
    private String Desc;

    private static final ImmutableMap<String, SonarMeasureEnum> CACHE;

    static {
        final ImmutableMap.Builder<String, SonarMeasureEnum> builder = ImmutableMap.builder();
        for (SonarMeasureEnum baseInfoEnum : values()) {
            builder.put(baseInfoEnum.getCode(), baseInfoEnum);
        }
        CACHE = builder.build();
    }

    SonarMeasureEnum(String code, String desc) {
        this.code = code;
        Desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return Desc;
    }

    public static SonarMeasureEnum valueOfCode(String code) {
        final SonarMeasureEnum status = CACHE.get(code);
        if (status == null) {
            return null;
        }
        return status;
    }

}
