package myCalendar.weather.ui;

import myCalendar.weather.dao.WeatherDao;
import myCalendar.weather.dao.WeatherDaoImpl;
import myCalendar.weather.domain.Weather;
import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
public class WeatherFrame extends MyFrame{
    private WeatherDao dao = new WeatherDaoImpl(); //天气接口
    private JPanel topJPanel = new JPanel(); //头部面板
    private JPanel contentJPanel = new JPanel(); //内容面板
    private Map<String,String> areaIdMap = new LinkedHashMap<>() //城市代码，LinkedHashMap可使内容按顺序输出
    {{
        put("南京市","320100");
        put("杭州市","330100");
        put("北京市","110000");
        put("天津市","120000");
        put("河北省","130000");
        put("山西省","140000");
        put("内蒙古","150000");
        put("辽宁省","210000");
        put("吉林省","220000");
        put("黑龙江省","230000");
        put("上海市","310000");
        put("宁波市","330200");
        put("安徽省","340000");
        put("福建省","350000");
        put("江西省","360000");
    }};

    public WeatherFrame(){
        super("天气小工具",400,420);

        getContentPane().add(topJLabel(),BorderLayout.NORTH); //将头部面板放在窗口顶部
        getContentPane().add(contentJPanel,BorderLayout.CENTER); //将内容面板放在窗口中间

        setVisible(true); //设置窗口可见
    }

    //头部组件方法
    public JPanel topJLabel(){
        FlowLayout flowLayout = (FlowLayout) topJPanel.getLayout();
        flowLayout.setVgap(20);
        flowLayout.setHgap(20);

        JLabel label = new JLabel("请选择地区：");
        label.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(label);

        String[] categorys = areaIdMap.keySet().toArray(new String[0]); //获取地区列表
        JComboBox choice = new JComboBox(categorys); //下拉菜单组件
        choice.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(choice);

        contentJLable((String) choice.getSelectedItem()); //调用中间组件方法

        choice.addActionListener(e -> { //选中下拉菜单中的内容触发
            JComboBox cb = (JComboBox) e.getSource(); //获取地区
            contentJLable((String) cb.getSelectedItem()); //调用中间组件方法
        });

        JButton historyButton = new JButton("查看历史记录");
        historyButton.setFont(new Font("微软雅黑", Font.PLAIN, 14));
        topJPanel.add(historyButton);

        historyButton.addActionListener((e) -> {
            setVisible(false); //设置窗口不可见
            new WeatherListFrame(); //打开列表窗口
        });

        return topJPanel;
    }

    public void contentJLable(String city){ //传参为下拉菜单选中的地区
        Weather weather = dao.findByCity(areaIdMap.get(city)); //根据地区获取行政代码
        dao.create(weather); //将查询的数据存入数据库

        contentJPanel.removeAll(); //移除所有组件
        contentJPanel.repaint();
        contentJPanel.setLayout(null); //不设置布局管理器


        setJLabel(weather.getCity(),0,0,400,30,24); //地区

        setJLabel(weather.getWeather()+"   "+weather.getTemperature()+"℃", //天气和温度
                0,40,400,50,36);

        setJLabel(weather.getWinddirection()+"风   "+weather.getWindpower()+"级", //风向和风力
                0,100,400,30,18);

        setJLabel("湿度   "+weather.getHumidity(),0,140,400,30,18); //湿度

        setJLabel("更新时间   "+weather.getReporttime(),0,220,400,30,14); //更新时间

        contentJPanel.revalidate(); //重新对面板计算大小并且对面板中的组件进行布局
    }

    public void setJLabel(String label,int x,int y,int width,int height,int fontsize){ //创建组件的方法
        JLabel jLabel = new JLabel(label); //创建组件
        jLabel.setBounds(x,y,width,height); //设置组件位置和大小
        jLabel.setHorizontalAlignment(SwingConstants.CENTER); //设置组件内容居中
        jLabel.setFont(new Font("微软雅黑", Font.PLAIN, fontsize)); //设置组件文字格式
        contentJPanel.add(jLabel); //将组件添加到内容面板
    }
}
