package myCalendar;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.SimpleDateFormat;
import java.util.Calendar;

class Clock extends Canvas implements Runnable {
    /**
     *
     */
    private static final long serialVersionUID = 3660124045489727166L;
    MainFrame mf;
    Thread t;
    String time;

    public Clock(MainFrame mf) {
        this.mf = mf;
        setSize(280, 40);
        setBackground(Color.white);
        t = new Thread(this);                //ʵ�����߳�
        t.start();                        //�����߳�
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);                    //����1����
            } catch (InterruptedException e) {
                System.out.println("�쳣");
            }
            this.repaint(100);
        }
    }

    public void paint(Graphics g) {
        Font f = new Font("����", Font.BOLD, 16);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy'��'MM'��'dd'��'HH:mm:ss");//��ʽ��ʱ����ʾ����
        Calendar now = Calendar.getInstance();
        time = SDF.format(now.getTime());        //�õ���ǰ���ں�ʱ��
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString(time, 45, 25);
    }
}
