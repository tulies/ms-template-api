package com.tulies.api.service.impl;

import com.tulies.api.beans.form.OperateNodeForm;
import com.tulies.api.beans.qo.OperateNodeQO;
import com.tulies.api.beans.qo.UserQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.config.RedisConstant;
import com.tulies.api.entity.OperateNode;
import com.tulies.api.entity.User;
import com.tulies.api.enums.CommEnum;
import com.tulies.api.enums.IdKeyEnum;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.repository.OperateNodeRepository;
import com.tulies.api.service.GeneratorService;
import com.tulies.api.service.OperateService;
import com.tulies.api.utils.*;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author 王嘉炀
 * @date 2019-10-12 00:07
 */
@Slf4j
@Service
public class OperateServiceImpl implements OperateService {

    @Autowired
    private OperateNodeRepository operateNodeRepository;
    @Autowired
    private GeneratorService generatorService;

    @Override
    public PageVO<OperateNode> findNodeList(Integer pageNum, Integer pageSize, OperateNodeQO operateNodeQO, String sorter) {
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        if(StringUtils.isNotBlank(sorter)){
            sort = CommUtil.formatSorter(sorter);
        }
        Pageable pageable = PageRequest.of(pageNum, pageSize, sort);

        Specification<User> specification = (Specification<User>) (root, criteriaQuery, criteriaBuilder) -> {

            List<Predicate> predicateList = new ArrayList<>();
            //根据id 查询
            if (operateNodeQO.getId() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("id").as(Integer.class), operateNodeQO.getId()));
            }

            //根据nid 查询
            if (operateNodeQO.getNid() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("nid").as(Integer.class), operateNodeQO.getNid()));
            }
            //根据parentNid 查询
            if (operateNodeQO.getParentNid() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("parentNid").as(Integer.class), operateNodeQO.getParentNid()));
            }

            //根据name 模糊匹配
            if (StringUtils.isNotBlank(operateNodeQO.getName())) {
                predicateList.add(criteriaBuilder.like(root.get("name").as(String.class), "%"+operateNodeQO.getName()+"%"));
            }
            //根据namePath 模糊匹配
            if (StringUtils.isNotBlank(operateNodeQO.getNamePath())) {
                predicateList.add(criteriaBuilder.like(root.get("namePath").as(String.class), "%"+ operateNodeQO.getNamePath()+"%"));
            }

            // 根据状态查询
            if (StringUtils.isNotBlank(operateNodeQO.getStatus())) {
                String[] statusArr= operateNodeQO.getStatus().split(",");
                if(statusArr.length > 1){
                    CriteriaBuilder.In<Integer> in = criteriaBuilder.in(root.get("status"));
                    for (int i = 0; i < statusArr.length; i++){
                        in.value(Integer.valueOf(statusArr[i]));
                    }
                    predicateList.add(in);
                }else{
                    predicateList.add(criteriaBuilder.equal(root.get("status").as(Integer.class), operateNodeQO.getStatus()));
                }
            }else{
                predicateList.add(criteriaBuilder.notEqual(root.get("status").as(Integer.class), -1));
            }

            Predicate[] pre = new Predicate[predicateList.size()];
            criteriaQuery.where(predicateList.toArray(pre));
            return criteriaBuilder.and(predicateList.toArray(pre));
        };

        Page<OperateNode> page = operateNodeRepository.findAll(specification,pageable);
        PageVO<OperateNode> pageVO = PageVO.convert(page);
        return pageVO;
    }

    /**
     * 根据id查询文章
     * @param id
     * @return
     */
    @Override
    public OperateNode findNodeById(Integer id) {
        Optional<OperateNode> record = operateNodeRepository.findById(id);
        if(!record.isPresent()){
            return null;
        }
        return record.get();
    }

    public OperateNode findNodeByNid(Integer nid) {
        OperateNode record = operateNodeRepository.findByNid(nid);
        return record;
    }
    /**
     * 根据id删除
     * @param id
     */
    @Override
    @Transactional
    public void deleteNode(Integer id) {
        operateNodeRepository.changeStatus(id, -1);
    }

    @Override
    @Transactional
    public void changeStatus(Integer id, Integer status) {
        operateNodeRepository.changeStatus(id, status);
    }


    @Override
    @Transactional
    public OperateNode createNode(OperateNodeForm operateNodeForm) {
        if(operateNodeForm.getParentNid() == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT.getCode(), "父节点不存在");
        }
        // 根据parentId查询parentid是多少
        OperateNode parentNode = this.findNodeByNid(operateNodeForm.getParentNid());
        if(parentNode == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT.getCode(), "父节点不存在");
        }

        OperateNode operateNode = new OperateNode();
        BeanUtil.copyProperties(operateNodeForm, operateNode);

        Integer nid = this.generatorService.autoGeneratorValue(IdKeyEnum.OPERATE_NODE_NID.getKey());

        operateNode.setNid(nid);
        operateNode.setNamePath(parentNode.getNamePath()+"/"+operateNode.getName());
        operateNode.setNidPath(parentNode.getNidPath()+"/"+operateNode.getNid());
        operateNode.setStatus(CommEnum.STATUS_ONLINE.getCode());
        Date nowDate = new Date();
        operateNode.setCreateTime(nowDate);
        operateNode.setUpdateTime(nowDate);
        OperateNode result = operateNodeRepository.save(operateNode);
        return result;
    }

    @Override
    @Transactional
    public OperateNode updateNode(OperateNodeForm operateNodeForm) {
        // 先根据id查到对象信息
        OperateNode operateNode = this.findNodeById(operateNodeForm.getId());
        if(operateNode == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }
        // 判断一下是否改过name，如果改过的话 需要更新所有子节点的路径
        boolean nameDiff = operateNode.getName().equals(operateNodeForm.getName());
        String namePath = operateNode.getNamePath();
        String newNamePath = namePath;
        if(!nameDiff){
            String[] names = namePath.split("/");
            names[names.length-1] = operateNodeForm.getName();
            newNamePath = StringUtils.join(names,'/');
            operateNode.setNamePath(newNamePath);
        }
        // 覆盖属性
        BeanUtil.copyProperties(operateNodeForm, operateNode);
        Date nowDate = new Date();
        operateNode.setUpdateTime(nowDate);
        OperateNode result = this.operateNodeRepository.save(operateNode);
        // 如果名称做了修改，则去批量更新下面的子节点。
        // 查询根据nidName查询，然后更新namePath
        if(!nameDiff){
            this.operateNodeRepository.updateChildNodeNamePath(namePath+'/',newNamePath+'/');
        }
        return result;
    }
}
