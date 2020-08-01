package com.tulies.api.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/31 22:34
 */
@DynamicUpdate
@DynamicInsert
@Data
@Entity
@Table(name = "generator")
public class Generator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String key;
    private Integer value;
}
