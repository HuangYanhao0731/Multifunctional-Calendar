package myCalendar;


import myCalendar.utils.DateUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DateQueryFrame extends JFrame implements ActionListener {
	JLabel y_label = new JLabel("年份");
	JLabel m_label = new JLabel("月份");
	JLabel date_label = new JLabel("日");

	JComboBox com1 = new JComboBox();
	JComboBox com2 = new JComboBox();
	JComboBox com3 = new JComboBox();

	JLabel resultLabel=new JLabel("查询结果");
	JLabel result=new JLabel("");


	String festival=null;
	String solarTerm=null;

	Calendar now = Calendar.getInstance(); // 实例化Calendar

	public void init(){
		int year, month_num, first_day_num;
		String log[] = { "日", "一", "二", "三", "四", "五", "六" };

		for (int i = 1; i < 10000; i++) {
			com1.addItem("" + i);
		}
		for (int i = 1; i < 13; i++) {
			com2.addItem("" + i);
		}
		month_num = (int) (now.get(Calendar.MONTH)); // 得到当前时间的月份
		year = (int) (now.get(Calendar.YEAR)); // 得到当前时间的年份
		com1.setSelectedIndex(year - 1); // 设置下拉列表显示为当前年???????????
		com2.setSelectedIndex(month_num); // 设置下拉列表显示为当前月
		ArrayList<Integer> monthDays = getMonthDays(month_num);
		for (Integer monthDay : monthDays) {
			com3.addItem(monthDay+"");
		}
	}



	public DateQueryFrame() {
		setTitle("日期查询");
		setLayout(null);
		setBounds(600,300,500,500);
		//设置控件位置和大小

		//设置控件位置和大小
		y_label.setBounds(50,20,100,100);
		com1.setBounds(100,50,200,30);
		m_label.setBounds(50,70,100,100);
		com2.setBounds(100,100,200,30);
		date_label.setBounds(50,120,100,100);
		com3.setBounds(100,150,200,30);

		JButton yesButton=new JButton("确定");
		yesButton.setBounds(100,300,80,30);
		JButton noButton=new JButton("取消");
		noButton.setBounds(200,300,80,30);


		resultLabel.setBounds(100,350,80,30);
		result.setBounds(160,350,180,30);



		add(y_label);
		add(com1);
		add(m_label);
		add(com2);
		add(date_label);
		add(com3);
		add(yesButton);
		add(noButton);
		add(resultLabel);
		add(result);
		//给添加选课设置的确定和取消按钮的监听事件
		yesButton.setActionCommand("确定");
		yesButton.addActionListener(this);
		noButton.setActionCommand("取消");
		noButton.addActionListener(this);
		setVisible(true);
		init();
		com2.addActionListener(new ClockAction());
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	public static  ArrayList<Integer> getMonthDays(Integer month){
		int dayLength = LocalDate.now().withMonth(month).lengthOfMonth();
		ArrayList result= new ArrayList<>();
		for (int i = 0; i < dayLength; i++) {
			result.add(Integer.valueOf(i+1));
		}
		return  result;

	}

	class ClockAction implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			int c_month;
			c_month = Integer.parseInt(com2.getSelectedItem().toString()); // 得到当前月份，并减1,计算机中的月为0－11
			ArrayList<Integer> monthDays = getMonthDays(c_month);
			com3.removeAllItems();;
			for (Integer monthDay : monthDays) {
				com3.addItem(monthDay+"");
			}


		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("确定")) {
			int c_year, c_month,c_week,c_day,week_num;
			c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
			c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
			c_day = Integer.parseInt(com3.getSelectedItem().toString()); // 得到当前所选天
			com1.setSelectedIndex(c_year);
			com2.setSelectedIndex(c_month);
			Calendar calendar=Calendar.getInstance();
			calendar.set(c_year, c_month, c_day); // 设置时间为所要查询的年月的第一天
			week_num = (int) (calendar.get(Calendar.DAY_OF_WEEK));// 得到第一天的星期
			java.util.Date time = calendar.getTime();
			String format = DateUtil.format(time, DateUtil.DATE_FORMAT);
			Map getWeek=new HashMap<>();
			getWeek.put("1","星期一");
			getWeek.put("2","星期二");
			getWeek.put("3","星期三");
			getWeek.put("4","星期四");
			getWeek.put("5","星期五");
			getWeek.put("6","星期六");
			getWeek.put("7","星期天");
			String weekStr = (String) getWeek.get(String.valueOf(week_num));
			System.out.println(format);
			System.out.println(weekStr);
			Lunar lunar=new Lunar();

			String lunarStr = lunar.getLunarDate(c_year, c_month + 1, c_day);
			System.out.println(lunarStr);
			String resetday = Resetday(c_month, c_day);
			if(resetday!=null){
				result.setText(format+weekStr+lunarStr+resetday);
			}


		}
	}
	public String  Resetday(int month_log,int count) {
       String getFestival="";
		if(month_log==9&&count==1){
			getFestival="国庆";
		}
		else if(month_log==0&&count==1){
			getFestival="元旦";

		}
		else if(month_log==11&&count==24){
			getFestival="平安夜";
		}
		else if(month_log==11&&count==25){
			getFestival="圣诞";

		}
		else if(month_log==1&&count==14){
			getFestival="情人节";
		}
		else if(month_log==4&&count==1){
			getFestival="劳动节";
		}
		return getFestival;

	}

}
