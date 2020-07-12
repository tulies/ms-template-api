package com.tulies.api.controller;

import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.service.WeMaService;
import com.tulies.api.service.WePayService;
import com.tulies.api.utils.ResultVOUtil;
import com.tulies.api.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Slf4j
@RestController
@RequestMapping("/wx")
public class WxController {


    @Autowired
    private WeMaService weMaService;

    @Autowired
    private WePayService wePayService;

    // 我的openid oFmAG5omzB607wf2WsbzL_PoYp90
    @GetMapping("/code2Session")
    public ResultVO code2Session(String appid, String code){
        if (StringUtils.isBlank(code) || StringUtils.isBlank(code)) {
            throw new AppException(ResultEnum.MISSING_PARAMS);
        }
        return ResultVOUtil.success(weMaService.code2Session(appid, code));
    }

    //统一下单
    @PostMapping("/unifiedOrder")
    public ResultVO unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request){
        return ResultVOUtil.success(wePayService.unifiedOrder(request));
    }

    //统一下单
    @PostMapping("/notify")
    public String notifyUrl(@RequestBody String xmlData){
        Boolean notifyStstus = this.wePayService.parseOrderNotifyResult(xmlData);
        log.info("走进了notify，回调状态：notifyStstus={}，xmlData={}",notifyStstus,xmlData);
        if(notifyStstus){
            return WxPayNotifyResponse.success("成功");
        }
        return WxPayNotifyResponse.success("成功");
    }


    @GetMapping("/sendSubscribeMsg")
    public ResultVO sendSubscribeMsg(String appid){
        WxMaSubscribeMessage subscribeMessage = new WxMaSubscribeMessage();
        subscribeMessage.setToUser("oFmAG5omzB607wf2WsbzL_PoYp90");
        subscribeMessage.setTemplateId("D9WFoVd7JrtkGoOlqXgtezSYRB520f_ky9d0Ygflf88");
        subscribeMessage.setPage("/xs/xs/x");
        subscribeMessage.setData(Arrays.asList(
            new WxMaSubscribeMessage.Data("name1","王小二"),
            new WxMaSubscribeMessage.Data("phone_number2","18012678888"),
            new WxMaSubscribeMessage.Data("time3","2020-06-29 18:30"),
            new WxMaSubscribeMessage.Data("thing11","上海市浦东新区陆家嘴东方明珠大厦"),
            new WxMaSubscribeMessage.Data("thing8","钢琴搬运-三角钢琴")
        ));
        this.weMaService.sendSubscribeMsg(appid,subscribeMessage);
        return ResultVOUtil.success();
    }

}
