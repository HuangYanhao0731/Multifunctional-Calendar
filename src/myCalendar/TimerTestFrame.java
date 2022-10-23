package myCalendar;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.*;
public class TimerTestFrame extends Frame{
	private static final long serialVersionUID = 1L;
	 int x_size, y_size;
	public TimerTestFrame(String s) {
		super(s);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
				//System.exit(0);
			}
		});
		setLayout(new GridLayout(2, 6));
		ClockCanvas clk1 = new ClockCanvas("北京", "GMT+8"); // 创建时钟
		ClockCanvas clk2 = new ClockCanvas("巴黎", "GMT+2");
		ClockCanvas clk3 = new ClockCanvas("华盛顿", "GMT-4");
		ClockCanvas clk4 = new ClockCanvas("洛衫矶", "GMT-7");
		ClockCanvas clk5 = new ClockCanvas("伦敦", "GMT+1");
		ClockCanvas clk6 = new ClockCanvas("芝加哥", "GMT-5");
		add(clk1);
		add(clk2);
		add(clk3);
		add(clk4);
		add(clk5);
		add(clk6);
		setSize(500, 350); // 设置框架宽高
		x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 250) / 2, (y_size - 250) / 2);
		setVisible(true);
	}
}
