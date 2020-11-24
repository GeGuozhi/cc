package com.icu.cc.protocol;

import java.util.HashMap;
import java.util.Map;

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
    private final int type;

    ProtocolTypeEnum(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }

    private static Map<Integer, String> map = new HashMap<Integer, String>();
    static {
        map.put(1, "授权");
        map.put(2, "文本消息");
    }

    public static boolean contains(int type) {
        return map.containsKey(type);
    }

}
