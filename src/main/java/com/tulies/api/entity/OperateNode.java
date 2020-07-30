package com.tulies.api.entity;

import lombok.Data;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/31 0:08
 */
@Data
public class OperateNode {
    private Integer id;
    private String nid;
    private String name;
    private String description;
    // 父节点名称
    private String parentId;
    // 中文路径名称 /name/name/name
    private String namePath;
    // nid路径 /nid/nid/nid
    private String nidPath;
}
