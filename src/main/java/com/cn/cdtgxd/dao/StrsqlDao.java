package com.cn.cdtgxd.dao;


import com.cn.cdtgxd.pojo.Strsql;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;


@Component
public interface StrsqlDao extends JpaRepository<Strsql,Long> {

    @Query("select c from check_strsql c where 1=1 and c.type=?1 ")
    public List<Strsql> getListStrsql(String type);

    @Query("select c from check_strsql c where 1=1 and c.flag='2' ")
    public List<Strsql> getListCheckStrsql();

    @Query("select c from check_strsql c where 1=1 and c.id=?1 ")
    public Strsql findById(String id);


    @Transactional
    @Modifying
    @Query("delete from check_strsql  where id=?1 ")
    public int delStrsql(String id);



    Page<Strsql> findAll(Specification<Strsql> spec, Pageable pageAble);
}
