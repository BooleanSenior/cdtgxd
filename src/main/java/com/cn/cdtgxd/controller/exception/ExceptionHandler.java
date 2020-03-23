package com.cn.cdtgxd.controller.exception;

import com.cn.cdtgxd.util.exception.AuthorizeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class  ExceptionHandler {


    //拦截异常
    @org.springframework.web.bind.annotation.ExceptionHandler(value =  AuthorizeException.class)
    public String handlerAuthorizeException(){


        return "redirect:/isNowCheckBack";
    }

}
