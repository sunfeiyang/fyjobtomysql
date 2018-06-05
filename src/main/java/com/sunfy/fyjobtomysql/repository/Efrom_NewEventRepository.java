package com.sunfy.fyjobtomysql.repository;

import com.sunfy.fyjobtomysql.domain.Eform_NewEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 通过Jpa实现自动写入数据库
 *  Eform_NewEvent 相对应的实体类
 */
public interface Efrom_NewEventRepository extends JpaRepository<Eform_NewEvent,Integer>{

    //通过年龄来查询 方法名规则固定
    public List<Eform_NewEvent> findByUsercode(String usercode);
}
