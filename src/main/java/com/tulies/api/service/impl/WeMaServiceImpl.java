package com.tulies.api.service.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaSubscribeMessage;
import com.tulies.api.config.WxMaConfiguration;
import com.tulies.api.enums.ResultEnum;
import com.tulies.api.exception.AppException;
import com.tulies.api.service.WeMaService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WeMaServiceImpl implements WeMaService {
    @Override
    public WxMaJscode2SessionResult code2Session(String appid, String code) {
        log.info(appid+","+code);
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            WxMaJscode2SessionResult session = wxMaService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());
            //TODO 可以增加自己的逻辑，关联业务相关数据
            return session;
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new AppException(ResultEnum.WX_Ma_CODE2SESSION);
        }
    }

    @Override
    public void sendSubscribeMsg(String appid, WxMaSubscribeMessage subscribeMessage) {
        final WxMaService wxMaService = WxMaConfiguration.getMaService(appid);
        try {
            wxMaService.getMsgService().sendSubscribeMsg(subscribeMessage);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            throw new AppException(ResultEnum.WX_Ma_ERROR);
        }

    }
}
