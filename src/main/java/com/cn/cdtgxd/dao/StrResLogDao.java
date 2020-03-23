package com.cn.cdtgxd.dao;

import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.pojo.Strsql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StrResLogDao extends JpaRepository<StrResLog,Long> {

    @Query("select c from check_strsql_log c where 1=1 and c.id=?1 ")
    public StrResLog findById(String id);

}
