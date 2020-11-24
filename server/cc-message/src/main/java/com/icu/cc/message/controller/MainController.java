package com.icu.cc.message.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息推送服务主要的 http 接口
 */
@RestController
public class MainController {

    /**
     * 检测服务是否连接正常
     *
     * @return "pong"
     */
    @GetMapping("/ping")
    public String pong() {
        return "pong";
    }

}
