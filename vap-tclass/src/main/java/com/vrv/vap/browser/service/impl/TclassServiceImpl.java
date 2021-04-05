package com.vrv.vap.browser.service.impl;

import com.vrv.vap.browser.domain.Tclass;
import com.vrv.vap.browser.mapper.TclassMapper;
import com.vrv.vap.browser.service.TclassService;
import com.vrv.vap.core.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liujinhui
 * date 2021/4/2 0:04
 */
@Service
public class TclassServiceImpl extends BaseServiceImpl<Tclass,String> implements TclassService {

    @Resource
    private TclassMapper tclassMapper;


}
