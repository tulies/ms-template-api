package com.tulies.api.beans.qo;

import lombok.Data;
/**
 * @author 王嘉炀 346461062@qq.com
 * @date 2020/7/30 23:45
 */
@Data
public class OperateContentQO {
    private Integer id;
    // 内容id
    private String cid;
    // 标题
    private String name;
    // 状态
    private String status;

    // 数据路径
    private String dataPath;
    // 所属的节点id
    private Integer nid;
    private String idPath;

    // json模板id
    private String tid;
    private String tplPath;
}
