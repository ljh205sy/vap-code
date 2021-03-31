package com.vrv.vap.utils.common;

/**
 * 结果编码接口
 * 在各个模块中可以自定义自己的ResultCodeEnum
 *
 * @author find
 */
public interface IResultCode {
    Integer getCode();

    String getMessage();
}
