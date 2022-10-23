package myCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class Alarm  extends JFrame implements ActionListener{
	/**
	 * 变量-->小时
	 */
	private String h;
	/**
	 * 变量-->分钟
	 */
	private String m;
	/**
	 * 变量-->秒
	 */
	private String s;
	/**
	 * 时
	 */
	private javax.swing.JComboBox dorp_down_hours;
	/**
	 * 分
	 */
	private javax.swing.JComboBox dorp_down_minute;
	/**
	 * 秒
	 */
	private JButton confirm=new JButton("确定");

	private javax.swing.JComboBox drop_down_second;
	int x_size, y_size;

	public Alarm(){
		this.setLayout(null);
		dorp_down_hours = new javax.swing.JComboBox();
		dorp_down_minute = new javax.swing.JComboBox();
		drop_down_second = new javax.swing.JComboBox();
		dorp_down_hours.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "关闭", "00", "01", "02", "03", "04", "05", "06",
						"07", "08", "09", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23" }));
		dorp_down_hours.setName("dorp_down_hours"); // NOI18N
		dorp_down_hours.addActionListener(this);
		dorp_down_hours.setBounds(10,50,50,30);
		this.add(dorp_down_hours);
		JLabel j1=new JLabel("时");
		j1.setBounds(10,20,50,30);
		this.add(j1);
		dorp_down_minute.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "关闭", "00", "01", "02", "03", "04", "05", "06",
						"07", "08", "09", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31", "32", "33",
						"34", "35", "36", "37", "38", "39", "40", "41", "42",
						"43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59" }));
		dorp_down_minute.setName("dorp_down_minute"); // NOI18N
		dorp_down_minute.addActionListener(this);
		dorp_down_minute.setBounds(80,50,50,30);
		this.add(dorp_down_minute);
		JLabel j2=new JLabel("分");
		j2.setBounds(80,20,50,30);
		this.add(j2);

		// 添加confirm button
		confirm.setBounds(100,90,80,30);
		this.add(confirm);
		JButton close=new JButton("关闭");
		close.setBounds(200,90,80,30);
		this.add(close);
		close.addActionListener(new AnActionClose());
		drop_down_second.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "关闭", "00", "01", "02", "03", "04", "05", "06",
						"07", "08", "09", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23", "24",
						"25", "26", "27", "28", "29", "30", "31", "32", "33",
						"34", "35", "36", "37", "38", "39", "40", "41", "42",
						"43", "44", "45", "46", "47", "48", "49", "50", "51",
						"52", "53", "54", "55", "56", "57", "58", "59" }));
		drop_down_second.setName("drop_down_second"); // NOI18N
		drop_down_second.addActionListener(this);
		drop_down_second.setBounds(150,50,50,30);
		this.add(drop_down_second);
		JLabel j3=new JLabel("秒");
		j3.setBounds(150,20,50,30);
		this.add(j3);
		this.setBackground(Color.yellow);
		this.setLayout(new BorderLayout());

		this.setTitle("闹钟");
		setSize(300,300);

		setVisible(true);
		x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 150) / 2, (y_size - 250) / 2);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * 检查时，分，秒的值,如果都不是"关闭"，那么</br>
	 *
	 * <li>stop按钮要显示出来</li><br/>
	 * <li>result要显示出来剩余时间</li><br/>
	 * <li>ring_setup要设置为不可用</li> <li>listening_test按钮为不可用</li> <li>
	 * listening_test_stop按钮为不可用</li>
	 */
	private void hsmCheck() {
		if (h != "关闭" && m != "关闭" && s != "关闭") {
			myListener();

		}
	}

	class AnActionClose implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			 setVisible(false);
		}
	}
	/**
	 * 给h,m,s三个变量赋值，并判断他们的值
	 */
	private void valueJudgment() {
		h = dorp_down_hours.getSelectedItem().toString();
		m = dorp_down_minute.getSelectedItem().toString();
		s = drop_down_second.getSelectedItem().toString();
		hsmCheck();
	}
	public void actionPerformed(ActionEvent e) {
		/**
		 * 获取dorp_down_hours，dorp_down_minute，drop_down_second的值
		 */
		if (e.getSource() == dorp_down_hours) {
			valueJudgment();
		}
		if (e.getSource() == dorp_down_minute) {
			valueJudgment();
		}
		if (e.getSource() == drop_down_second) {
			valueJudgment();
		}

	}
	/**
	 * 获取当前小时数
	 *
	 * @return 返回当前小时数
	 */
	private int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 获取当前分钟数
	 *
	 * @return 返回当前分钟数
	 */
	private int getMunite() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * 获取当前秒钟数
	 *
	 * @return 返回当前秒钟数
	 */
	private int getSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	/**
	 * 播放声音的监听器
	 */
	public void myListener() {
		new Thread(new Runnable() {// 设置一个线程
					public void run() {
						while (true) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							executeSound();// 播放声音
						}
					}
				}).start();
	}
	/**
	 * 时间到了的时候就播放声音
	 */
	public void executeSound() {
		// 获取系统时，分，秒
		int h = getHour();
		int m = getMunite();
		int s = getSecond();
		// 获取设置的响铃时间
		int dh = 0;
		int dm = 0;
		int ds = 0;
		if (dorp_down_hours.getSelectedIndex() != 0) {
			dh = dorp_down_hours.getSelectedIndex() - 1;
		}
		if (dorp_down_minute.getSelectedIndex() != 0) {
			dm = dorp_down_minute.getSelectedIndex() - 1;
		}
		if (drop_down_second.getSelectedIndex() != 0) {
			ds = drop_down_second.getSelectedIndex() - 1;
		}
		int hour = dh - h;
		int min = dm - m;
		int sec = ds - s;
		if (hour == 0 && min == 0 && sec == 0) {
			// 主窗体设置为可见
			setVisible(true);
			// 设置窗口前端显示
			setExtendedState(JFrame.NORMAL);
			setAlwaysOnTop(true);
			// 播放声音
			new Thread(new AlarmSound()).start();

		}
	}



}

