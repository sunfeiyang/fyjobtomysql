package com.sunfy.fyjobtomysql.enums;

/**
 * 业务接口返回结果
 * 枚举
 */
public enum ResultEnum {
    UNKNOW_ERROR(-1,"未知错误！"),
    SUCCESS(1, "成功"),
    ERROR(0,"错误"),
    EFORM_ERROR(100,"表单校验失败！"),
    ;

    private Integer code;

    private String Msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        Msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return Msg;
    }
}
