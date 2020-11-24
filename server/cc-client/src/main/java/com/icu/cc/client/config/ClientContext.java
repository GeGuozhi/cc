package com.icu.cc.client.config;

import com.google.common.collect.Maps;
import com.icu.cc.client.handler.StrMessageHandler;
import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.MessageTypeEnum;

import java.util.Map;

public class ClientContext {
 
    public static Map<Integer, Class<? extends CCHandler>> handlers = Maps.newHashMap();

    static {
        handlers.put(MessageTypeEnum.STR_MESSAGE.getType(), StrMessageHandler.class);
    }

}
