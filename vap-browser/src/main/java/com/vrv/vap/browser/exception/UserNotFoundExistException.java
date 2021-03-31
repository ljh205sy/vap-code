package com.vrv.vap.browser.exception;

/**
 * @author liujinhui
 * date 2021/3/30 21:03
 */
public class UserNotFoundExistException extends RuntimeException {

    private Integer uid;

    public UserNotFoundExistException(Integer  uid){
        super("用户不存在！");
        this.uid = uid;
    }

}
