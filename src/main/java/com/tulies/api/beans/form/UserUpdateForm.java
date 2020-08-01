package com.tulies.api.beans.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 王嘉炀
 * @date 2020/7/13 6:28 下午
 */
@Data
public class UserUpdateForm {
    private Integer id;
    private String uid;
    private String alias;
    private String username;
    private Integer status;
}
