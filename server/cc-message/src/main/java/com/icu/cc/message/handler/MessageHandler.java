package com.icu.cc.message.handler;

import com.icu.cc.protocol.CCProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<CCProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CCProtocol msg) throws Exception {
        log.info("From ip => {} 接收到消息 => {}", ctx.channel().remoteAddress(), msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        log.error("链接异常，错误描述 => ", cause);
    }
}
