package com.icu.cc.common.protocol;

import com.google.common.base.Joiner;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * cc 协议编码器
 *
 * Created by yi on 2020/11/23 21:48
 */
public class CCEncoder extends MessageToByteEncoder<CCProtocol> {

    protected void encode(ChannelHandlerContext ctx, CCProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getHeaderFlag());
        out.writeInt(msg.getType());
        byte[] headerBytes = new byte[0];
        if (msg.getHeader() != null && !msg.getHeader().isEmpty())
            headerBytes = Joiner.on("\n").withKeyValueSeparator(":").join(msg.getHeader()).getBytes();
        out.writeInt(headerBytes.length);
        if (headerBytes.length > 0) out.writeBytes(headerBytes);
        out.writeInt(msg.getContent().length);
        if (msg.getContent() != null && msg.getContent().length > 0) out.writeBytes(msg.getContent());
    }

}
