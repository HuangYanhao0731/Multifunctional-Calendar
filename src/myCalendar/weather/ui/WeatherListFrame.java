package myCalendar.weather.ui;
import myCalendar.weather.dao.WeatherDao;
import myCalendar.weather.dao.WeatherDaoImpl;
import myCalendar.weather.domain.Weather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class WeatherListFrame extends MyFrame{
    private WeatherDao dao = new WeatherDaoImpl(); //�����ӿ�
    private JPanel topJPanel = new JPanel(); //ͷ�����
    private JTable table = new JTable(); //���
    private JScrollPane scrollPane = new JScrollPane();
    private JLabel message = new JLabel("ɶҲû�У�");
    private String[] areaList = {"","�Ͼ���","������","������","�����","�ӱ�ʡ","ɽ��ʡ","���ɹ�","����ʡ","����ʡ",
            "������ʡ","�Ϻ���","������","����ʡ","����ʡ","����ʡ"};

    public WeatherListFrame() {
        super("������ѯ��ʷ��¼", 700, 500);

        getContentPane().add(topJLabel(), BorderLayout.NORTH); //��ͷ�������ڴ��ڶ���
        // �ѱ�ͷ��ӵ�����������ʹ����ͨ���м�������ӱ��ʱ����ͷ �� ���� ��Ҫ�ֿ���ӣ�

        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        setVisible(true); //���ô��ڿɼ�
    }

    public JPanel topJLabel(){
        FlowLayout flowLayout = (FlowLayout) topJPanel.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);

        JLabel label = new JLabel("��ѡ�������");
        label.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(label);

        JComboBox choice = new JComboBox(areaList); //�����˵����
        choice.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(choice);

        contentJLable((String) choice.getSelectedItem()); //�����м��������

        choice.addActionListener(e -> { //ѡ�������˵��е����ݴ���

            JComboBox cb = (JComboBox) e.getSource(); //��ȡ����
            contentJLable((String) cb.getSelectedItem()); //�����м��������

        });

        JButton backButton = new JButton("������ҳ");
        backButton.setFont(new Font("΢���ź�", Font.PLAIN, 14));
        topJPanel.add(backButton);

        backButton.addActionListener((e) -> {
            setVisible(false); //���ô��ڲ��ɼ�
            new WeatherFrame(); //���б���
        });

        return topJPanel;
    }

    public void contentJLable(String city){ //����Ϊ�����˵�ѡ�еĵ���
        List<Weather> weatherList = dao.findAll(city); //���ݵ�����ȡ��������

        if (weatherList.size()>0){
            getContentPane().remove(message);
            String[] columnNames = {"ID","����","����","�¶�","�����ͷ���","ʪ��","����ʱ��"}; //�б���
            Object[][] rowData = new Object[weatherList.size()][columnNames.length];
            for(int i = 0;i < weatherList.size();i++){
                rowData[i][0] = weatherList.get(i).getWeatherid().toString();
                rowData[i][1] = weatherList.get(i).getCity();
                rowData[i][2] = weatherList.get(i).getWeather();
                rowData[i][3] = weatherList.get(i).getTemperature()+"��";
                rowData[i][4] = weatherList.get(i).getWinddirection()+"�� "+weatherList.get(i).getWindpower()+"��";
                rowData[i][5] = weatherList.get(i).getHumidity();
                rowData[i][6] = weatherList.get(i).getReporttime();
            }

            DefaultTableModel tableModel=new DefaultTableModel(rowData,columnNames){//����һ�����ָ�����������ݺͱ�ͷ
                public boolean isCellEditable(int row,int column){ //���ñ�����ݲ��ɱ༭
                    return false;
                }
            };
            table.setModel(tableModel);

            ListSelectionModel rowSM = table.getSelectionModel(); //���ص�ǰ�е�״̬ģ��
            rowSM.addListSelectionListener(e -> {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()){
                    System.out.println("û��ѡ����");
                }else{
                    int isremove = JOptionPane.showConfirmDialog(null, "��ȷ���Ƿ�ɾ��", "", JOptionPane.YES_NO_OPTION);
                    if (isremove == 0){ //ȷ��ɾ��
                        int selectedRow = lsm.getMinSelectionIndex();
                        dao.removeByID(Integer.parseInt(table.getValueAt(selectedRow,0).toString()));
                        tableModel.removeRow(selectedRow);
                    }
                }
            });

            scrollPane.setViewportView(table);
            getContentPane().add(scrollPane,BorderLayout.CENTER);
        }else{
            getContentPane().remove(scrollPane);
            message.setBounds(0,0,600,200); //�������λ�úʹ�С
            message.setHorizontalAlignment(SwingConstants.CENTER); //����������ݾ���
            message.setFont(new Font("΢���ź�", Font.PLAIN, 36)); //����������ָ�ʽ
            getContentPane().add(message,BorderLayout.CENTER);
        }
        getContentPane().repaint();
        getContentPane().revalidate(); //���¶��������С���Ҷ�����е�������в���
    }
}

