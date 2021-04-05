package com.vrv.vap.core.common;

import java.io.Serializable;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/31 14:36
 */
public class PageResult<T> implements Serializable {

    private int code;

    private String message;

    private long total;

    private List<T> list;

    /**
     * 默认构造
     */
    public PageResult() {
    }

    public PageResult SUCCESS(List<T> rows, long total) {
        this.list = rows;
        this.total = total;
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        return this;
    }

    public PageResult SUCCESS(int code, List<T> rows, long total) {
        this.list = rows;
        this.total = total;
        this.code = code;
        this.message = ResultEnum.SUCCESS.getMessage();
        return this;
    }

    public PageResult SUCCESS(List<T> rows, long total, Integer pageNum, Integer pageSize) {
        this.list = rows;
        this.total = rows.size();
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();
        return this;
    }

    public PageResult SUCCESS(List<T> rows) {
        this.list = rows;
        this.total = rows.size();
        this.code = ResultEnum.SUCCESS.getCode();
        this.message = ResultEnum.SUCCESS.getMessage();

        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setData(List<T> data) {
        this.list = data;
        this.total = data.size();
    }

    public PageResult error(int code, String message) {
        this.list = null;
        this.total = 0;
        this.code = code;
        this.message = message;
        return this;
    }
}