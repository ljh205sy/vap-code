package com.vrv.vap.browser.mapper;

import com.vrv.vap.browser.domain.Tclass;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * @author wh1107066
 */
public interface TclassMapper extends MySqlMapper<Tclass>, Mapper<Tclass> {
    /**
     * 递归查找所有
     *
     * @return
     */
     List<Tclass> findAllRecursion();

     Tclass findTclassByParentId(String guid);

    List<Tclass> findAllTclass();

    List<Tclass>  findAllRecursionById(String guid);
}