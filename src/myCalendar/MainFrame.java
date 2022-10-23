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
    Image image = new ImageIcon("E:\\作业\\背景.png").getImage();
    ImageIcon imageIcon = new ImageIcon("E:\\作业\\背景.png");
    JPanel panel_demo = new BackgroundPanel(image);
    JPanel panel = new JPanel(new BorderLayout());

//    用于分层
    JLayeredPane layeredPane = new JLayeredPane();
//    图片导入

//    JLabel background = new JLabel(image);
//    JPanel background = new JPanel();
    JPanel panel1 = new JPanel();
    JPanel panel2 = new JPanel(new GridLayout(7, 7));
    JPanel panel3 = new JPanel();
    JLabel[] label = new JLabel[49];
    JLabel y_label = new JLabel("年份");
    JLabel m_label = new JLabel("月份");
//    y_label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
    JComboBox com1 = new JComboBox();
    JComboBox com2 = new JComboBox();


    JButton but1=new JButton("上个月");
    JButton but2=new JButton("下个月");
    JButton but3=new JButton("闹钟");
    JButton but4=new JButton("查询");
    JButton but5=new JButton("备忘录");
    JButton but6=new JButton("日期查询");
    JButton but7=new JButton("天气查询");
    int re_year, re_month;
    int x_size, y_size;
    String year_num;
    static  Calendar now = Calendar.getInstance(); // 实例化Calendar
    MainFrame() {
        super("万年历");
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
            label[i] = new JLabel("", JLabel.CENTER);// 将显示的字符设置为居中
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

             c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
             c_year = Integer.parseInt(com1.getSelectedItem().toString())-1; // 得到当前所选年份
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
             c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
             c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
             c_week = use(c_year, c_month); // 调用函数use，得到星期几
             Resetday(c_week, c_year, c_month); // 调用函数Resetday

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

   		new TimerTestFrame("不同时区部分城市时间");
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

            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
            if(c_month==11)
            {
           	 c_year=c_year+1;
           	 c_month=0;
            }
            c_week = use(c_year, c_month); // 调用函数use，得到星期几
            Resetday(c_week, c_year, c_month); // 调用函数Resetday

   	 }

    }

     */

     class ClockAction implements ActionListener {
         public void actionPerformed(ActionEvent arg0) {
            int c_year, c_month, c_week;
            c_year = Integer.parseInt(com1.getSelectedItem().toString()); // 得到当前所选年份
            c_month = Integer.parseInt(com2.getSelectedItem().toString()) - 1; // 得到当前月份，并减1,计算机中的月为0－11
            c_week = use(c_year, c_month); // 调用函数use，得到星期几
            Resetday(c_week, c_year, c_month); // 调用函数Resetday
        }
    }

     public void Init() {
        int year, month_num, first_day_num;
         String log[] = { "日", "一", "二", "三", "四", "五", "六" };
         for (int i = 0; i < 7; i++) {
            label[i].setText(log[i]);
        }

         for (int i = 0; i < 49; i = i + 7) {
            label[i].setForeground(Color.red); // 将星期日的日期设置为红色
        }
         for (int i = 6; i < 49; i = i + 7) {
            label[i].setForeground(Color.blue);// 将星期六的日期设置为hong色
        }
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
        first_day_num = use(year, month_num);
        Resetday(first_day_num, year, month_num);
    }

     public static int use(int reyear, int remonth) {
        int week_num;
        now.set(reyear, remonth, 1); // 设置时间为所要查询的年月的第一天
        week_num = (int) (now.get(Calendar.DAY_OF_WEEK));// 得到第一天的星期

        return week_num;
    }

   // @SuppressWarnings("deprecation")
     public void Resetday(int week_log, int year_log, int month_log) {
    	 //System.out.println(week_log+"\t"+year_log+"\t"+month_log);
        int month_day_score; // 存储月份的天数
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


        Date date = new Date(year_log, month_log + 1, 1); // now MONTH是从0开始的, 对于一月第几天来说，DAY_OF_MONTH第一天就是1. 对于一年第几个月来说，MONTH一月份是0，二月份是1...
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1); // 前个月
        month_day_score = cal.getActualMaximum(Calendar.DAY_OF_MONTH);// 最后一天
        month_day=month_day_score;

         for (int i = 7; i < 49; i++) { // 初始化标签
            label[i].setText("");
        }
        week_log = week_log + 6; // 将星期数加6，使显示正确
        month_day_score = month_day_score + week_log;

        lunar=new Lunar();
        for(int i=0;i<month_day;i++)
        {
        	//System.out.println("年"+year_log);
        	//System.out.println( "月"+(month_log+1)+""+(i+1));
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
        		label[i].setText(count +"国庆" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==0&&count==1){
        		label[i].setText(count +"元旦" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==11&&count==24){
        		label[i].setText(count +"平安夜" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==11&&count==25){
        		label[i].setText(count +"圣诞" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==1&&count==14){
        		label[i].setText(count +"情人节" );
        		label[i].setForeground(Color.red);
        	}
        	else if(month_log==4&&count==1){
        		label[i].setText(count +"劳动节" );
        		label[i].setForeground(Color.red);
        	}

//        	农历节日
        	else if(LunarDate[i-week_log].equals("春节")||LunarDate[i-week_log].equals("元宵")
                    ||LunarDate[i-week_log].equals("龙抬头") ||LunarDate[i-week_log].equals("端午")
                    ||LunarDate[i-week_log].equals("七夕") ||LunarDate[i-week_log].equals("中秋")
                    ||LunarDate[i-week_log].equals("重阳")||LunarDate[i-week_log].equals("除夕"))
        	{
        		label[i].setText(count +LunarDate[i-week_log] );
        		label[i].setForeground(Color.red);
        	}
            else if(year_log==2022&&month_log==7&&count==29) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第一周 ");
            }
            else if(year_log==2022&&month_log==8&&count==5) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第二周 ");
            }
            else if(year_log==2022&&month_log==8&&count==12) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第三周 ");
            }
            else if(year_log==2022&&month_log==8&&count==19) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第四周 ");
            }
            else if(year_log==2022&&month_log==8&&count==26) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第五周 ");
            }
            else if(year_log==2022&&month_log==9&&count==3){
                label[i].setText(count +LunarDate[i-week_log]+" 第六周" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==10){
                label[i].setText(count +LunarDate[i-week_log]+" 第七周" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==17){
                label[i].setText(count +LunarDate[i-week_log]+" 第八周" );
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==24){
                label[i].setText(count +LunarDate[i-week_log]);
                label[i].setText(label[i].getText()+" 第九周 ");
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==9&&count==31){
                label[i].setText(count +LunarDate[i-week_log]);
                label[i].setText(label[i].getText()+" 第十周 ");
//                label[i].setForeground(Color.ORANGE);
            }
            else if(year_log==2022&&month_log==10&&count==7) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十一周 ");
            }
            else if(year_log==2022&&month_log==10&&count==14) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十二周 ");
            }
            else if(year_log==2022&&month_log==10&&count==21) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十三周 ");
            }
            else if(year_log==2022&&month_log==10&&count==28) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十四周 ");
            }
            else if(year_log==2022&&month_log==11&&count==5) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十五周 ");
            }
            else if(year_log==2022&&month_log==11&&count==12) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十六周 ");
            }
            else if(year_log==2022&&month_log==11&&count==19) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十七周 ");
            }
            else if(year_log==2022&&month_log==11&&count==26) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十八周 ");
            }
            else if(year_log==2022&&month_log==12&&count==2) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第十九周 ");
            }
            else if(year_log==2022&&month_log==12&&count==9) {
                label[i].setText(count + LunarDate[i - week_log]);
                label[i].setText(label[i].getText() + " 第二十周 ");
            }

        	else{
        		label[i].setText(count +LunarDate[i-week_log] );
        	}
        	String solarTerm=SolarTerm.getSolatName(year_log, (month_log+1)+""+count);
        	//System.out.println((month_log+1)+""+count+"\t节气"+solarTerm);
//             节气写入
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
