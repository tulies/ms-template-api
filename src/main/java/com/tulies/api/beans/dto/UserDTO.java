package com.tulies.api.beans.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王嘉炀
 * @date 2019-10-24 12:44
 */
@Data
public class UserDTO{
    private Integer id;
    private String userToken;
    private String uid;
    private String alias;
    private String username;
    private Integer status;
    private Date createTime;
    private Date updateTime;
}
