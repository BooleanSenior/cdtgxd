package com.cn.cdtgxd;

import com.cn.cdtgxd.dao.StrsqlResDao;
import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.pojo.StrsqlRes;
import com.cn.cdtgxd.service.StrsqlResLogService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootTest

class CdtgxdApplicationTests {

    @Autowired
    private StrsqlResDao strsqlResDao;
    @Autowired
    StrsqlResLogService strsqlResLogService;
    @Test
    void contextLoads() {
    }

    @Test
    public void test(){


        System.out.println( strsqlResLogService.findAlldesc().get(0));

    }

}
