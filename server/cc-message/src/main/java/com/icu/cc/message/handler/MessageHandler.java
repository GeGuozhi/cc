package com.icu.cc.message.handler;

import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.CCProtocol;
import com.icu.cc.common.protocol.MessageTypeEnum;
import com.icu.cc.message.config.ChannelContext;
import com.icu.cc.message.dto.ChannelInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<CCProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CCProtocol msg) throws Exception {
        CCHandler handler = ChannelContext.handlerMap.get(msg.getType());
        if (handler == null) {
            log.info("没有对应的消息处理器");
            return;
        }
        handler.handle(ctx, msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("设备上线, ip => {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        ChannelContext.onlineChannelMap.remove(ctx.channel());
        log.info("设备下线, ip => {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ChannelContext.onlineChannelMap.remove(ctx.channel());
        ctx.channel().close();
        log.error("链接异常，错误描述 => {}", cause.getMessage());
    }
}
