package com.icu.cc.common.handler;

import com.icu.cc.common.protocol.CCProtocol;
import io.netty.channel.ChannelHandlerContext;

/**
 * 自定义消息处理器
 */
@FunctionalInterface
public interface CCHandler {

    void handle(ChannelHandlerContext ctx, CCProtocol msg);

}
