package com.icu.cc.swing.frame;

import com.icu.cc.client.CCClient;
import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.MessageTypeEnum;

import javax.swing.*;
import java.awt.*;

/**
 * Created by yi on 2020/11/24 22:30
 */
public class MainActivity extends JFrame {

    private String title;
    private JPanel mainPanel;
    private CCClient client;
    private JTextArea msgTextArea;

    public MainActivity(String title) {
        super("cc - " + title);
        this.title = title;
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loadLayout();
        loadCCClient();
        setVisible(true);
    }

    /**
     * 绘制界面
     */
    private void loadLayout() {
        mainPanel = new JPanel(new BorderLayout());

        msgTextArea = new JTextArea();
        msgTextArea.setEditable(false);
        msgTextArea.setText("欢迎使用 cc 客户端，该客户端由 com.icu 提供 ~\n");
        JScrollPane jsp = new JScrollPane(msgTextArea);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel bottomPane = new JPanel(new BorderLayout());
        JButton button = new JButton("发送消息");
        TextField inputField = new TextField();
        button.addActionListener(e -> {
            if (inputField.getText() != null && !"".equals(inputField.getText().trim())) {
                client.sendMessage(inputField.getText());
            }
            inputField.setText("");
        });
        bottomPane.add(button, BorderLayout.EAST);
        bottomPane.add(inputField, BorderLayout.CENTER);

        mainPanel.add(jsp, BorderLayout.CENTER);
        mainPanel.add(bottomPane, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }


    /**
     * 文本消息处理器
     */
    private CCHandler strMessageHandler = (ctx, msg) -> {
        msgTextArea.setText(msgTextArea.getText() + new String(msg.getContent()) + "\n");
    };

    /**
     * 加载 cc 客户端
     */
    private void loadCCClient() {
        try {
            client = new CCClient("localhost", 8081);
            client.init();
            client.addHandler(MessageTypeEnum.STR_MESSAGE.getType(), strMessageHandler);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "发送错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


}
