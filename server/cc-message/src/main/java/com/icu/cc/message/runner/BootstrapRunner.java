package com.icu.cc.message.runner;

import com.icu.cc.message.config.PropertyConfig;
import com.icu.cc.message.handler.CCInitialHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 消息推送服务启动类
 * <p>
 * Created by yi on 2020/11/17 23:02
 */
@Component
@AllArgsConstructor
@Slf4j
public class BootstrapRunner implements ApplicationRunner {

    private final PropertyConfig propertyConfig;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new CCInitialHandler());

            ChannelFuture future = b.bind(propertyConfig.nettyPort).sync();

            log.info("消息推送服务启动成功");
            future.channel().closeFuture().sync();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
            log.info("服务器关闭成功");
        }
    }

}
