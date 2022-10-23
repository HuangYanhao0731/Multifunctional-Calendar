package myCalendar.weather.ui;
import myCalendar.weather.dao.WeatherDao;
import myCalendar.weather.dao.WeatherDaoImpl;
import myCalendar.weather.domain.Weather;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class WeatherListFrame extends MyFrame{
    private WeatherDao dao = new WeatherDaoImpl(); //天气接口
    private JPanel topJPanel = new JPanel(); //头部面板
    private JTable table = new JTable(); //表格
    private JScrollPane scrollPane = new JScrollPane();
    private JLabel message = new JLabel("啥也没有！");
    private String[] areaList = {"","南京市","杭州市","北京市","天津市","河北省","山西省","内蒙古","辽宁省","吉林省",
            "黑龙江省","上海市","宁波市","安徽省","福建省","江西省"};

    public WeatherListFrame() {
        super("天气查询历史记录", 700, 500);

        getContentPane().add(topJLabel(), BorderLayout.NORTH); //将头部面板放在窗口顶部
        // 把表头添加到容器顶部（使用普通的中间容器添加表格时，表头 和 内容 需要分开添加）

        scrollPane.setViewportView(table);
        getContentPane().add(scrollPane,BorderLayout.CENTER);

        setVisible(true); //设置窗口可见
    }

    public JPanel topJLabel(){
        FlowLayout flowLayout = (FlowLayout) topJPanel.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);

        JLabel label = new JLabel("请选择地区：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(label);

        JComboBox choice = new JComboBox(areaList); //下拉菜单组件
        choice.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(choice);

        contentJLable((String) choice.getSelectedItem()); //调用中间组件方法

        choice.addActionListener(e -> { //选中下拉菜单中的内容触发

            JComboBox cb = (JComboBox) e.getSource(); //获取地区
            contentJLable((String) cb.getSelectedItem()); //调用中间组件方法

        });

        JButton backButton = new JButton("返回首页");
        backButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(backButton);

        backButton.addActionListener((e) -> {
            setVisible(false); //设置窗口不可见
            new WeatherFrame(); //打开列表窗口
        });

        return topJPanel;
    }

    public void contentJLable(String city){ //传参为下拉菜单选中的地区
        List<Weather> weatherList = dao.findAll(city); //根据地区获取行政代码

        if (weatherList.size()>0){
            getContentPane().remove(message);
            String[] columnNames = {"ID","地区","天气","温度","风力和风速","湿度","发布时间"}; //列标题
            Object[][] rowData = new Object[weatherList.size()][columnNames.length];
            for(int i = 0;i < weatherList.size();i++){
                rowData[i][0] = weatherList.get(i).getWeatherid().toString();
                rowData[i][1] = weatherList.get(i).getCity();
                rowData[i][2] = weatherList.get(i).getWeather();
                rowData[i][3] = weatherList.get(i).getTemperature()+"℃";
                rowData[i][4] = weatherList.get(i).getWinddirection()+"风 "+weatherList.get(i).getWindpower()+"级";
                rowData[i][5] = weatherList.get(i).getHumidity();
                rowData[i][6] = weatherList.get(i).getReporttime();
            }

            DefaultTableModel tableModel=new DefaultTableModel(rowData,columnNames){//创建一个表格，指定所有行数据和表头
                public boolean isCellEditable(int row,int column){ //设置表格内容不可编辑
                    return false;
                }
            };
            table.setModel(tableModel);

            ListSelectionModel rowSM = table.getSelectionModel(); //返回当前行的状态模型
            rowSM.addListSelectionListener(e -> {
                ListSelectionModel lsm = (ListSelectionModel) e.getSource();
                if (lsm.isSelectionEmpty()){
                    System.out.println("没有选中行");
                }else{
                    int isremove = JOptionPane.showConfirmDialog(null, "请确认是否删除", "", JOptionPane.YES_NO_OPTION);
                    if (isremove == 0){ //确认删除
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
            message.setBounds(0,0,600,200); //设置组件位置和大小
            message.setHorizontalAlignment(SwingConstants.CENTER); //设置组件内容居中
            message.setFont(new Font("微软雅黑", Font.PLAIN, 36)); //设置组件文字格式
            getContentPane().add(message,BorderLayout.CENTER);
        }
        getContentPane().repaint();
        getContentPane().revalidate(); //重新对面板计算大小并且对面板中的组件进行布局
    }
}

