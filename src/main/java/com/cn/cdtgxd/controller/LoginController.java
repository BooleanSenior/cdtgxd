package com.cn.cdtgxd.controller;

import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.service.StrsqlResLogService;
import com.cn.cdtgxd.service.StrsqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController

public class LoginController {


    @Autowired
    private StrsqlService strsqlService;
    @Autowired
    private StrsqlResLogService strsqlResLogService;


    @RequestMapping("/isNowCheckBack")
    public Map<String,String> isNowCheckBack(){
        Map<String,String> map = new HashMap<>();
        map.put("code","1000");
        return map;
    }


    /**
     * 多线程数据校验接口
     * 校检注意事项：
     *      1. 若返回为数据集合，建议指定列名只查一个字段
     *
     * @return
     */
    @RequestMapping("/getSqlToCheckThread")
    public Map<String, Object>  getSqlToCheckThread(StrResLog strResLog ) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println(strResLog.getId()+"-------------=============-");
        if(strsqlService.getSqlToCheckThread(strResLog).equals("1")){
            map.put("strResLog",strsqlResLogService.findByIdStrsqlResLog(strResLog.getId())) ;

        }
        map.put("code","1");
        return map;
    }

    @RequestMapping("/findStrsqlResLogdesc")
    public List<StrResLog> findStrsqlResLogdesc() throws ParseException {
        return strsqlResLogService.findStrsqlResLogdesc();
    }

    @RequestMapping("/stopCheck")
    public  Map<String, Object>  stopCheck(StrResLog strResLog) throws ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        strResLog.setIstype("1");
        strsqlResLogService.saveStrsqlResLog(strResLog);
        map.put("code","1");
        return map;

    }

}
