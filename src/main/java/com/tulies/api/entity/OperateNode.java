package com.tulies.api.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/31 0:08
 */
@DynamicUpdate
@DynamicInsert
@Data
@Entity
@Table(name = "operate_node")
public class OperateNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer nid;
    private String name;
    private String description;
    // 是否为叶子节点
    private Integer leaf;
    // 序号，用户节点排序
    private Integer orderNo;
    // 中文路径名称 /name/name/name
    private String namePath;
    // nid路径 /nid/nid/nid
    private String nidPath;
    private Integer status;
    // 父节点ID
    private Integer parentNid;
    private Date updateTime;
    private Date createTime;
}
