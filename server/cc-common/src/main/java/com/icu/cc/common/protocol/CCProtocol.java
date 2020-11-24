package com.icu.cc.common.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 *
 * cc 协议格式
 * --------------------------------------------------------------
 * | headerFlag | type | headerLength | header | contentLength |
 * --------------------------------------------------------------
 *
 * Created by yi on 2020/11/23 21:43
 */
@Data
@Accessors(chain = true)
public class CCProtocol {

    /**
     * 消息起始位置标识
     */
    private int headerFlag = ProtocolConstant.HEADER_FLAG;
    /**
     * 消息类型
     */
    private int type;
    /**
     * 请求头长度
     */
    private int headerLength;
    /**
     * 消息头参数
     */
    private Map<String, String> header;
    /**
     * 消息内容长度
     */
    private int contentLength;
    /**
     * 消息内容
     */
    private byte[] content;

    public CCProtocol(int type) {
        this.type = type;
    }

}
