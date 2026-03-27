package com.fxt.newgb.common.response;

import java.io.Serializable;

public class R<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Integer code;
    private String message;
    private T data;
    private Long timestamp;
    
    public R() {
        this.timestamp = System.currentTimeMillis();
    }
    
    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }
    
    public static <T> R<T> success() {
        return new R<>(200, "success", null);
    }
    
    public static <T> R<T> success(T data) {
        return new R<>(200, "success", data);
    }
    
    public static <T> R<T> success(String message, T data) {
        return new R<>(200, message, data);
    }
    
    public static <T> R<T> error(Integer code, String message) {
        return new R<>(code, message, null);
    }
    
    public static <T> R<T> error(String message) {
        return new R<>(500, message, null);
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public T getData() {
        return data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public Long getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
    
    public boolean isSuccess() {
        return Integer.valueOf(200).equals(this.code);
    }
}
