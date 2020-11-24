package com.icu.cc.message.handler;

import com.icu.cc.common.protocol.CCProtocol;
import com.icu.cc.common.protocol.MessageTypeEnum;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<CCProtocol> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CCProtocol msg) throws Exception {
        log.info("From ip => {} 接收到消息 => {}", ctx.channel().remoteAddress(), new String(msg.getContent()));
        CCProtocol ccMessage = new CCProtocol(MessageTypeEnum.STR_MESSAGE.getType()).setContent(msg.getContent());
        ctx.writeAndFlush(ccMessage);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("设备上线, ip => {}", ctx.channel().remoteAddress());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("设备下线, ip => {}", ctx.channel().remoteAddress());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
        log.error("链接异常，错误描述 => ", cause);
    }
}
