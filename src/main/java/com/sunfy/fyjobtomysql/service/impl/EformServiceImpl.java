package com.sunfy.fyjobtomysql.service.impl;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;
import com.sunfy.fyjobtomysql.repository.Efrom_NewEventRepository;
import com.sunfy.fyjobtomysql.service.EformService;
import com.sunfy.fyjobtomysql.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 接口实现类
 */
@Service("EformService")
public class EformServiceImpl implements EformService {

    //log日志输出对象
    private final static Logger logger = LoggerFactory.getLogger(EformServiceImpl.class);

    @Autowired
    //自动注入Efrom_NewEvent的Jpa对象
    private Efrom_NewEventRepository efrom_NewEventRepository;

    @Override
    public Eform_NewEvent NewEvent(Eform_NewEvent eformNewEvent) {
        logger.info("NewEvent插入对象");
        Date date = new Date();
        date.getTime();
        //创建对象，并写入相应的数据
        Eform_NewEvent eform_newEvent = new Eform_NewEvent();
        if(!eformNewEvent.getContent().isEmpty()){
            eform_newEvent.setContent(eformNewEvent.getContent());
            eform_newEvent.setOrgCode("sfy");
            eform_newEvent.setUsername("孙斐扬");
            eform_newEvent.setUsercode("sunfy");
            eform_newEvent.setCretionDate(date);
            efrom_NewEventRepository.save(eform_newEvent);
            return eform_newEvent;
        }
        return null;
    }

    @Override
    public List<Eform_NewEvent> getEventAboutUser(String usercode) {
        logger.info("根据usercode查询所有Event");
        return efrom_NewEventRepository.findByUsercode(usercode);
    }
}
