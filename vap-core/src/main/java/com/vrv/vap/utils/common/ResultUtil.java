package com.vrv.vap.utils.common;

import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: liujinhui
 * @Date: 2019/11/9 13:21
 * 进行web结果的包装方法
 * 主要提供成功时的方法success和错误时的error方法
 */

public class ResultUtil {

    private static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    private ResultUtil() {

    }

    public static <T> Result<T> success(T data) {
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static <T> PageResult<T> success(PageInfo<T> pageInfo) {
        PageResult<T> result = new PageResult<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        if (pageInfo != null && pageInfo.getTotal() > 0) {
            result.setTotal(pageInfo.getTotal());
            result.setList(pageInfo.getList());
        }
        if (logger.isDebugEnabled()) {
            //将封装好的数据返回到前台页面， 返回ResponseBody
            logger.debug("总数量：" + pageInfo.getTotal());
            logger.debug("总页数：" + pageInfo.getPages());
            logger.debug("每页显示条数：" + pageInfo.getPageSize());
            logger.debug("当前是第几页：" + pageInfo.getPageNum());
            logger.debug("当前页数据数量：" + pageInfo.getSize());
        }
        return result;
    }

    public static <T> Result<T> error(IResultCode result) {

        return error(result.getCode(), result.getMessage());
    }

    public static <T> Result<T> error(Integer code, String msg) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMessage(msg);
        return res;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMessage(msg);
        res.setData(data);
        return res;
    }
}
