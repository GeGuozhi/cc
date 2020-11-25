package com.icu.cc.common.protocol;

import java.util.HashMap;
import java.util.Map;

/**
 * 协议类型
 * <p>
 * Created by yi on 2020/11/23 22:17
 */
public enum MessageTypeEnum {

    AUTH(1, "授权"),
    STR_MESSAGE(2, "文本消息"),
    ;

    /**
     * 消息类型
     */
    private final Integer type;
    private final String desc;

    MessageTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public Integer getType() {
        return this.type;
    }

    public String getDesc() {
        return this.desc;
    }

    private static final Map<Integer, String> map = new HashMap<Integer, String>();

    static {
        for (MessageTypeEnum m : MessageTypeEnum.values()) {
            map.put(m.getType(), m.getDesc());
        }
    }

    public static boolean contains(int type) {
        return map.containsKey(type);
    }

}
