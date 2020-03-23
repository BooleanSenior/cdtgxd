package com.cn.cdtgxd.util;

import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.service.StrsqlResLogService;
import com.cn.cdtgxd.service.StrsqlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class TopThread extends Thread {

    @Autowired
    private  StrsqlService strsqlService;
    @Autowired
    private StrsqlResLogService strsqlResLogService;

    private static TopThread topThread;

    public void setStrsqlService(StrsqlService strsqlService) {
        this.strsqlService = strsqlService;
    }
    public void strsqlResLogService(StrsqlResLogService strsqlResLogService) {
        this.strsqlResLogService = strsqlResLogService;
    }
    @PostConstruct
    public void init() {
        topThread = this;
        topThread.strsqlService = this.strsqlService;
        topThread.strsqlResLogService = strsqlResLogService;
    }

    @Override
    public void run() {
       // super.run();
        topThread.strsqlService.getSqlToCheckThreadTo();
    }

    public static  String execTopJob(){
        ExecutorService fixedThreadPool= Executors.newFixedThreadPool(2);
        Thread t2 = new TopThread();
        Thread t3=new TopThread();
        fixedThreadPool.execute(t2);
        fixedThreadPool.execute(t3);

        fixedThreadPool.shutdown();
        while (true) {
            if (fixedThreadPool.isTerminated()) {
                System.out.println("结束了！");
                return "1";
                //break;
            }
        }
    }

    @Scheduled(cron = "0 20 20 ?  *  *")
    public void getSqlToCheckThreadScheduled() throws ParseException {
        StrResLog strResLog = new StrResLog();
        strResLog.setId(UUID.randomUUID().toString().replace("-", ""));
        strResLog.setStarttime(MyDate.toFormatTime().format(new Date()));
        strResLog.setIstype("0");
        strResLog =  strsqlResLogService.saveStrsqlResLog(strResLog);
        topThread.strsqlService.getSqlToCheckThread(strResLog);
    }
}
