package myCalendar.weather.ui;

import myCalendar.weather.dao.WeatherDao;
import myCalendar.weather.dao.WeatherDaoImpl;
import myCalendar.weather.domain.Weather;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
public class WeatherFrame extends MyFrame{
    private WeatherDao dao = new WeatherDaoImpl(); //�����ӿ�
    private JPanel topJPanel = new JPanel(); //ͷ�����
    private JPanel contentJPanel = new JPanel(); //�������
    private Map<String,String> areaIdMap = new LinkedHashMap<>() //���д��룬LinkedHashMap��ʹ���ݰ�˳�����
    {{
        put("�Ͼ���","320100");
        put("������","330100");
        put("������","110000");
        put("�����","120000");
        put("�ӱ�ʡ","130000");
        put("ɽ��ʡ","140000");
        put("���ɹ�","150000");
        put("����ʡ","210000");
        put("����ʡ","220000");
        put("������ʡ","230000");
        put("�Ϻ���","310000");
        put("������","330200");
        put("����ʡ","340000");
        put("����ʡ","350000");
        put("����ʡ","360000");
    }};

    public WeatherFrame(){
        super("����С����",400,420);

        getContentPane().add(topJLabel(),BorderLayout.NORTH); //��ͷ�������ڴ��ڶ���
        getContentPane().add(contentJPanel,BorderLayout.CENTER); //�����������ڴ����м�

        setVisible(true); //���ô��ڿɼ�
    }

    //ͷ���������
    public JPanel topJLabel(){
        FlowLayout flowLayout = (FlowLayout) topJPanel.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);

        JLabel label = new JLabel("��ѡ�������");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(label);

        String[] categorys = areaIdMap.keySet().toArray(new String[0]); //��ȡ�����б�
        JComboBox choice = new JComboBox(categorys); //�����˵����
        choice.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(choice);

        contentJLable((String) choice.getSelectedItem()); //�����м��������

        choice.addActionListener(e -> { //ѡ�������˵��е����ݴ���
            JComboBox cb = (JComboBox) e.getSource(); //��ȡ����
            contentJLable((String) cb.getSelectedItem()); //�����м��������
        });

        JButton historyButton = new JButton("�鿴��ʷ��¼");
        historyButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(historyButton);

        historyButton.addActionListener((e) -> {
            setVisible(false); //���ô��ڲ��ɼ�
            new WeatherListFrame(); //���б���
        });

        return topJPanel;
    }

    public void contentJLable(String city){ //����Ϊ�����˵�ѡ�еĵ���
        Weather weather = dao.findByCity(areaIdMap.get(city)); //���ݵ�����ȡ��������
        dao.create(weather); //����ѯ�����ݴ������ݿ�

        contentJPanel.removeAll(); //�Ƴ��������
        contentJPanel.repaint();
        contentJPanel.setLayout(null); //�����ò��ֹ�����


        setJLabel(weather.getCity(),0,0,400,30,24); //����

        setJLabel(weather.getWeather()+"   "+weather.getTemperature()+"��", //�������¶�
                0,40,400,50,36);

        setJLabel(weather.getWinddirection()+"��   "+weather.getWindpower()+"��", //����ͷ���
                0,100,400,30,18);

        setJLabel("ʪ��   "+weather.getHumidity(),0,140,400,30,18); //ʪ��

        setJLabel("����ʱ��   "+weather.getReporttime(),0,220,400,30,14); //����ʱ��

        contentJPanel.revalidate(); //���¶��������С���Ҷ�����е�������в���
    }

    public void setJLabel(String label,int x,int y,int width,int height,int fontsize){ //��������ķ���
        JLabel jLabel = new JLabel(label); //�������
        jLabel.setBounds(x,y,width,height); //�������λ�úʹ�С
        jLabel.setHorizontalAlignment(SwingConstants.CENTER); //����������ݾ���
        jLabel.setFont(new Font("΢���ź�", Font.PLAIN, fontsize)); //����������ָ�ʽ
        contentJPanel.add(jLabel); //�������ӵ��������
    }
}
