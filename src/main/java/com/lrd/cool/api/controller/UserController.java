package com.lrd.cool.api.controller;

import com.lrd.cool.api.entity.User;
import com.lrd.cool.api.response.ApiResult;
import com.lrd.cool.api.service.IUserService;
import com.lrd.cool.api.utils.ApiUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lierdong
 * @date 2018-09-04
 */
@RestController
public class UserController {

    private final IUserService service;

    @Autowired
    public UserController(IUserService service) {
        this.service = service;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ApiResult login(@RequestParam(name = "phone") String phone
            , @RequestParam(name = "password") String password
            , HttpServletRequest request) {
        User user = new User();
        user.setPhone(phone);
        user.setPassword(password);
        if (service.isUserValid(user)) {
            request.getSession().setAttribute("user", service.getUserInfo(phone));
            return ApiUtils.getResultForSuccessOperation("登录成功");
        } else {
            return ApiUtils.getResultForInvalidParams("账号或者密码错误");
        }
    }
}
