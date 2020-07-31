package com.tulies.api.beans.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author 王嘉炀
 * @date 2020/7/31 10:58 上午
 */
@Data
public class OperateNodeForm {
    private Integer id;
    private String nid;
    @NotBlank
    private String name;
    private String description;
    // 中文路径名称 /name/name/name
    @NotBlank
    private String namePath;
    // nid路径 /nid/nid/nid
    @NotBlank
    private String nidPath;
    // 父节点ID
    @NotBlank
    private String parentNid;
    private Integer status;
}
