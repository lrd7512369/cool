package com.lrd.cool.api.service.impl;

import com.lrd.cool.api.entity.User;
import com.lrd.cool.api.repository.UserRepository;
import com.lrd.cool.api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

/**
 * @author lierdong
 * @date 2018-09-04
 */
@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isUserValid(User user) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.caseSensitive())
                .withMatcher("password", ExampleMatcher.GenericPropertyMatchers.caseSensitive());
        return userRepository.exists(Example.of(user, matcher));
    }

    @Override
    public User getUserInfo(String phone) {
        return userRepository.findUserByPhone(phone).withoutSensitiveData();
    }
}
