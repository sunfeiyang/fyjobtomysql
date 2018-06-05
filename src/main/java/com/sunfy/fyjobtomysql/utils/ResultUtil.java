package com.sunfy.fyjobtomysql.utils;

import com.sunfy.fyjobtomysql.domain.Result;
import com.sunfy.fyjobtomysql.enums.ResultEnum;

/**
 * 工具类  处理返回信息
 */
public class ResultUtil {

    /**
     * 请求成功 也data中包含值
     * @param resultEnum
     * @param object
     * @return
     */
    public static Result success(ResultEnum resultEnum,Object object){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(object);
        return result;
    }

    /**
     * 请求成功 data为null
     * @return
     */
    public static Result success(ResultEnum resultEnum){
        return success(null);
    }

    /**
     * 请求失败
     * @param resultEnum
     * @return
     */
    public static Result error(ResultEnum resultEnum){
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMsg(resultEnum.getMsg());
        result.setData(null);
        return result;
    }

}
