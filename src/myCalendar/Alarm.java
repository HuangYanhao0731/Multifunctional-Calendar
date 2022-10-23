package myCalendar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;


public class Alarm  extends JFrame implements ActionListener{
	/**
	 * ����-->Сʱ
	 */
	private String h;
	/**
	 * ����-->����
	 */
	private String m;
	/**
	 * ����-->��
	 */
	private String s;
	/**
	 * ʱ
	 */
	private javax.swing.JComboBox dorp_down_hours;
	/**
	 * ��
	 */
	private javax.swing.JComboBox dorp_down_minute;
	/**
	 * ��
	 */
	private JButton confirm=new JButton("ȷ��");

	private javax.swing.JComboBox drop_down_second;
	int x_size, y_size;

	public Alarm(){
		this.setLayout(null);
		dorp_down_hours = new javax.swing.JComboBox();
		dorp_down_minute = new javax.swing.JComboBox();
		drop_down_second = new javax.swing.JComboBox();
		dorp_down_hours.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "�ر�", "00", "01", "02", "03", "04", "05", "06",
						"07", "08", "09", "10", "11", "12", "13", "14", "15",
						"16", "17", "18", "19", "20", "21", "22", "23" }));
		dorp_down_hours.setName("dorp_down_hours"); // NOI18N
		dorp_down_hours.addActionListener(this);
		dorp_down_hours.setBounds(10,50,50,30);
		this.add(dorp_down_hours);
		JLabel j1=new JLabel("ʱ");
		j1.setBounds(10,20,50,30);
		this.add(j1);
		dorp_down_minute.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "�ر�", "00", "01", "02", "03", "04", "05", "06",
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
		JLabel j2=new JLabel("��");
		j2.setBounds(80,20,50,30);
		this.add(j2);

		// ���confirm button
		confirm.setBounds(100,90,80,30);
		this.add(confirm);
		JButton close=new JButton("�ر�");
		close.setBounds(200,90,80,30);
		this.add(close);
		close.addActionListener(new AnActionClose());
		drop_down_second.setModel(new javax.swing.DefaultComboBoxModel(
				new String[] { "�ر�", "00", "01", "02", "03", "04", "05", "06",
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
		JLabel j3=new JLabel("��");
		j3.setBounds(150,20,50,30);
		this.add(j3);
		this.setBackground(Color.yellow);
		this.setLayout(new BorderLayout());

		this.setTitle("����");
		setSize(300,300);

		setVisible(true);
		x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 150) / 2, (y_size - 250) / 2);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	/**
	 * ���ʱ���֣����ֵ,���������"�ر�"����ô</br>
	 *
	 * <li>stop��ťҪ��ʾ����</li><br/>
	 * <li>resultҪ��ʾ����ʣ��ʱ��</li><br/>
	 * <li>ring_setupҪ����Ϊ������</li> <li>listening_test��ťΪ������</li> <li>
	 * listening_test_stop��ťΪ������</li>
	 */
	private void hsmCheck() {
		if (h != "�ر�" && m != "�ر�" && s != "�ر�") {
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
	 * ��h,m,s����������ֵ�����ж����ǵ�ֵ
	 */
	private void valueJudgment() {
		h = dorp_down_hours.getSelectedItem().toString();
		m = dorp_down_minute.getSelectedItem().toString();
		s = drop_down_second.getSelectedItem().toString();
		hsmCheck();
	}
	public void actionPerformed(ActionEvent e) {
		/**
		 * ��ȡdorp_down_hours��dorp_down_minute��drop_down_second��ֵ
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
	 * ��ȡ��ǰСʱ��
	 *
	 * @return ���ص�ǰСʱ��
	 */
	private int getHour() {
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * ��ȡ��ǰ������
	 *
	 * @return ���ص�ǰ������
	 */
	private int getMunite() {
		return Calendar.getInstance().get(Calendar.MINUTE);
	}

	/**
	 * ��ȡ��ǰ������
	 *
	 * @return ���ص�ǰ������
	 */
	private int getSecond() {
		return Calendar.getInstance().get(Calendar.SECOND);
	}

	/**
	 * ���������ļ�����
	 */
	public void myListener() {
		new Thread(new Runnable() {// ����һ���߳�
					public void run() {
						while (true) {
							try {
								Thread.sleep(1000);
							} catch (Exception e) {
								e.printStackTrace();
							}
							executeSound();// ��������
						}
					}
				}).start();
	}
	/**
	 * ʱ�䵽�˵�ʱ��Ͳ�������
	 */
	public void executeSound() {
		// ��ȡϵͳʱ���֣���
		int h = getHour();
		int m = getMunite();
		int s = getSecond();
		// ��ȡ���õ�����ʱ��
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
			// ����������Ϊ�ɼ�
			setVisible(true);
			// ���ô���ǰ����ʾ
			setExtendedState(JFrame.NORMAL);
			setAlwaysOnTop(true);
			// ��������
			new Thread(new AlarmSound()).start();

		}
	}



}

