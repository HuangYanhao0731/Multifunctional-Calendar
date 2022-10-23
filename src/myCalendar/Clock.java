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
        t = new Thread(this);                //实例化线程
        t.start();                        //调用线程
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);                    //休眠1秒钟
            } catch (InterruptedException e) {
                System.out.println("异常");
            }
            this.repaint(100);
        }
    }

    public void paint(Graphics g) {
        Font f = new Font("宋体", Font.BOLD, 16);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy'年'MM'月'dd'日'HH:mm:ss");//格式化时间显示类型
        Calendar now = Calendar.getInstance();
        time = SDF.format(now.getTime());        //得到当前日期和时间
        g.setFont(f);
        g.setColor(Color.black);
        g.drawString(time, 45, 25);
    }
}
