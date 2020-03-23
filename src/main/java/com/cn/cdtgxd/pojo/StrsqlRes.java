package com.cn.cdtgxd.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "check_strsql_res")
public class StrsqlRes {
    @Id
    private String id;

    @Column(name="checkid")
    private String checkId;

    @Column(name="checksql")
    private String checkSql;

    private String errornums;
    private String checktime;

    private String sqlname;    //名称
    private String remarks;  //备注
    private String type;  //类型


    public StrsqlRes(){

    }

    public StrsqlRes(String checkId,String sqlname, String remarks,String type,String errornums){
        this.checkId = checkId;
        this.sqlname = sqlname;
        this.remarks = remarks;
        this.type = type;
        this.errornums = errornums;

    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckId() {
        return checkId;
    }

    public void setCheckId(String checkId) {
        this.checkId = checkId;
    }

    public String getCheckSql() {
        return checkSql;
    }

    public void setCheckSql(String checkSql) {
        this.checkSql = checkSql;
    }

    public String getErrornums() {
        return errornums;
    }

    public void setErrornums(String errornums) {
        this.errornums = errornums;
    }

    public String getChecktime() {
        return checktime;
    }

    public void setChecktime(String checktime) {
        this.checktime = checktime;
    }
}
