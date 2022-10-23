package myCalendar.weather.ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    // 获得当前屏幕的宽
    private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    // 获得当前屏幕的高
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public MyFrame(String title, int width, int height) {
        super(title);

        setSize(width, height); // 设置窗口大小
        // 计算窗口位于屏幕中心的坐标
        int x = (int) (screenWidth - width) / 2;
        int y = (int) (screenHeight - height) / 2;
        setLocation(x, y); // 设置窗口位于屏幕中心

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置窗口关闭退出程序
    }
}
