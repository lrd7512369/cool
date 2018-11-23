package com.lrd.cool.api.service;

import com.lrd.cool.api.entity.User;

/**
 * @author lierdong
 */
public interface IUserService {

    /**
     * 判断该用户是否可以登录
     *
     * @param user 登录的用户信息
     * @return 是否可以登录
     */
    boolean isUserValid(User user);

    /**
     * 获取用户详细信息
     *
     * @param phone 用户手机号
     * @return 用户信息
     */
    User getUserInfo(String phone);

}
