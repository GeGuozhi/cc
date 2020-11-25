package com.icu.cc.swing;

import com.icu.cc.swing.frame.MainActivity;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

/**
 * 启动入口
 *
 * Created by yi on 2020/11/24 22:29
 */
public class CCSwingApplication {

    public static void main(String[] args) {
        initGlobalFont(new Font("alias", Font.PLAIN, 24));
        new MainActivity();
    }

    /**
     * 初始化全局字体
     */
    private static void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements();) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }

}
