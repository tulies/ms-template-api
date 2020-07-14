package com.tulies.api.controller;

import com.tulies.api.beans.dto.UserDTO;
import com.tulies.api.beans.form.UserCreateForm;
import com.tulies.api.beans.form.UserUpdateForm;
import com.tulies.api.beans.qo.UserQO;
import com.tulies.api.beans.vo.PageVO;
import com.tulies.api.beans.vo.ResultVO;
import com.tulies.api.entity.User;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.service.UserService;
import com.tulies.api.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author 王嘉炀
 * @date 2019-10-13 15:45
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping("/list")
    public ResultVO list(@RequestParam(value = "pageNum", defaultValue = "0") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                         UserQO userQO,
                         String sorter){
        PageVO<User> pageVO = this.userService.findList(pageNum , pageSize, userQO, sorter);
        return ResultVOUtil.success(pageVO);
    }



    @PostMapping("/delete")
    public ResultVO delete(@RequestBody UserQO userQO){
        if(userQO.getId() == null ){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少id参数");
        }
        // 先查询下当前这个活动信息
        User record = userService.findById(userQO.getId());
        if (record == null) {
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }

        userService.deleteById(userQO.getId());
        ResultVO resultVO = ResultVOUtil.success();
        return resultVO;
    }

    @PostMapping("/changeStatus")
    public ResultVO changeStatus(@RequestBody UserQO userQO) {
        if(userQO.getId() == null || StringUtils.isBlank(userQO.getStatus())){
            log.error("【更改状态】参数不正确，id={}，status={}", userQO.getId(), userQO.getStatus());
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少id或status参数");
        }

        User user = userService.findById(userQO.getId());
        if (user == null) {
            throw new AppException(ResultEnum.DATA_NOT_EXIT);
        }
        userService.changeStatus(userQO.getId(), Integer.valueOf(userQO.getStatus()));
        return ResultVOUtil.success();
    }
    @PostMapping("/login")
    public ResultVO login(@RequestBody UserQO userQO){
        if( StringUtils.isBlank(userQO.getUsername()) || StringUtils.isBlank(userQO.getPassword())){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                    ResultEnum.PARAM_ERROR.getMessage()+",缺少帐号或密码");
        }
        UserDTO userDTO = this.userService.login(userQO);
        return ResultVOUtil.success(userDTO);
    }


    @PostMapping("/create")
    public ResultVO create(@RequestBody @Valid UserCreateForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建】参数不正确，userForm={}", userForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        User create = userService.create(userForm);
        return ResultVOUtil.success(create);

    }

    @PostMapping("/update")
    public ResultVO update(@RequestBody @Valid UserUpdateForm userForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【更新】参数不正确，userForm={}", userForm);
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(),
                bindingResult.getFieldError().getDefaultMessage());
        }
        if(userForm.getId() == null){
            throw new AppException(ResultEnum.PARAM_ERROR.getCode(), "缺少参数，ID必传");
        }
        User update = userService.update(userForm);
        return ResultVOUtil.success(update);
    }
//
//    @GetMapping("/info/{id}")
//    public ResultVO info(@PathVariable(name = "id") Integer id){
//
//        ArticleDTO articleDTO = this.articleService.findByIdWithExt(id);
//        if(articleDTO == null){
//            throw new AppException(ResultEnum.DATA_NOT_EXIT.getCode(), ResultEnum.DATA_NOT_EXIT.getMessage());
//        }
//        return ResultVOUtil.success(articleDTO);
//    }
}
