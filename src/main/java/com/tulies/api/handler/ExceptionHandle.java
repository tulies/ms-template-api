package com.tulies.api.handler;


import com.tulies.api.beans.vo.ResultVO;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 全局异常统一处理
 */
@ControllerAdvice
@Slf4j
public class ExceptionHandle {

    /**
     * 404拦截返回
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResultVO handleResourceNotFoundException(NoHandlerFoundException e) {
        e.printStackTrace();
        return ResultVOUtil.error(ResultEnum.NOT_EXIST.getCode(),ResultEnum.NOT_EXIST.getMessage());
    }


    @ExceptionHandler(value = AppException.class)
    @ResponseBody
    public ResultVO handlerSellerException(AppException e) {
//        e.printStackTrace();
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    @ResponseBody
    public ResultVO handlerMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        e.printStackTrace();
        return ResultVOUtil.error(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage()+",缺少"+e.getParameterName());
    }


    /**
     * 全局异常返回
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResultVO globalExceptionHandler(Exception e){
        e.printStackTrace();
        return ResultVOUtil.error(ResultEnum.SERVER_ERROR.getCode(),ResultEnum.SERVER_ERROR.getMessage());
    }
}
