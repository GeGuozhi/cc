package com.icu.cc.protocol;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 *
 * cc 协议格式
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
     * 消息长度
     */
    private int headerLength;
    /**
     * 消息类型
     */
    private Map<String, String> header;
    /**
     * 消息内容长度
     */
    private long contentLength;
    /**
     * 消息内容
     */
    private byte[] content;

}
