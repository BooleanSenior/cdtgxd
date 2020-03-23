package com.cn.cdtgxd.util;

public class AnalysisStr {
    public static String  fhAnalysis(String str){
        boolean status = str.contains(";");
        if(status){
            String str1 = str.substring(0, str.indexOf(";"));
            return str1;
        }else{
            return str;
        }
    }

    public static boolean  countstrAnalysis(String str){
        boolean status = str.contains("count");
        return status;
    }

}
