package com.icu.cc.message.dto;

import io.netty.channel.Channel;

/**
 * 连接信息
 */
public class ChannelInfo {

    /**
     * 用户标识
     */
    public String id;
    /**
     * 通道
     */
    public Channel channel;

    public ChannelInfo(String id, Channel channel) {
        this.id = id;
        this.channel = channel;
    }

}
