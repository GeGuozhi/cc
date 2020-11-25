package com.icu.cc.message.handler;

import com.icu.cc.common.constants.CommonHeader;
import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.CCProtocol;
import com.icu.cc.message.config.ChannelContext;
import com.icu.cc.message.dto.ChannelInfo;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 登录认证处理器
 */
@Slf4j
public class AuthHandler implements CCHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, CCProtocol msg) {
        Map<String, String> header = msg.getHeader();
        String from = header.get(CommonHeader.FROM);
        if (from == null) {
            log.error("登录失败，缺失 from 字段");
            return;
        }
        
        ChannelContext.onlineChannelMap.put(ctx.channel(), new ChannelInfo(from, ctx.channel()));
        log.info("登录成功");
    }

}
