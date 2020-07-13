package com.tulies.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tulies.blog.utils.serializer.PicurlSerializer;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 王嘉炀
 * @date 2019-10-13 14:30
 */
@Data
@Entity
@Table(name = "sso_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String uid;
    private String nickname;
    private String username;
    @JsonIgnore
    private String password;
    @JsonSerialize(using = PicurlSerializer.class)
    private String avatar;
    @JsonIgnore
    private String salt;
    private Integer status;
    @Column(name="admin",columnDefinition="int default 0")
    private Integer admin;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;
}
