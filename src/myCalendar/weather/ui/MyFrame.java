package myCalendar.weather.ui;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    // ��õ�ǰ��Ļ�Ŀ�
    private double screenWidth = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    // ��õ�ǰ��Ļ�ĸ�
    private double screenHeight = Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public MyFrame(String title, int width, int height) {
        super(title);

        setSize(width, height); // ���ô��ڴ�С
        // ���㴰��λ����Ļ���ĵ�����
        int x = (int) (screenWidth - width) / 2;
        int y = (int) (screenHeight - height) / 2;
        setLocation(x, y); // ���ô���λ����Ļ����

//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //���ô��ڹر��˳�����
    }
}
