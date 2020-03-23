package com.cn.cdtgxd.controller;

import com.cn.cdtgxd.pojo.StrResLog;
import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.service.StrsqlResLogService;
import com.cn.cdtgxd.service.StrsqlService;
import com.cn.cdtgxd.util.AnalysisStr;
import com.cn.cdtgxd.util.MyDate;
import com.cn.cdtgxd.util.exception.AuthorizeException;
import com.cn.cdtgxd.util.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
public class StrsqlController {

    @Autowired
    private StrsqlService strsqlService;
    @Autowired
    private StrsqlResLogService strsqlResLogService;



    @RequestMapping("/findAll")
    public List<Strsql> findAll(String type) {
        return strsqlService.findAll(type);
    }

    @RequestMapping("/findAllCondition")
    public List<Strsql> findAllCondition(String type,String sqlname,String flag){
        return strsqlService.findAllCondition(type,sqlname,flag);
    }

    @RequestMapping("/findById")
    public Strsql findById(String id) {
        return strsqlService.findById(id);
    }

    @RequestMapping("/delStrsql")
    public int delStrsql(String id) {
        return strsqlService.delStrsql(id);
    }


    @RequestMapping("/saveStrsql")
    public String  saveStrsql(Strsql strsql ,HttpServletRequest request){
        String timeNow = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
        String sqlstrReq = request.getParameter("requestStrsql");
        String sqlstr = AnalysisStr.fhAnalysis(sqlstrReq);
        if(StringUtils.isEmpty(strsql.getId())){
            strsql.setId(UUID.randomUUID().toString().replace("-", ""));
            strsql.setCreatetime(timeNow);
            strsql.setFlag("2");
        }else{
            strsql.setCreatetime(strsqlService.findById(strsql.getId()).getCreatetime());
        }
        strsql.setStrsql(sqlstr);
        strsql.setUpdatetime(timeNow);
        //strsqlService.saveStrsql(strsql);
        strsqlService.updateOrSave(strsql);
        return "1";
    }

    /**
     * 单线程数据校验接口
     * @return
     */
    @RequestMapping("/getSqlToCheck")
    public String getSqlToCheck(){
        strsqlService.getSqlToCheck();
        return "1";
    }




    /**
     * 分页调用
     */
    @RequestMapping("/findAllPage")
    public Page<Strsql> findAllPage(Strsql strsql, int pageNum,String sortType)  {
        return strsqlService.findAllPage(strsql,pageNum,sortType);
    }
}
