package com.tulies.api.beans.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 王嘉炀
 * @date 2020/7/13 6:28 下午
 */
@Data
public class UserCreateForm {
    @NotBlank
    private String alias;
    @NotBlank
    private String username;
    private String password;
    private String salt;
    private Integer status;
}
