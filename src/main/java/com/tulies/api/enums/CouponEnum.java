package com.tulies.api.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CouponEnum {

    TYPE_ACTUAL(0,"实物券"),
    TYPE_INVENTED(1,"虚拟券"),
    TYPE_OTHER(2,"第三方券"),


    HAS_RECEIVE(1,"已领取"),
    NO_RECEIVE(0,"未领取"),
    ;

    private Integer code;
    private String msg;
}
