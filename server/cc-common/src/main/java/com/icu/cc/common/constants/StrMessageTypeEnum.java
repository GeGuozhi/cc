package com.icu.cc.common.constants;

/**
 * 字符串消息发送类型
 */
public enum StrMessageTypeEnum {
    SINGLE(1, "私发"),
    MULTI(2, "群发"),
    BROADCAST(3, "群发")
    ;

    private Integer type;
    private String desc;
    StrMessageTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return type;
    }
}
