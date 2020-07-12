package com.tulies.api.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;

public interface WeMaService {
    WxMaJscode2SessionResult code2Session(String appid, String code);
    void sendSubscribeMsg(String appid, WxMaSubscribeMessage subscribeMessage);
}
