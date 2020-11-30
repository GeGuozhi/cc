package com.icu.cc.swing.frame;

import com.icu.cc.client.CCClient;
import com.icu.cc.common.constants.CommonHeader;
import com.icu.cc.common.handler.CCHandler;
import com.icu.cc.common.protocol.MessageTypeEnum;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * 主界面
 * <p>
 * Created by yi on 2020/11/24 22:30
 */
public class MainActivity extends JFrame {

    private JPanel mainPanel;
    private CCClient client;
    private JTextArea msgTextArea;
    private JTextField userNameField;

    public MainActivity() {
        super("cc");
        setSize(700, 500);
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

        // 消息框
        msgTextArea = new JTextArea();
        msgTextArea.setEditable(false);
        msgTextArea.setText("欢迎使用 cc 客户端，该客户端由 com.icu 提供 ~\n");
        JScrollPane jsp = new JScrollPane(msgTextArea);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // 界面底部
        JPanel bottomPane = new JPanel(new BorderLayout());
        JButton button = new JButton("发送消息");
        TextField inputField = new TextField();
        button.addActionListener(e -> {
            if (userNameField.getText() == null || "".equals(userNameField.getText().trim())) {
                JOptionPane.showMessageDialog(mainPanel, "用户名不能为空", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (inputField.getText() != null && !"".equals(inputField.getText().trim())) {
                client.sendBroadcastStrMessage(inputField.getText(), userNameField.getText());
            }
            inputField.setText("");
        });
        bottomPane.add(button, BorderLayout.EAST);
        bottomPane.add(inputField, BorderLayout.CENTER);

        // 界面顶部
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel userNameLabel = new JLabel("用户名:   ");
        userNameField = new JTextField("default");
        topPanel.add(userNameLabel, BorderLayout.WEST);
        topPanel.add(userNameField, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(jsp, BorderLayout.CENTER);
        mainPanel.add(bottomPane, BorderLayout.SOUTH);
        setContentPane(mainPanel);
    }

    /**
     * 文本消息处理器
     */
    private final CCHandler strMessageHandler = (ctx, msg) -> {
        Map<String, String> header = msg.getHeader();
        String from = header.get(CommonHeader.FROM);
        if (from != null) {
            msgTextArea.setText(msgTextArea.getText() + "received by: " + from + "：" + new String(msg.getContent()) + "\n");
        } else {
            msgTextArea.setText(msgTextArea.getText() + "received msg：" + new String(msg.getContent()) + "\n");
        }
    };

    /**
     * 加载 cc 客户端
     */
    private void loadCCClient() {
        try {
            client = new CCClient("utools.club", 36140);
            client.init(userNameField.getText());
            client.addHandler(MessageTypeEnum.STR_MESSAGE.getType(), strMessageHandler);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(mainPanel, e.getMessage(), "发送错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }


}
