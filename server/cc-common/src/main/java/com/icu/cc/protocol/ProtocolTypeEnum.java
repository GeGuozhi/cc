package com.icu.cc.protocol;

/**
 * 协议类型
 *
 * Created by yi on 2020/11/23 22:17
 */
public enum ProtocolTypeEnum {

    /**
     * 授权
     */
    AUTH(1),
    /**
     * 文本消息
     */
    STR_MESSAGE(2),
    ;

    /**
     * 消息类型
     */
    private int type;

    ProtocolTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

}
