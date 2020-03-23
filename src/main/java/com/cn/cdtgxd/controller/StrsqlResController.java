package com.cn.cdtgxd.controller;

import com.cn.cdtgxd.pojo.StrsqlRes;
import com.cn.cdtgxd.service.StrsqlResService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/res")
public class StrsqlResController {
    @Autowired
    private StrsqlResService strsqlResService;

    @RequestMapping("/getSqlRes")
    public Page<Object[]> getSqlRes(StrsqlRes strsqlRes, int pageNum, String sortType) {
        return  strsqlResService.findAllPage( strsqlRes,  pageNum,  sortType);
    }
}
