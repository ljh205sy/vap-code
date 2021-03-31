package com.vrv.vap.utils.common;

/**
 * 统一的返回成功和未知错误定义
 * 其他模块可以自定义自己的枚举范围，只需要实现IResultCode即可。各模块的code可以重复
 *
 * @author find
 */
public enum ResultEnum implements IResultCode {
    Field_VALIDATE_ERROR(-3, "字段验证错误"),
    FORM_VALIDATE_ERROR(-2, "表单验证错误"),
    UNKNOW_FAILED(-1, "服务器异常"),
    SUCCESS(0, "成功"),
    Unauthorized(403, "未授权的请求"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private Integer code;
    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return msg;
    }

}
