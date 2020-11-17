package com.icu.cc.message.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yi on 2020/11/17 23:16
 */
@Component
public class PropertyConfig {

    @Value("${netty.port}")
    public Integer nettyPort;

}
