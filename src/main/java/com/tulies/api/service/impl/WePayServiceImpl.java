package com.tulies.api.service.impl;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;

import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.service.WePayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Slf4j
public class WePayServiceImpl implements WePayService {

    @Autowired
    private WxPayService wxPayService;

    /**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @Override
    public WxPayUnifiedOrderResult unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) {
        try {
            return this.wxPayService.unifiedOrder(request);
        } catch (WxPayException e) {
            log.error(e.getMessage(), e);
            throw new AppException(ResultEnum.WX_PAY_UNIFIEDORDER);
        }
    }

    @Override
    public Boolean parseOrderNotifyResult(String xmlData) {
        try {
            final WxPayOrderNotifyResult notifyResult = this.wxPayService.parseOrderNotifyResult(xmlData);
            // todo 添加我们自己的逻辑，比如同步订单
            log.info("WxPayOrderNotifyResult={}",notifyResult);
            return true;

        } catch (WxPayException e) {
            log.error(e.getMessage(), e);
//            throw new AppException(ResultEnum.WX_PAY_UNIFIEDORDER);
            return false;
        }
//        return null;
    }
}
