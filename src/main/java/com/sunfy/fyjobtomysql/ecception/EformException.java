package com.sunfy.fyjobtomysql.ecception;

import com.sunfy.fyjobtomysql.enums.ResultEnum;

/**
 * 异常类
 * 注意：在继承父类时要继承RuntimeException（RuntimeException继承于Exception，发生异常时会回滚，直接继承Exception，则发生异常时不回滚）
 */
public class EformException extends RuntimeException {

    private Integer code;

    public EformException(ResultEnum resultEnum) {
//    public GirlException(Integer code, String message) {
//        super(message);
        super(resultEnum.getMsg());
//        this.code = code;
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
