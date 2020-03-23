package com.cn.cdtgxd.service;

import com.cn.cdtgxd.dao.StrResLogDao;
import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.pojo.StrsqlRes;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Service
public class StrsqlResLogService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private StrResLogDao strResLogDao;

    public StrResLog saveStrsqlResLog(StrResLog strResLog){

       return strResLogDao.save(strResLog);
    }
    public StrResLog findByIdStrsqlResLog(String id){
       return strResLogDao.findById(id);
    }


    //查询最新一条istype
    public List<StrResLog> findAlldesc(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c.istype from check_strsql_log c ORDER BY STARTTIME DESC ");
        Query query = entityManager.createNativeQuery(String.valueOf(stringBuilder));
       // query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }
    //查询所有
    public List<StrResLog> findStrsqlResLogdesc(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT c.id \"id\",c.istype \"istype\",c.starttime \"starttime\"," +
                "c.endtime \"endtime\",c.timeconsuming  \"timeconsuming\" " +
                ",c.allsqlnum  \"allsqlnum\",c.errsqlnum  \"errsqlnum\"" +
                " from check_strsql_log c ORDER BY STARTTIME DESC ");
        Query query = entityManager.createNativeQuery(String.valueOf(stringBuilder));
         query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }


}
