package com.lrd.cool.api.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author lierdong
 * @date 2018-09-04
 */
@Entity
@Table(name = "tb_user")
public class User implements Serializable {

    /**
     * 用户id
     */
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    /**
     * 用户昵称
     */
    @Column(name = "nickname")
    private String nickname;

    /**
     * 用户手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 用户状态
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 头像
     */
    @Column(name = "avatar")
    private String avatar;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public User withoutSensitiveData() {
        userId = null;
        password = null;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", nickname='" + nickname + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
