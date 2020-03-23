package com.cn.cdtgxd;

import com.cn.cdtgxd.util.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CdtgxdApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdtgxdApplication.class, args);
        //Test.mytest();
    }

}
