package com.sunfy.fyjobtomysql.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.util.Date;

/**
 * 待办事宜
 */
@Entity
public class Eform_NewEvent {

    @Id
    @GeneratedValue
    //主键
    private Integer id;
    //具体内容
    private String content;
    //创建时间
    private Date cretionDate;
    //创建人
    private String username;
    //创建人ID
    private String usercode;
    //组织号
    private String OrgCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCretionDate() {
        return cretionDate;
    }

    public void setCretionDate(Date cretionDate) {
        this.cretionDate = cretionDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getOrgCode() {
        return OrgCode;
    }

    public void setOrgCode(String orgCode) {
        OrgCode = orgCode;
    }
}
