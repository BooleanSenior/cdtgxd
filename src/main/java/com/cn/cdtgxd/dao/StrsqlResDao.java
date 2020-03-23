package com.cn.cdtgxd.dao;

import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.pojo.StrsqlRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public interface StrsqlResDao extends JpaRepository<StrsqlRes,Long> {

    @Query("select new com.cn.cdtgxd.pojo.StrsqlRes(c1.checkId,c2.sqlname,c2.remarks,c2.type,c1.errornums) from check_strsql_res c1 left join \n" +
            "check_strsql c2\n" +
            "on c1.checkId = c2.id\n" +
            " where 1=1 \n" +
            "and c2.flag='2' ")
    Page<Object[]> findAllBack(Pageable pageAble);

}
