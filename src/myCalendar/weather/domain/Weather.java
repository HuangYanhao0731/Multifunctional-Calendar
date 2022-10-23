package myCalendar.weather.domain;

public class Weather {
    private Integer weatherid;
    private String city; //城市名
    private String weather; //天气现象（汉字描述）
    private String temperature; //实时气温，单位：摄氏度
    private String winddirection; //风向
    private String windpower; //风力级别，单位：级
    private String humidity; //空气湿度
    private String reporttime; //数据发布的时间

    public Integer getWeatherid() {
        return weatherid;
    }

    public void setWeatherid(Integer weatherid) {
        this.weatherid = weatherid;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getWinddirection() {
        return winddirection;
    }

    public void setWinddirection(String winddirection) {
        this.winddirection = winddirection;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getReporttime() {
        return reporttime;
    }

    public void setReporttime(String reporttime) {
        this.reporttime = reporttime;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "weatherid=" + weatherid +
                ", city='" + city + '\'' +
                ", weather='" + weather + '\'' +
                ", temperature='" + temperature + '\'' +
                ", winddirection='" + winddirection + '\'' +
                ", windpower='" + windpower + '\'' +
                ", humidity='" + humidity + '\'' +
                ", reporttime='" + reporttime + '\'' +
                '}';
    }
}
