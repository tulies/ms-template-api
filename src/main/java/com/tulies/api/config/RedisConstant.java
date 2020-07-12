package com.tulies.api.config;

/**
 * @author 王嘉炀
 * @date 2018/7/11 下午10:34
 */
public interface RedisConstant {

    Integer EXPIRE = 7200; //2小时

    // TOKEN的前缀
    String TOKEN_PREFIX = "sso:usertoken:%s";

}
