package com.tulies.api.service;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import org.springframework.web.bind.annotation.RequestBody;

public interface WePayService {
    WxPayUnifiedOrderResult unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request);
    Boolean parseOrderNotifyResult(String xmlData);
}
