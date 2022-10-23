package myCalendar;

import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class ClockCanvas extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	private int seconds = 0;
	private String city;
	private GregorianCalendar calendar;
	Thread t;
	public ClockCanvas(String c, String tz) {
		city = c;
	//也可以通过TimeZone.setTimeZone(String n)函数改变时区，n为时区参数名。
		calendar = new GregorianCalendar(TimeZone.getTimeZone(tz));
		t = new Thread(this);
		t.start();
		setSize(125, 125); // 设置画布大小
		setBackground(Color.black);
	}
	// 重写父类的方法绘制时钟图形
	public void paint(Graphics g) {
	Graphics2D g2d = (Graphics2D) g;
	BasicStroke bstroke = new BasicStroke(2.0f);
	g2d.setStroke(bstroke);
	g2d.setColor(Color.green);
	g2d.drawOval(0, 0, 100, 100);
	bstroke = new BasicStroke(5.0f);
	g2d.setStroke(bstroke);
	g2d.drawLine(50, 0, 50, 5);
	g2d.drawLine(0, 50, 5, 50);
	g2d.drawLine(50, 95, 50, 98);
	g2d.drawLine(95, 50, 98, 50);
	double hourAngle = 2 * Math.PI * (seconds - 3 * 60 * 60)
	/ (12 * 60 * 60);
	double minuteAngle = 2 * Math.PI * (seconds - 15 * 60) / (60 * 60);
	double secondAngle = 2 * Math.PI * (seconds - 15) / (60);
	bstroke = new BasicStroke(5.0f);
	g2d.setStroke(bstroke);
	g2d.drawLine(50, 50, 50 + (int) (30 * Math.cos(hourAngle)),
	50 + (int) (30 * Math.sin(hourAngle)));
	bstroke = new BasicStroke(3.0f);
	g2d.setStroke(bstroke);
	g2d.drawLine(50, 50, 50 + (int) (40 * Math.cos(minuteAngle)),
	50 + (int) (40 * Math.sin(minuteAngle)));
	bstroke = new BasicStroke(1.0f);
	g2d.setStroke(bstroke);
	g2d.drawLine(50, 50, 50 + (int) (45 * Math.cos(secondAngle)),
	50 + (int) (45 * Math.sin(secondAngle)));
	g2d.setColor(Color.red);
	g2d.drawString(city, 35, 115);
	}
	public void timeElapsed() {
	//new Date()()获得当前时间
	//System.out.println(new Date());
	calendar.setTime(new Date());
	seconds = calendar.get(Calendar.HOUR) * 60 * 60
	+ calendar.get(Calendar.MINUTE) * 60
	+ calendar.get(Calendar.SECOND);
	}
	public void run() {
	try {
	while (true) {
	Thread.sleep(300);
	timeElapsed();
	repaint();
	}
	} catch (InterruptedException e) {
	}
	}
}
