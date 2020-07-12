package com.tulies.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CommEnum {

    /**上下线****/
    STATUS_NEW_BUILD(0,"新建"),//新建
    STATUS_ONLINE(1,"上线"), //上线
    STATUS_OFFLINE(2,"下线"),//下线

    /***搜索***/
    CAN_SEARCHABLE(1,"可被搜索"),//可被搜索

    /***审核****/
    CHECK_POST(0,"先审后发"), //先审后发
    POST_CHECK(1,"先发后审"), //先发后审



    ;
    private Integer code;
    private String desc;


}
