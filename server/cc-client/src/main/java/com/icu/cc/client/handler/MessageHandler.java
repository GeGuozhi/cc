package com.icu.cc.client.handler;

import com.icu.cc.common.protocol.CCProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<CCProtocol> {

    protected void channelRead0(ChannelHandlerContext ctx, CCProtocol msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("与服务建立链接");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error("链接中断 ", cause);
        ctx.channel().close();
    }
}
