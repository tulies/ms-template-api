package com.tulies.api.service.impl;

import com.tulies.api.beans.form.OperateNodeForm;
import com.tulies.api.beans.qo.OperateNodeQO;
import com.tulies.api.beans.qo.UserQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.config.RedisConstant;
import com.tulies.api.entity.OperateNode;
import com.tulies.api.entity.User;
import com.tulies.api.enums.CommEnum;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.repository.OperateNodeRepository;
import com.tulies.api.service.OperateService;
import com.tulies.api.utils.*;
import lombok.extern.slf4j.Slf4j;
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
                predicateList.add(criteriaBuilder.equal(root.get("id").as(Long.class), operateNodeQO.getId()));
            }

            //根据nid 查询
            if (operateNodeQO.getNid() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("nid").as(Long.class), operateNodeQO.getNid()));
            }
            //根据parentNid 查询
            if (operateNodeQO.getParentNid() != null) {
                predicateList.add(criteriaBuilder.equal(root.get("parentNid").as(Long.class), operateNodeQO.getParentNid()));
            }

            //根据username 模糊匹配
            if (StringUtils.isNotBlank(operateNodeQO.getName())) {
                predicateList.add(criteriaBuilder.equal(root.get("name").as(String.class), operateNodeQO.getName()));
            }
            //根据nuckname 模糊匹配
            if (StringUtils.isNotBlank(operateNodeQO.getNamePath())) {
                predicateList.add(criteriaBuilder.like(root.get("alias").as(String.class), "%"+ operateNodeQO.getNamePath()+"%"));
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
        // 根据parentId查询parentid是多少

        OperateNode operateNode = new OperateNode();
        BeanUtil.copyProperties(operateNodeForm, operateNode);
        operateNode.setNid("1820");
        operateNode.setNamePath("/21/2121/");
        Date nowDate = new Date();
        operateNode.setCreateTime(nowDate);
        operateNode.setUpdateTime(nowDate);
        operateNode.setStatus(CommEnum.STATUS_NEW_BUILD.getCode());
        OperateNode result = operateNodeRepository.save(operateNode);
        return result;
    }

    @Override
    @Transactional
    public OperateNode updateNode(OperateNodeForm operateNodeForm) {
        // 先根据id查到对象信息
        OperateNode operateNode = this.findNodeById(operateNodeForm.getId());
        // 覆盖属性
        BeanUtil.copyProperties(operateNodeForm, operateNode);
        Date nowDate = new Date();
        operateNode.setUpdateTime(nowDate);
        OperateNode result = operateNodeRepository.save(operateNode);
        return result;
    }
}
