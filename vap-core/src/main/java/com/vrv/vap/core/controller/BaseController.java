package com.vrv.vap.core.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.core.common.Query;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/4/2 19:05
 */
public abstract class BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private static final List<String> criteriaBy = Arrays.asList("asc", "desc");

    protected void orderBy(Example example, Query query) {
        if (StringUtils.isNotEmpty(query.getOrder()) && StringUtils.isNotEmpty(query.getBy()) && criteriaBy.contains(query.getBy().toLowerCase())) {
            String order = query.getOrder();
            example.setOrderByClause(order + "  " + query.getBy());
        }
    }

    protected Example.Criteria createCriteria(Class<?> clazz) {
        Example example = new Example(clazz);
        Example.Criteria criteria = example.createCriteria();
        return criteria;
    }

    protected void printPageInfo(PageInfo pageInfo){
        logger.debug("查询总数量：" + pageInfo.getTotal());
        logger.debug("总页数：" + pageInfo.getPages());
        logger.debug("每页显示条数：" + pageInfo.getPageSize());
        logger.debug("当前是第几页：" + pageInfo.getPageNum());
        logger.debug("当前页数据数量：" + pageInfo.getSize());
    }

}
