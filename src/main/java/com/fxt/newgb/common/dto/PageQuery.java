package com.fxt.newgb.common.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public class PageQuery {
    
    @Min(value = 1, message = "页码最小为1")
    private Integer current = 1;
    
    @Min(value = 1, message = "每页条数最小为1")
    @Max(value = 100, message = "每页条数最大为100")
    private Integer size = 10;
    
    public Integer getCurrent() {
        return current;
    }
    
    public void setCurrent(Integer current) {
        this.current = current;
    }
    
    public Integer getSize() {
        return size;
    }
    
    public void setSize(Integer size) {
        this.size = size;
    }
    
    public long getOffset() {
        return (long) (current - 1) * size;
    }
}
