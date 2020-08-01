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
//    private Integer nid;
    @NotBlank(message = "name不能为空")
    private String name;
    private String description;
    private Integer orderNo;
    // 中文路径名称 /name/name/name
//    private String namePath;
    // nid路径 /nid/nid/nid
//    private String nidPath;
    // 父节点ID
//    @NotNull(message = "parentNid不能为空")
    private Integer parentNid;
//    private Integer status;
}
