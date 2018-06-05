package com.sunfy.fyjobtomysql.handle;

import com.sunfy.fyjobtomysql.domain.Result;
import com.sunfy.fyjobtomysql.ecception.EformException;
import com.sunfy.fyjobtomysql.enums.ResultEnum;
import com.sunfy.fyjobtomysql.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    //声明要捕获的异常类
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof EformException){
            EformException eformException = (EformException) e;
            return ResultUtil.error(ResultEnum.ERROR);
        }else{
            logger.error("【系统异常】{}", e);
            return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
        }
    }

}

