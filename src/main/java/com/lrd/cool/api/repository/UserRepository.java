package com.lrd.cool.api.repository;

import com.lrd.cool.api.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author lierdong
 */
public interface UserRepository extends JpaRepository<User, Long>
        , JpaSpecificationExecutor<User> {

    /**
     * 根据手机号获取用户信息
     *
     * @param phone 用户手机号
     * @return 用户信息
     */
    public User findUserByPhone(String phone);

}
