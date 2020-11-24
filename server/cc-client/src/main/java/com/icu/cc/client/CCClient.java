package com.icu.cc.client;

import com.icu.cc.client.handler.MessageHandler;
import com.icu.cc.common.exception.CCException;
import com.icu.cc.common.protocol.CCDecoder;
import com.icu.cc.common.protocol.CCEncoder;
import com.icu.cc.common.protocol.CCProtocol;
import com.icu.cc.common.protocol.MessageTypeEnum;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * 客户端相关操作
 */
@Slf4j
public class CCClient implements AutoCloseable {

    public static Channel channel;

    private final String ip;
    private final Integer port;
    private final EventLoopGroup worker = new NioEventLoopGroup(2, new DefaultThreadFactory("cc-worker"));

    public CCClient(String ip, Integer port) {
        this.ip = ip;
        this.port = port;
    }

    /**
     * 初始化客户端
     */
    public void init() {
        try {
            Bootstrap b = new Bootstrap();
            b.group(worker)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("encoder", new CCEncoder())
                                    .addLast("decoder", new CCDecoder())
                                    .addLast("ccHandler", new MessageHandler());
                        }
                    });

            final ChannelFuture cf = b.connect(ip, port).sync();
            channel = cf.channel();
        } catch (InterruptedException e) {
            log.error("启动失败");
            throw new CCException("启动失败");
        }
    }

    /**
     * 发送文本消息
     *
     * @param message 消息内容
     */
    public void sendMessage(String message) {
        CCProtocol ccMessage = new CCProtocol(MessageTypeEnum.STR_MESSAGE.getType()).setContent(message.getBytes());
        channel.writeAndFlush(ccMessage);
        log.info("发送完成");
    }

    /**
     * 关闭客户端
     */
    public void close() {
        try {
            if (channel != null) channel.close().sync();
        } catch (InterruptedException e) {
            log.error("客户端关闭异常");
        } finally {
            worker.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        try (CCClient client = new CCClient("localhost", 8081)) {
            client.init();
            log.info("client start...");
            client.sendMessage("hi~");
            TimeUnit.SECONDS.sleep(10);
            log.info("client end...");
        } catch (InterruptedException e) {
            log.error("", e);
        }
    }
}
