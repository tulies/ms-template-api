package com.tulies.api.controller;

import com.tulies.api.beans.dto.OperateNodeDTO;
import com.tulies.api.beans.form.OperateNodeCreateForm;
import com.tulies.api.beans.form.OperateNodeUpdateForm;
import com.tulies.api.beans.qo.OperateNodeQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.beans.vo.ResultVO;
import com.tulies.api.entity.OperateNode;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.service.OperateService;
import com.tulies.api.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 王嘉炀
 * @date 2020-7-31 15:45
 */
@Slf4j
@RestController
@RequestMapping("/operate")
public class OperateController {

    @Autowired
    private OperateService operateService;
    @GetMapping("/node/list")
    public ResultVO list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                         OperateNodeQO operateNodeQO,
                         String sorter){
        PageVO<OperateNode> pageVO = this.operateService.findNodeList(pageNum-1 , pageSize, operateNodeQO, sorter);
        return ResultVOUtil.success(pageVO);
    }



    @PostMapping("/delete")
    public ResultVO delete(@RequestBody OperateNodeQO operateNodeQO){
        if(operateNodeQO.getId() == null ){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少id参数");
        }
        if(operateNodeQO.getId() == 1 ){
            throw new AppException(ResultEnum.ILLEGAL_OPERATION);
        }
        // 先查询下当前这个活动信息
        OperateNode record = operateService.findNodeById(operateNodeQO.getId());
        if (record == null) {
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }

        operateService.deleteNode(operateNodeQO.getId());
        ResultVO resultVO = ResultVOUtil.success();
        return resultVO;
    }

    @PostMapping("/changeStatus")
    public ResultVO changeStatus(@RequestBody OperateNodeQO operateNodeQO) {
        if(operateNodeQO.getId() == null || StringUtils.isBlank(operateNodeQO.getStatus())){
            log.error("【更改状态】参数不正确，id={}，status={}", operateNodeQO.getId(), operateNodeQO.getStatus());
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少id或status参数");
        }

        OperateNode user = operateService.findNodeById(operateNodeQO.getId());
        if (user == null) {
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }
        operateService.changeStatus(operateNodeQO.getId(), Integer.valueOf(operateNodeQO.getStatus()));
        return ResultVOUtil.success();
    }

    @PostMapping("/create")
    public ResultVO create(@RequestBody @Valid OperateNodeCreateForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建】参数不正确，userForm={}", userForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        OperateNode create = operateService.create(userForm);
        return ResultVOUtil.success(create);
    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody @Valid OperateNodeUpdateForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【更新】参数不正确，userForm={}", userForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        if(userForm.getId() == null){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(), "缺少参数，ID必传");
        }
        OperateNode update = operateService.update(userForm);
        return ResultVOUtil.success(update);
    }
//
    @GetMapping("/info/{id}")
    public ResultVO info(@PathVariable(name = "id") Integer id){

        OperateNode user = this.operateService.findById(id);
        if(user == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT.getCode(), ResultEnum.DATA_NOT_EXIT.getMessage());
        }
        return ResultVOUtil.success(user);
    }
}
