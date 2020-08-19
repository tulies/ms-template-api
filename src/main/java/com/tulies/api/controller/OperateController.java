package com.tulies.api.controller;

import com.tulies.api.beans.form.OperateNodeForm;
import com.tulies.api.beans.qo.OperateContentQO;
import com.tulies.api.beans.qo.OperateNodeQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.beans.vo.ResultVO;
import com.tulies.api.entity.OperateContent;
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

    /**** **********************************
     *    CONTENT CONTENT CONTENT CONTENT CONTENT
     **************************************/
    @GetMapping("/content/list")
    public ResultVO listContent(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                         OperateContentQO operateContentQO,
                         String sorter){
        PageVO<OperateContent> pageVO = this.operateService.findContentList(pageNum-1 , pageSize, operateContentQO, sorter);
        return ResultVOUtil.success(pageVO);
    }

    /**** **********************************
     *    NODE NODE NODE NODE NODE
     **************************************/
    @GetMapping("/node/list")
    public ResultVO listNode(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                         OperateNodeQO operateNodeQO,
                         String sorter){
        PageVO<OperateNode> pageVO = this.operateService.findNodeList(pageNum-1 , pageSize, operateNodeQO, sorter);
        return ResultVOUtil.success(pageVO);
    }


    @PostMapping("/node/delete")
    public ResultVO deleteNode(@RequestBody OperateNodeQO operateNodeQO){
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

    @PostMapping("/node/changeStatus")
    public ResultVO changeNodeStatus(@RequestBody OperateNodeQO operateNodeQO) {
        if(operateNodeQO.getId() == null || StringUtils.isBlank(operateNodeQO.getStatus())){
            log.error("【更改状态】参数不正确，id={}，status={}", operateNodeQO.getId(), operateNodeQO.getStatus());
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少id或status参数");
        }

        OperateNode user = operateService.findNodeById(operateNodeQO.getId());
        if (user == null) {
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }
        operateService.changeNodeStatus(operateNodeQO.getId(), Integer.valueOf(operateNodeQO.getStatus()));
        return ResultVOUtil.success();
    }

    @PostMapping("/node/create")
    public ResultVO createNode(@RequestBody @Valid OperateNodeForm operateNodeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建】参数不正确，operateNodeForm={}", operateNodeForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        OperateNode create = operateService.createNode(operateNodeForm);
        return ResultVOUtil.success(create);
    }

    @PostMapping("/node/update")
    public ResultVO updateNode(@RequestBody @Valid OperateNodeForm operateNodeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【更新】参数不正确，operateNodeForm={}", operateNodeForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        if(operateNodeForm.getId() == null){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(), "缺少参数，ID必传");
        }
        OperateNode update = operateService.updateNode(operateNodeForm);
        return ResultVOUtil.success(update);
    }
//
    @GetMapping("/node/info/{id}")
    public ResultVO infoNode(@PathVariable(name = "id") Integer id){

        OperateNode user = this.operateService.findNodeById(id);
        if(user == null){
            throw new AppException(ResultEnum.DATA_NOT_EXIT.getCode(), ResultEnum.DATA_NOT_EXIT.getMessage());
        }
        return ResultVOUtil.success(user);
    }
}
