package com.tulies.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    private String alias;
    private String username;
    @JsonIgnore
    private String password;
    @JsonIgnore
    private String salt;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
