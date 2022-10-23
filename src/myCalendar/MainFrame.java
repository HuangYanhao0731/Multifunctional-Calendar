package myCalendar;

import myCalendar.weather.ui.WeatherFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;


 class MainFrame extends JFrame {
     /**
     *
     */
    private static final long serialVersionUID = 1L;
    Image image = new ImageIcon("E:\\��ҵ\\����.png").getImage();
    ImageIcon imageIcon = new ImageIcon("E:\\��ҵ\\����.png");
    JPanel panel_demo = new BackgroundPanel(image);
    JPanel panel = new JPanel(new BorderLayout());

//    ���ڷֲ�
    JLayeredPane layeredPane = new JLayeredPane();
//    ͼƬ����

//    JLabel background = new JLabel(image);
//    JPanel background = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(new GridLayout(7, 7));
    JPanel panel3 = new JPanel();
    JLabel[] label = new JLabel[49];
    JLabel y_label = new JLabel("���");
    JLabel m_label = new JLabel("�·�");
//    y_label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
    JComboBox com1 = new JComboBox();
    JComboBox com2 = new JComboBox();


    JButton but1=new JButton("�ϸ���");
    JButton but2=new JButton("�¸���");
    JButton but3=new JButton("����");
    JButton but4=new JButton("��ѯ");
    JButton but5=new JButton("����¼");
    JButton but6=new JButton("���ڲ�ѯ");
    JButton but7=new JButton("������ѯ");
    int re_year, re_month;
    int x_size, y_size;
    String year_num;
    static  Calendar now = Calendar.getInstance(); // ʵ����Calendar
    MainFrame() {
        super("������");
//        panel.setSize(1800, 1700);
        setBounds(0,0,imageIcon.getIconWidth(),imageIcon.getIconHeight());
//        panel.add(background,new Integer(Integer.MIN_VALUE));
//        background.add(jLabel);
        x_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth());
        y_size = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        setLocation((x_size - 300) / 2, (y_size - 350) / 2);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel1.add(but1);
        panel1.add(y_label);
        panel1.add(com1);
        panel1.add(m_label);
        panel1.add(com2);


        panel1.add(but2);
        panel1.add(but3);
        panel1.add(but4);
        panel1.add(but5);
        panel1.add(but6);
        panel1.add(but7);
         for (int i = 0; i < 49; i++) {
            label[i] = new JLabel("", JLabel.CENTER);// ����ʾ���ַ�����Ϊ����
//            label[i].setSize(100,0);
             label[i].setOpaque(false);

            panel2.add(label[i],BorderLayout.CENTER);
        }




        panel3.add(new Clock(this));
        panel.add(panel1, BorderLayout.NORTH);
//        panel.add(background,BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.CENTER);
        panel.add(panel3, BorderLayout.SOUTH);

//        layeredPane.add(panel1, JLayeredPane.PALETTE_LAYER);
//        layeredPane.add(panel2, JLayeredPane.MODAL_LAYER);
//        layeredPane.add(panel3, JLayeredPane.POPUP_LAYER);
//
//        layeredPane.add(background,JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(panel);
        panel.setBackground(Color.white);
        panel1.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel3.setBackground(Color.gray);
        panel1.setOpaque(false);
        panel2.setOpaque(false);
        panel3.setOpaque(false);
//        panel.setOpaque(false);
        panel.setSize(800,700);
        panel_demo.add(panel);

        Init();
        but1.addActionListener(new AnAction());
        but2.addActionListener(new AnAction());
        but3.addActionListener(new AnAction3());
        but4.addActionListener(new AnAction4());
        but5.addActionListener(new AnAction5());
        com1.addActionListener(new ClockAction());
        com2.addActionListener(new ClockAction());

        but6.addActionListener(new AnAction6());
        but7.addActionListener(new AnAction7());
        setContentPane(panel_demo);
//        getContentPane().add(background);


        setContentPane(panel);

        setVisible(true);
        setResizable(true);
    }


    class AnAction implements ActionListener
     {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 int c_year, c_month,c_week;

             c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
             c_year = Integer.parseInt(com1.getSelectedItem().toString())-1; // �õ���ǰ��ѡ���
             if(e.getSource()==but1)
             {
            	 if(c_month==0)
                 {
                	 c_year=c_year-1;
                	 c_month=11;
                 }
            	 else
            		 c_month=c_month-1;

             }
             if(e.getSource()==but2)
             {
            	 if(c_month==11)
                 {
                	 c_year=c_year+1;
                	 c_month=0;
                 }
            	 else
            		 c_month=c_month+1;
             }
             com1.setSelectedIndex(c_year);
             com2.setSelectedIndex(c_month);
             c_year = Integer.parseInt(com1.getSelectedItem().toString()); // �õ���ǰ��ѡ���
             c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
             c_week = use(c_year, c_month); // ���ú���use���õ����ڼ�
             Resetday(c_week, c_year, c_month); // ���ú���Resetday

    	 }

     }
    class AnAction3 implements ActionListener
    {
   	 public void actionPerformed(ActionEvent e)
   	 {

   		new Alarm();
   	 }

    }

    class AnAction6 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            new DateQueryFrame();
        }
    }
    class AnAction5 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
             new Memo();
        }
    }
    class AnAction4 implements ActionListener
    {
   	 public void actionPerformed(ActionEvent e)
   	 {

   		new TimerTestFrame("��ͬʱ�����ֳ���ʱ��");
   	 }

    }
    class AnAction7 implements ActionListener{
        public void actionPerformed(ActionEvent e){
            new WeatherFrame();
        }
    }
   /* class NextAction implements ActionListener
    {
   	 public void actionPerformed(ActionEvent arg0)
   	 {
   		 int c_year, c_month,c_week;

            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // �õ���ǰ��ѡ���
            if(c_month==11)
            {
           	 c_year=c_year+1;
           	 c_month=0;
            }
            c_week = use(c_year, c_month); // ���ú���use���õ����ڼ�
            Resetday(c_week, c_year, c_month); // ���ú���Resetday

   	 }

    }

     */

     class ClockAction implements ActionListener {
         public void actionPerformed(ActionEvent arg0) {
            int c_year, c_month, c_week;
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // �õ���ǰ��ѡ���
            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // �õ���ǰ�·ݣ�����1,������е���Ϊ0��11
            c_week = use(c_year, c_month); // ���ú���use���õ����ڼ�
            Resetday(c_week, c_year, c_month); // ���ú���Resetday
        }
    }

     public void Init() {
        int year, month_num, first_day_num;
         String log[] = { "��", "һ", "��", "��", "��", "��", "��" };
         for (int i = 0; i < 7; i++) {
            label[i].setText(log[i]);
        }

         for (int i = 0; i < 49; i = i + 7) {
            label[i].setForeground(Color.red); // �������յ���������Ϊ��ɫ
        }
         for (int i = 6; i < 49; i = i + 7) {
            label[i].setForeground(Color.blue);// ������������������Ϊhongɫ
        }
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
        first_day_num = use(year, month_num);
        Resetday(first_day_num, year, month_num);
    }

     public static int use(int reyear, int remonth) {
        int week_num;
        now.set(reyear, remonth, 1); // ����ʱ��Ϊ��Ҫ��ѯ�����µĵ�һ��
        week_num = (int) (now.get(Calendar.DAY_OF_WEEK));// �õ���һ�������

        return week_num;
    }

   // @SuppressWarnings("deprecation")
     public void Resetday(int week_log, int year_log, int month_log) {
    	 //System.out.println(week_log+"\t"+year_log+"\t"+month_log);
        int month_day_score; // �洢�·ݵ�����
        int count;
        Lunar lunar;
        int month_day;
        String[] LunarDate=new String[49];

        month_day_score = 0;
        count = 1;


        for (int i = 1; i < 49; i ++) {
        	for(int j=0;j<49;j=j+7)
        	{
        		if(i!=j&&i!=j+6)
        			label[i].setForeground(Color.black);
        	}
        }


        Date date = new Date(year_log, month_log + 1, 1); // now MONTH�Ǵ�0��ʼ��, ����һ�µڼ�����˵��DAY_OF_MONTH��һ�����1. ����һ��ڼ�������˵��MONTHһ�·���0�����·���1...
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1); // ǰ����
        month_day_score = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// ���һ��
        month_day=month_day_score;

         for (int i = 7; i < 49; i++) { // ��ʼ����ǩ
            label[i].setText("");
        }
        week_log = week_log + 6; // ����������6��ʹ��ʾ��ȷ
        month_day_score = month_day_score + week_log;

        lunar=new Lunar();
        for(int i=0;i<month_day;i++)
        {
        	//System.out.println("��"+year_log);
        	//System.out.println( "��"+(month_log+1)+""+(i+1));
        	LunarDate[i]=lunar.getLunarDate( year_log,  month_log+1, i+1);
        	//String name=SolarTerm.getSolatName(year_log, (month_log+1)+"\t"+(i+1));
        	//System.out.println(name);
        	//System.out.println(LunarDate[i]);
        }

        //System.out.println(week_log);
        //System.out.println(month_day_score);


         for (int i = week_log; i < month_day_score; i++, count++) {
        	 //System.out.println(count);
        	if(month_log==9&&count==1){
        		label[i].setText(count +"����" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==0&&count==1){
        		label[i].setText(count +"Ԫ��" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==11&&count==24){
        		label[i].setText(count +"ƽ��ҹ" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==11&&count==25){
        		label[i].setText(count +"ʥ��" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==1&&count==14){
        		label[i].setText(count +"���˽�" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==4&&count==1){
        		label[i].setText(count +"�Ͷ���" );
        		label[i].setForeground(Color.red);
        	}

//        	ũ������
        	else if(LunarDate[i-week_log].equals("����")||LunarDate[i-week_log].equals("Ԫ��")
                    ||LunarDate[i-week_log].equals("��̧ͷ") ||LunarDate[i-week_log].equals("����")
                    ||LunarDate[i-week_log].equals("��Ϧ") ||LunarDate[i-week_log].equals("����")
                    ||LunarDate[i-week_log].equals("����")||LunarDate[i-week_log].equals("��Ϧ"))
        	{
        		label[i].setText(count +LunarDate[i-week_log] );
        		label[i].setForeground(Color.red);
        	}
            else if(year_log==2022&&month_log==7&&count==29) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��һ�� ");
            }
            else if(year_log==2022&&month_log==8&&count==5) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " �ڶ��� ");
            }
            else if(year_log==2022&&month_log==8&&count==12) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ������ ");
            }
            else if(year_log==2022&&month_log==8&&count==19) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ������ ");
            }
            else if(year_log==2022&&month_log==8&&count==26) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ������ ");
            }
            else if(year_log==2022&&month_log==9&&count==3){
                label[i].setText(count +LunarDate[i-week_log]+" ������" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==10){
                label[i].setText(count +LunarDate[i-week_log]+" ������" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==17){
                label[i].setText(count +LunarDate[i-week_log]+" �ڰ���" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==24){
                label[i].setText(count +LunarDate[i-week_log]);
                label[i].setText(label[i].getText()+" �ھ��� ");
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==31){
                label[i].setText(count +LunarDate[i-week_log]);
                label[i].setText(label[i].getText()+" ��ʮ�� ");
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==10&&count==7) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮһ�� ");
            }
            else if(year_log==2022&&month_log==10&&count==14) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==10&&count==21) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==10&&count==28) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==11&&count==5) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==11&&count==12) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==11&&count==19) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==11&&count==26) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==12&&count==2) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " ��ʮ���� ");
            }
            else if(year_log==2022&&month_log==12&&count==9) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " �ڶ�ʮ�� ");
            }

        	else{
        		label[i].setText(count +LunarDate[i-week_log] );
        	}
        	String solarTerm=SolarTerm.getSolatName(year_log, (month_log+1)+""+count);
        	//System.out.println((month_log+1)+""+count+"\t����"+solarTerm);
//             ����д��
        	if(solarTerm!=null){
        		//label[i].getText()+solarTerm
        		label[i].setText(count+solarTerm);
        		label[i].setForeground(Color.blue);
        	}
        }
    }

     public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
         UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        JFrame.setDefaultLookAndFeelDecorated(true);
        new MainFrame();

    }
}
