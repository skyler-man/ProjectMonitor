package com.taocares.monitor.common;

/**
 * @Description:
 * @Author: LiuYiQiang
 * @Date: 14:21 2019/7/9
 */
public enum  JiraStatuEnum {

    Open("Open","开放"),
    InProgress("In Progress","处理中"),
    Reopened("Reopened","重新打开"),
    Resolved("Resolved","已解决"),
    Closed("Closed","已关闭"),
    NotProgressNow("暂不处理","暂不处理"),
    UnableReproduce("无法重现","无法重现"),
    Done("Done","完成"),
    ToDo("To Do","待办"),
    Testing("提交测试","提交测试");


    private String code;
    private String Desc;

    JiraStatuEnum(String code, String desc) {
        this.code = code;
        Desc = desc;
    }

    public String getCode() {
        return code;
    }
}
