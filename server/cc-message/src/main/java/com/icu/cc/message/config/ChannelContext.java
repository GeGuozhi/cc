package com.icu.cc.message.config;

import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.MessageTypeEnum;
import com.icu.cc.message.dto.ChannelInfo;
import com.icu.cc.message.handler.AuthHandler;
import com.icu.cc.message.handler.StrMessageHandler;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelContext {

    /**
     * 链接信息
     */
    public final static ConcurrentHashMap<Channel, ChannelInfo> onlineChannelMap = new ConcurrentHashMap<>();

    /**
     * 处理器
     */
    public final static HashMap<Integer, CCHandler> handlerMap = new HashMap<>();

    static {
        handlerMap.put(MessageTypeEnum.AUTH.getType(), new AuthHandler());
        handlerMap.put(MessageTypeEnum.STR_MESSAGE.getType(), new StrMessageHandler());
    }

}
