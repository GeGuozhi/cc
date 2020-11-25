package com.icu.cc.message.handler;

import com.google.common.collect.Maps;
import com.icu.cc.common.constants.CommonHeader;
import com.icu.cc.common.constants.StrMessageTypeEnum;
import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.CCProtocol;
import com.icu.cc.common.protocol.MessageTypeEnum;
import com.icu.cc.message.config.ChannelContext;
import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.Objects;

/**
 * 字符串消息处理器
 */
public class StrMessageHandler implements CCHandler {

    @Override
    public void handle(ChannelHandlerContext ctx, CCProtocol msg) {
        Map<String, String> header = msg.getHeader();
        String typeStr = header.get(CommonHeader.TYPE);
        if (typeStr == null) return;
        String from = header.get(CommonHeader.FROM);
        if (from != null) ChannelContext.onlineChannelMap.get(ctx.channel()).id = from;

        Integer type = Integer.parseInt(typeStr);

        if (Objects.equals(StrMessageTypeEnum.SINGLE.getType(), type)) {
            // 私发
            Map<String, String> respHeader = Maps.newHashMap();
            String toId = header.get(CommonHeader.TO);
            if (toId == null) return;
            respHeader.put(CommonHeader.TO, toId);
            respHeader.put(CommonHeader.TYPE, type.toString());

            String fromId = header.get(CommonHeader.FROM);
            if (fromId != null) respHeader.put(CommonHeader.FROM, fromId);

            ChannelContext.onlineChannelMap.forEach((channel, channelInfo) -> {
                if (Objects.equals(channelInfo.id, fromId)) {
                    channel.writeAndFlush(new CCProtocol(MessageTypeEnum.STR_MESSAGE.getType())
                            .setHeader(respHeader).setContent(msg.getContent()));
                }
            });
        } else if (Objects.equals(StrMessageTypeEnum.BROADCAST.getType(), type)) {
            // 广播
            Map<String, String> respHeader = Maps.newHashMap();
            String fromId = header.get(CommonHeader.FROM);
            if (fromId != null) respHeader.put(CommonHeader.FROM, fromId);
            respHeader.put(CommonHeader.TYPE, type.toString());

            ChannelContext.onlineChannelMap.forEach((channel, channelInfo) -> {
                // 跳过自己
                if (channel.equals(ctx.channel())) return;

                channel.writeAndFlush(new CCProtocol(MessageTypeEnum.STR_MESSAGE.getType())
                        .setHeader(respHeader).setContent(msg.getContent()));
            });
        }
    }
}
