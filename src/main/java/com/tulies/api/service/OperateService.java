package com.tulies.api.service;


import com.tulies.api.beans.form.OperateNodeForm;
import com.tulies.api.beans.qo.OperateNodeQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.entity.OperateNode;

/**
 * @author 王嘉炀
 * @date 2019-10-12 00:07
 */
public interface OperateService {
    PageVO<OperateNode> findNodeList(Integer pageNum, Integer pageSize, OperateNodeQO operateNodeQO, String sorter);

    OperateNode findNodeById(Integer id);
    OperateNode createNode(OperateNodeForm operateNodeForm);
    OperateNode updateNode(OperateNodeForm operateNodeForm);
    void deleteNode(Integer id);
    //上下线
    void changeStatus(Integer id, Integer status);
}
