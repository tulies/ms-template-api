package com.tulies.api.beans.qo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 王嘉炀
 * @date 2020/7/31 3:43 下午
 */
@Data
public class OperateNodeQO {
    private Integer id;
    private String nid;
    private String name;
    private String description;
    // 中文路径名称 /name/name/name
    private String namePath;
    // nid路径 /nid/nid/nid
    private String nidPath;
    // 父节点ID
    private String parentNid;

    // 用字符串
    private String status;

}
