package com.cn.cdtgxd.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity(name = "check_strsql_log")
public class StrResLog {
    @Id
    private String id;
    private String istype;    //当前状态
    private String starttime;  //开始时间
    private String endtime;  //结束时间
    private String timeconsuming;  //耗时
    private String allsqlnum;   //校检所有个数
    private String errsqlnum;   //错误个数

}
