package com.icu.cc.protocol;

/**
 * 协议中的一些常量
 *
 * Created by yi on 2020/11/23 22:15
 */
public class ProtocolConstant {

    /**
     * 协议头占用字节数
     */
    public final static int HEADER_LEN = 8;

    /**
     * 消息内容长度占用字节数
     */
    public final static int CONTENT_LEN = 4;

    /**
     * 协议起始标识
     */
    public final static int HEADER_FLAG = 0x76;

}
