package com.cn.cdtgxd.service;

import com.cn.cdtgxd.dao.StrsqlResDao;
import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.pojo.StrsqlRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class StrsqlResService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StrsqlResDao strsqlResDao;

    public void saveStrsqlRes(StrsqlRes strsqlRes){
        strsqlResDao.save(strsqlRes);
    }

    public void delStrsqlRes(){
        strsqlResDao.deleteAllInBatch();
    }

    public List<StrsqlRes> findAll(){
        return  strsqlResDao.findAll();
    }


    //分页查询
    public Page<Object[]> findAllPage(StrsqlRes strsqlRes, int pageNum, String sortType) {

        String strSortType = "checktime";
//        if(!StringUtils.isEmpty(sortType) ){
//            strSortType =  sortType;
//        }
        return strsqlResDao.findAllBack(PageRequest.of(pageNum, 15, Sort.Direction.DESC , strSortType));
    }





}
