package com.cn.cdtgxd.controller;

import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.service.StrsqlResLogService;
import com.cn.cdtgxd.util.MyDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/reslog")
public class StrsqlResLogController {

    @Autowired
    private StrsqlResLogService strsqlResLogService;


    @RequestMapping("/saveStrsqlResLog")
    public  StrResLog saveStrsqlResLog(){
        StrResLog strResLog = new StrResLog();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        strResLog.setId(uuid);
        strResLog.setStarttime(MyDate.toFormatTime().format(new Date()));
        strResLog.setIstype("0");
        strResLog =  strsqlResLogService.saveStrsqlResLog(strResLog);
        return  strResLog;
    }
}
