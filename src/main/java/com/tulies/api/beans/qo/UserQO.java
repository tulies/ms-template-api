package com.tulies.api.beans.qo;

import lombok.Data;

@Data
public class UserQO {
    private Integer id;
    private String uid;
    private String alias;
    private String username;
    private String status;
    private String password;
}
