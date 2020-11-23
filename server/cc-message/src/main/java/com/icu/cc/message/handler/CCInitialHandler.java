package com.icu.cc.message.handler;

import com.icu.cc.protocol.CCDecoder;
import com.icu.cc.protocol.CCEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by yi on 2020/11/17 23:11
 */
public class CCInitialHandler extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        // todo: 添加自定义处理器
        ch.pipeline()
                .addLast("encoder", new CCEncoder())
                .addLast("decoder", new CCDecoder());
    }

}
