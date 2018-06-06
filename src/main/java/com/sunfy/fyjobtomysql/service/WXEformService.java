package com.sunfy.fyjobtomysql.service;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;

import java.util.List;

/**
 * Eform 接口类
 */
@SuppressWarnings("rawtypes")
public interface WXEformService {

    /**
     * 新增一个待办事项
     * @param eformNewEvent
     * @return
     */
    public Eform_NewEvent WXNewEvent(Eform_NewEvent eformNewEvent);

    /**
     * 通过用户code获得所有事项
     * @param usercode
     * @return
     */
    public List<Eform_NewEvent> getWXEventAboutUser(String usercode);
}
