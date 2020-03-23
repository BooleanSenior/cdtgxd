package com.cn.cdtgxd.util.exception;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义异常
 */
public class AuthorizeException extends RuntimeException{
    private static final long serialVersionUID = -8581672033133636908L;
    /*** 错误码枚举*/
    private ErrorCodeEnum errorCode;
    /**
     * 详细错误信息
     */
    private Map<String, String> errorMap = new HashMap<String, String>();

    /**
     * 无参构造器。
     */
    public AuthorizeException() {

    }

    /**
     * 带参构造器.
     *
     * @param errorCode
     */
    public AuthorizeException(ErrorCodeEnum errorCode) {
        super(errorCode.getDesc());
        this.setErrorCode(errorCode);
    }
    /**
     * 带参构造器.自定义message
     *
     * @param errorCode
     * @param message
     */
    public AuthorizeException(ErrorCodeEnum errorCode, String message) {
        super(StringUtils.isNotBlank(message) ? message : errorCode.getDesc());
        this.setErrorCode(errorCode);
    }

    /**
     * 带参构造器.
     *
     * @param errorCode
     * @param errorMap
     */
    public AuthorizeException(ErrorCodeEnum errorCode, Map<String, String> errorMap) {
        this(errorCode);
        this.errorMap = errorMap;
    }
    /**
     * 带参构造器.
     *
     * @param message
     */
    public AuthorizeException(String message) {
        super(message);
        this.setErrorCode(ErrorCodeEnum.UNKNOWN_ERROR);
    }
    /**
     * Gets error code.
     *
     * @return the error code
     */
    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }
    /**
     * Sets error code.
     *
     * @param errorCode the error code
     */
    public void setErrorCode(ErrorCodeEnum errorCode) {
        this.errorCode = errorCode;
    }
    /**
     * Gets error map.
     *
     * @return the error map
     */
    public Map<String, String> getErrorMap() {
        return errorMap;
    }
    /**
     * Sets error map.
     *
     * @param errorMap the error map
     */
    public void setErrorMap(Map<String, String> errorMap) {
        this.errorMap = errorMap;
    }
    private static String findMessage(Map<String, String> errorMap) {
        if (errorMap.isEmpty()) {
            return null;
        }
        return errorMap.values().iterator().next();
    }



}
