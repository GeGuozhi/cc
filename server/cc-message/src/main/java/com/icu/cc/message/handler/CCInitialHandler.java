package com.icu.cc.message.handler;

import com.icu.cc.common.protocol.CCDecoder;
import com.icu.cc.common.protocol.CCEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Channel 初始化工具
 *
 * Created by yi on 2020/11/17 23:11
 */
public class CCInitialHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ch.pipeline()
                .addLast("encoder", new CCEncoder())
                .addLast("decoder", new CCDecoder())
                .addLast("ccHandler", new MessageHandler());
    }

}
