package com.tulies.api.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/30 23:45
 */
@DynamicUpdate
@DynamicInsert
@Data
@Entity
@Table(name = "operate_content")
public class OperateContent {
    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    // 内容id
    private String cid;
    // 标题
    private String name;
    // 描述
    private String description;
    // 状态
    private Integer status;

    // 数据路径
    private String dataPath;
    // 所属的节点id
    private Integer nid;
    private String idPath;

    // json模板id
    private Integer tid;
    private String tplPath;

    private Date updateTime;
    private Date createTime;

}
