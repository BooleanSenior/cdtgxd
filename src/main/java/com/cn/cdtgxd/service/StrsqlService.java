package com.cn.cdtgxd.service;

import com.cn.cdtgxd.dao.StrsqlDao;
import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.pojo.StrsqlRes;
import com.cn.cdtgxd.util.AnalysisStr;
import com.cn.cdtgxd.util.MyDate;
import com.cn.cdtgxd.util.TopThread;
import org.hibernate.SQLQuery;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static com.cn.cdtgxd.util.MyDate.*;

@Service
public class StrsqlService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private StrsqlDao strsqlDao;

    @Autowired
    private StrsqlResService strsqlResService;
    @Autowired
    private StrsqlResLogService strsqlResLogService;


    private static List<Strsql> strsqls;

    public static int ALL_CHECK_SQL_NUM = 0 ;
    public static int ERR_CHECK_SQL_NUM = 0 ;


    public List<Strsql> findAll(String type) {
        return strsqlDao.findAll();
       // return strsqlDao.getListStrsql(type);
    }

    public Strsql findById(String id) {
        return strsqlDao.findById(id);
    }

    public void saveStrsql(Strsql strsql){
        strsqlDao.save(strsql);
    }

    public Strsql updateOrSave(Strsql strsql) {
        return strsqlDao.saveAndFlush(strsql);
    }

    public int delStrsql(String id) {
        return strsqlDao.delStrsql(id);
    }


    //查询不分页
    public List<Strsql> findAllCondition(String type,String sqlname,String flag){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("select c.id \"id\",c.sqlname  as \"sqlname\" ,c.strsql \"strsql\"" +
                ",c.flag \"flag\",c.remarks \"remarks\",c.type \"type\" " +
                "from check_strsql c where 1=1 ");

        if(!StringUtils.isEmpty(type)){
            stringBuilder.append("and  c.type='"+type+"' ");
        }
        if(!StringUtils.isEmpty(sqlname)){
            stringBuilder.append("and  c.sqlname like '%"+sqlname+"%' ");
        }
        if(!StringUtils.isEmpty(flag)){
            stringBuilder.append("and  c.flag '"+flag+"' ");
        }
        Query query = entityManager.createNativeQuery(String.valueOf(stringBuilder));
        //转成对象

        //转成map
        query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return query.getResultList();
    }

    //分页查询
    public Page<Strsql> findAllPage(Strsql strsql,int pageNum,String sortType) {

        String strSortType = "updatetime";
        if(!StringUtils.isEmpty(sortType) ){
            strSortType =  sortType;
        }

        //规格定义
        Specification<Strsql> specification = new Specification<Strsql>() {

            /**
             * 构造断言
             * @param root 实体对象引用
             * @param query 规则查询对象
             * @param cb 规则构建对象
             * @return 断言
             */
            @Override
            public Predicate toPredicate(Root<Strsql> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(); //所有的断言
                if(!StringUtils.isEmpty(strsql.getSqlname())){ //添加断言
                    Predicate likeName = cb.like(root.get("sqlname").as(String.class),strsql.getSqlname()+"%");
                    predicates.add(likeName);
                }
                if(!StringUtils.isEmpty(strsql.getFlag())){
                    predicates.add(cb.equal(root.get("flag"),strsql.getFlag()));
                }
                if(!StringUtils.isEmpty(strsql.getType())){
                    predicates.add(cb.equal(root.get("type"),strsql.getType()));
                }
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
        //pageNum  初始值是0
        PageRequest pageRequest = PageRequest.of(pageNum, 15, Sort.Direction.DESC , strSortType);
        strsqlDao.findAll(specification,pageRequest);
        return strsqlDao.findAll(specification,pageRequest);
    }


    public String getSqlToCheck() {
        List<Strsql> list = strsqlDao.findAll();

        for (int i = 0; i < list.size(); i++) {
            StrsqlRes strsqlRes = new StrsqlRes();
            strsqlRes.setId(UUID.randomUUID().toString().replace("-", ""));

            String sql = list.get(i).getStrsql();
            Query query = entityManager.createNativeQuery(sql);
            query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List rusult = query.getResultList();

            strsqlRes.setCheckId(list.get(i).getId());
            strsqlRes.setChecktime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

            if (rusult.size() == 1) {
                Map m = (Map) rusult.get(0);

                if (m.get("errorsnum") == null) {
                    //System.out.println("有错误sql的序号："+list.get(i).getId());
                    // strsqlRes.setCheckId(list.get(i).getId());
                    strsqlRes.setCheckSql(list.get(i).getStrsql());
                    strsqlRes.setErrornums(String.valueOf(rusult.size()));
                    strsqlResService.saveStrsqlRes(strsqlRes);
                } else if (Integer.parseInt(String.valueOf(m.get("errorsnum"))) > 0) {
                    //System.out.println("有错误sql的序号："+list.get(i).getId());
                    strsqlRes.setCheckSql(list.get(i).getStrsql());
                    strsqlRes.setErrornums(String.valueOf(m.get("errorsnum")));
                    strsqlResService.saveStrsqlRes(strsqlRes);
                }
            } else if (rusult.size() > 0) {
                //System.out.println("有错误sql的序号："+list.get(i).getId());
                strsqlRes.setCheckSql(list.get(i).getStrsql());
                strsqlRes.setErrornums(String.valueOf(rusult.size()));
                strsqlResService.saveStrsqlRes(strsqlRes);
            }
        }

        return "1";
    }

    public String getSqlToCheckThread(StrResLog strResLog ) throws ParseException {
        strsqls = strsqlDao.getListCheckStrsql();
        //清理反馈表中上一次校验数据
        strsqlResService.delStrsqlRes();
        String successFlag = TopThread.execTopJob();

        String endtime = MyDate.toFormatTime().format(new Date());
        strResLog.setEndtime(endtime);
        strResLog.setIstype("1");
        strResLog.setTimeconsuming(MyDate.toFormatreduce(strResLog.getStarttime(),endtime));
        strResLog.setAllsqlnum(String.valueOf(ALL_CHECK_SQL_NUM));
        strResLog.setErrsqlnum(String.valueOf(ERR_CHECK_SQL_NUM));
        strsqlResLogService.saveStrsqlResLog(strResLog);
        ALL_CHECK_SQL_NUM = 0;ERR_CHECK_SQL_NUM=0;
        return successFlag;
  
    }

    public void getSqlToCheckThreadTo() {
        Strsql strsql = getTopOne();
        while (strsql != null) {
            ALL_CHECK_SQL_NUM ++;
            StrsqlRes strsqlRes = new StrsqlRes();
            strsqlRes.setId(UUID.randomUUID().toString().replace("-", ""));
            String sql = strsql.getStrsql();
            System.out.println(Thread.currentThread().getName()+"--------"+sql);

            Query query = entityManager.createNativeQuery(sql);
           // query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
            List rusult = query.getResultList();

            strsqlRes.setCheckId(strsql.getId());
            strsqlRes.setChecktime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));

            if (rusult.size() == 1) {
                Map m = (Map) rusult.get(0);
                //兼容 count()
                String  value ="";
                for (Object key : m.keySet()) {
                    value = String.valueOf(m.get(key));
                }
                if(AnalysisStr.countstrAnalysis(sql)){
                    if(Integer.parseInt(value) != 0){
                        System.out.println("有错误sql的序号："+strsql.getId());
                        ERR_CHECK_SQL_NUM ++;
                        strsqlRes.setCheckSql(strsql.getStrsql());
                        strsqlRes.setErrornums(value);
                        strsqlResService.saveStrsqlRes(strsqlRes);
                    }
                }else{
                    System.out.println("有错误sql的序号："+strsql.getId());
                    ERR_CHECK_SQL_NUM ++;
                    strsqlRes.setErrornums(value);
                    strsqlResService.saveStrsqlRes(strsqlRes);
                }

                //原来的写法
//                if (m.get("errorsnum") == null) {
//                    System.out.println("有错误sql的序号："+strsql.getId());
//                    strsqlRes.setCheckSql(strsql.getStrsql());
//                    strsqlRes.setErrornums(String.valueOf(rusult.size()));
//                    strsqlResService.saveStrsqlRes(strsqlRes);
//                } else if (Integer.parseInt(String.valueOf(m.get("errorsnum"))) > 0) {
//                    System.out.println("有错误sql的序号："+strsql.getId());
//                    strsqlRes.setCheckSql(strsql.getStrsql());
//                    strsqlRes.setErrornums(String.valueOf(m.get("errorsnum")));
//                    strsqlResService.saveStrsqlRes(strsqlRes);
//                }
            } else if (rusult.size() > 0) {
                System.out.println("有错误sql的序号："+strsql.getId());
                ERR_CHECK_SQL_NUM ++;
                strsqlRes.setCheckSql(strsql.getStrsql());
                strsqlRes.setErrornums(String.valueOf(rusult.size()));
                strsqlResService.saveStrsqlRes(strsqlRes);
            }
            strsql.setChecktime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
            this.updateOrSave(strsql);
            strsql = getTopOne();
        }
    }


    private synchronized Strsql getTopOne() {
        Iterator<Strsql> iter = strsqls.iterator();
        Strsql strsql = null;
        if (iter.hasNext()) {
            strsql = iter.next();
            iter.remove();
        } else {
            System.out.println("所有列表内sql已执行完成！");
        }

        return strsql;
    }
}
