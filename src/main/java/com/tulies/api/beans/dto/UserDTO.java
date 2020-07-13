package com.tulies.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.tulies.blog.utils.serializer.PicurlSerializer;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 王嘉炀
 * @date 2019-10-24 12:44
 */
@Data
public class UserDTO  implements Serializable {
    private static final long serialVersionUID = 8785138549708742519L;

    private Integer id;
    private String userToken;
    private String uid;
    private String nickname;
    private String username;
    @JsonSerialize(using = PicurlSerializer.class)
    private String avatar;
    private Integer status;
    private Integer admin;
    private Date lastLoginTime;
    private Date createTime;
    private Date updateTime;
}
