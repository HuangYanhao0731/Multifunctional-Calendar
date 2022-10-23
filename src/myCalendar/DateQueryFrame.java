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
	JLabel y_label = new JLabel("���");
	JLabel m_label = new JLabel("�·�");
	JLabel date_label = new JLabel("��");

	JComboBox com1 = new JComboBox();
	JComboBox com2 = new JComboBox();
	JComboBox com3 = new JComboBox();

	JLabel resultLabel=new JLabel("��ѯ���");
	JLabel result=new JLabel("");


	String festival=null;
	String solarTerm=null;

	Calendar now = Calendar.getInstance(); // ʵ����Calendar

	public void init(){
		int year, month_num, first_day_num;
		String log[] = { "��", "һ", "��", "��", "��", "��", "��" };

		for (int i = 1; i < 10000; i++) {
			com1.addItem("" + i);
		}
		for (int i = 1; i < 13; i++) {
			com2.addItem("" + i);
		}
		month_num = (int) (now.get(Calendar.MONTH)); // �õ���ǰʱ����·�
		year = (int) (now.get(Calendar.YEAR)); // �õ���ǰʱ������
		com1.setSelectedIndex(year - 1); // ���������б���ʾΪ��ǰ��???????????
		com2.setSelectedIndex(month_num); // ���������б���ʾΪ��ǰ��
		ArrayList<Integer> monthDays = getMonthDays(month_num);
		for (Integer monthDay : monthDays) {
			com3.addItem(monthDay+"");
		}
	}



	public DateQueryFrame() {
		setTitle("���ڲ�ѯ");
		setLayout(null);
		setBounds(600,300,500,500);
		//���ÿؼ�λ�úʹ�С

		//���ÿؼ�λ�úʹ�С
		y_label.setBounds(50,20,100,100);
		com1.setBounds(100,50,200,30);
		m_label.setBounds(50,70,100,100);
		com2.setBounds(100,100,200,30);
		date_label.setBounds(50,120,100,100);
		com3.setBounds(100,150,200,30);

		JButton yesButton=new JButton("ȷ��");
		yesButton.setBounds(100,300,80,30);
		JButton noButton=new JButton("ȡ��");
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
		//�����ѡ�����õ�ȷ����ȡ����ť�ļ����¼�
		yesButton.setActionCommand("ȷ��");
		yesButton.addActionListener(this);
		noButton.setActionCommand("ȡ��");
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
			c_month = Integer.parseInt(com2.getSelectedItem().toString()); // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
			ArrayList<Integer> monthDays = getMonthDays(c_month);
			com3.removeAllItems();;
			for (Integer monthDay : monthDays) {
				com3.addItem(monthDay+"");
			}


		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("ȷ��")) {
			int c_year, c_month,c_week,c_day,week_num;
			c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
			c_year = Integer.parseInt(com1.getSelectedItem().toString()); // �õ���ǰ��ѡ���
			c_day = Integer.parseInt(com3.getSelectedItem().toString()); // �õ���ǰ��ѡ��
			com1.setSelectedIndex(c_year);
			com2.setSelectedIndex(c_month);
			Calendar calendar=Calendar.getInstance();
			calendar.set(c_year, c_month, c_day); // ����ʱ��Ϊ��Ҫ��ѯ�����µĵ�һ��
			week_num = (int) (calendar.get(Calendar.DAY_OF_WEEK));// �õ���һ�������
			java.util.Date time = calendar.getTime();
			String format = DateUtil.format(time, DateUtil.DATE_FORMAT);
			Map getWeek=new HashMap<>();
			getWeek.put("1","����һ");
			getWeek.put("2","���ڶ�");
			getWeek.put("3","������");
			getWeek.put("4","������");
			getWeek.put("5","������");
			getWeek.put("6","������");
			getWeek.put("7","������");
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
			getFestival="����";
		}
		else if(month_log==0&&count==1){
			getFestival="Ԫ��";

		}
		else if(month_log==11&&count==24){
			getFestival="ƽ��ҹ";
		}
		else if(month_log==11&&count==25){
			getFestival="ʥ��";

		}
		else if(month_log==1&&count==14){
			getFestival="���˽�";
		}
		else if(month_log==4&&count==1){
			getFestival="�Ͷ���";
		}
		return getFestival;

	}

}
