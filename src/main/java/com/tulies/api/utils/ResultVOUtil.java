package com.tulies.api.utils;


import com.tulies.api.beans.vo.ResultVO;
import com.tulies.api.enums.ResultEnum;

/**
 * @author 王嘉炀
 * @date 2019/6/30 下午1:58
 */
public class ResultVOUtil<T> {
    public static ResultVO success(Object object){
        return info(0, "成功", object);
    }
    public static ResultVO success(Object object,String msg){
        return info(0, msg, object);
    }
    public static ResultVO success(){
        return success(null);
    }

    public static <T> ResultVO<T> ok(T data){
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(data);
        return resultVO;
    }


    public static ResultVO error(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
    public static ResultVO error(ResultEnum resultEnum){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMsg(resultEnum.getMessage());
        return resultVO;
    }

    // 全部自定义
    public static ResultVO info(Integer code, String msg , Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        resultVO.setData(object);
        return resultVO;
    }

    public static ResultVO info(Integer code, String msg){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
