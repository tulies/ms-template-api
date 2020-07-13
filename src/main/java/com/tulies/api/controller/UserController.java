package com.tulies.blog.controller;

import com.tulies.blog.dto.UserDTO;
import com.tulies.blog.entity.User;
import com.tulies.blog.enums.ResultEnum;
import com.tulies.blog.exception.AppException;
import com.tulies.blog.qo.UserQO;
import com.tulies.blog.service.UserService;
import com.tulies.blog.utils.ResultVOUtil;
import com.tulies.blog.vo.PageVO;
import com.tulies.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVO list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                         @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
                         UserQO userQO,
                         String sorter){
        PageVO<User> pageVO = this.userService.findList(pageNum - 1, pageSize, userQO, sorter);
        return ResultVOUtil.success(pageVO);
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestBody UserQO userQO){
        if(userQO.getId() == null ){
//            log.error("【删除活动记录】参数不正确，actRecordVO={}", actRecordVO);
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

        // 先查询下当前这个活动信息，判断下状态，是否是可以删除的情况。
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


}
