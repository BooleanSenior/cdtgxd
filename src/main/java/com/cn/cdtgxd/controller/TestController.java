package com.cn.cdtgxd.controller;

import com.cn.cdtgxd.pojo.Strsql;
import com.cn.cdtgxd.pojo.StrsqlRes;
import com.cn.cdtgxd.service.StrsqlResService;
import com.cn.cdtgxd.service.StrsqlService;
import com.cn.cdtgxd.util.exception.AuthorizeException;
import com.cn.cdtgxd.util.exception.ErrorCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
public class TestController {

    @Autowired
    private StrsqlResService strsqlResService;

    @Autowired
    private StrsqlService strsqlService;

    @RequestMapping("/")
    public Object i(HttpServletRequest request) {
        Map<String, Object> res = new HashMap<String, Object>();
        res.put("ip:", request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getRequestURI());
        return res;

    }


    @RequestMapping("/saveStrsqlRes")
    public int  saveStrsqlRes() {

        StrsqlRes s = new StrsqlRes();
        s.setId(UUID.randomUUID().toString().replace("-", ""));
        s.setCheckId(UUID.randomUUID().toString().replace("-", ""));
        s.setCheckSql("select * from t_inv_personal t where t.PERSONAL_ID ='130123194810016010'");
        s.setErrornums("123");
        s.setChecktime("2020-02-05");
        strsqlResService.saveStrsqlRes(s);

        return 1;

    }

    /**
     * 多线程数据校验接口
     * @return
     */
//    @RequestMapping("/getSqlToCheckThread")
//    public int   getSqlToCheckThread() {
//        strsqlService.getSqlToCheckThread();
//        return 1;
//    }


    @RequestMapping("/getSqlRes")
    public Page<Object[]>  getSqlRes(StrsqlRes strsqlRes, int pageNum, String sortType) {

        return  strsqlResService.findAllPage( strsqlRes,  pageNum,  sortType);
    }


    @RequestMapping("/test")
    public Map<String,String> test(){
        System.out.println("-----test---------来了--------------");
        //        System.out.println("--------------来了");
       Map<String,String> map = new HashMap<>();
     map.put("1","1");
        return map;
    }

    /**
     * 分页调用,枚举与自定义异常
     */
    @RequestMapping("/findAllPage1")
    public Map<String,Object> findAllPage1(Strsql strsql, int pageNum,String sortType) {
        Map<String, String> maperror = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        try {
            if (StringUtils.isEmpty(pageNum))
                //定义简略错误信息
                throw new AuthorizeException(ErrorCodeEnum.ILLEGAL_ARGS);
            if (StringUtils.isEmpty(sortType)){
                //定义详细错误信息
                maperror.put("1","1");
                maperror.put("2","2");
                throw new AuthorizeException(ErrorCodeEnum.ILLEGAL_ARGS,maperror);
            }

            map.put("list", strsqlService.findAllPage(strsql, pageNum, sortType));
            return map;
        } catch (AuthorizeException e) {
            e.printStackTrace();
            System.out.println("异常码：" + e.getErrorCode().getCode());
            System.out.println("异常描述：" + e.getMessage());
            map.put("code", e.getErrorCode().getCode());
            map.put("msg", e.getErrorMap());
            return map;
        }
    }

}
