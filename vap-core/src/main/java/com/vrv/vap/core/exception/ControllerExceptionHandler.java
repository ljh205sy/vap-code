package com.vrv.vap.core.exception;

import com.vrv.vap.core.common.Result;
import com.vrv.vap.core.common.ResultEnum;
import com.vrv.vap.core.common.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;
import java.util.List;

/**
 * @author liujinhui
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<List<ObjectError>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("后台验证字段不符合要求!!", e);
        BindingResult result = e.getBindingResult();
        List<ObjectError> list = Collections.emptyList();
        if (result.hasErrors()) {
            list = result.getAllErrors();
            for (ObjectError objectError : list) {
                FieldError error = (FieldError) objectError;
                String field = error.getField();
                String defaultMessage = error.getDefaultMessage();
                String objectName = error.getObjectName();
                logger.error(objectName + "" + field + "" + defaultMessage);
            }
        }
        return ResultUtil.error(ResultEnum.Field_VALIDATE_ERROR.getCode(), ResultEnum.Field_VALIDATE_ERROR.getMessage(), list);
    }
}