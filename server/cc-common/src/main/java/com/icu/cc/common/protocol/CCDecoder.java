package com.icu.cc.common.protocol;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.Map;

/**
 *
 * cc协议 解码器
 *
 * Created by yi on 2020/11/23 21:52
 */
public class CCDecoder extends ByteToMessageDecoder {

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // 处理协议头
        if (in.readableBytes() < ProtocolConstant.HEADER_LEN) {
            return;
        } else if (in.readInt() != ProtocolConstant.HEADER_FLAG) {
            return;
        }

        // 解析消息类型
        int type = in.readInt();
        if (!MessageTypeEnum.contains(type)) {
            return;
        }

        // 处理 header
        in.markReaderIndex();
        int headerLen = in.readInt();
        if (headerLen > in.readableBytes()) {
            in.resetReaderIndex();
            return;
        }
        Map<String, String> header = Maps.newHashMap();
        if (headerLen > 0) {
            byte[] headerBytes = new byte[headerLen];
            in.readBytes(headerBytes);
            header = Splitter.on("\n").withKeyValueSeparator(":").split(new String(headerBytes));
        }

        // 处理消息内容
        if (in.readableBytes() < ProtocolConstant.CONTENT_LEN) {
            in.resetReaderIndex();
            return;
        }
        int contentLen = in.readInt();
        if (contentLen < in.readableBytes()) {
            in.resetReaderIndex();
            return;
        }
        byte[] content = new byte[0];
        if (contentLen > 0) {
            content = new byte[contentLen];
            in.readBytes(content);
        }

        // 组装协议信息
        out.add(new CCProtocol(type)
                .setHeaderLength(headerLen)
                .setHeader(header)
                .setContentLength(contentLen)
                .setContent(content));
    }

}
