package com.sunfy.fyjobtomysql.controller;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;
import com.sunfy.fyjobtomysql.domain.Result;
import com.sunfy.fyjobtomysql.enums.ResultEnum;
import com.sunfy.fyjobtomysql.service.EformService;
import com.sunfy.fyjobtomysql.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 表单相关操作
 */
@RestController
public class EformController {

    private final static Logger logger = LoggerFactory.getLogger(EformController.class);

    @Autowired
    private EformService eformService;

    //BindingResult bindingResult  要验证的内容  以及验证后返回结果 针对的是post请求
    @PostMapping(value = "newEvent")
    public Result AddNewEvent(@Valid Eform_NewEvent eform_newEvent, BindingResult bindingResult){
        logger.info("执行新增待办事项！");
        //如果有错误
        if(bindingResult.hasErrors()){
            //利用枚举的方式对code和msg进行统一管理
            return ResultUtil.error(ResultEnum.EFORM_ERROR );
        }
        return ResultUtil.success(ResultEnum.SUCCESS,eformService.NewEvent(eform_newEvent));
    }

    @GetMapping(value = "/eventList/{usercode}")
    public Result getEvent(@PathVariable("usercode") String usercode){
        logger.info("根据usercode获取所有Event！---"+usercode);
        return ResultUtil.success(ResultEnum.SUCCESS,eformService.getEventAboutUser(usercode));
    }

}
