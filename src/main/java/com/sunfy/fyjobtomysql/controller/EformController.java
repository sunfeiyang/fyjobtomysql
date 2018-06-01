package com.sunfy.fyjobtomysql.controller;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;
import com.sunfy.fyjobtomysql.service.EformService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 表单相关操作
 */
@RestController
public class EformController {

    private final static Logger logger = LoggerFactory.getLogger(EformController.class);

    @Autowired
    private EformService eformService;

    @PostMapping(value = "newEvent")
    public String AddNewEvent(@Valid Eform_NewEvent eform_newEvent){
        logger.info("执行新增待办事项！");
        eformService.NewEvent(eform_newEvent);
        return "success";
    }

    @GetMapping(value = "/eventList/{usercode}")
    public Eform_NewEvent getEvent(@PathVariable("usercode") String usercode){
        logger.info("根据usercode获取所有Event！");
        return eformService.getEventAboutUser(usercode);
    }

}
