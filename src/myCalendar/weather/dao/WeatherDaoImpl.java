package myCalendar.weather.dao;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import myCalendar.weather.domain.Weather;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class WeatherDaoImpl implements WeatherDao{
    @Override
    public List<Weather> findAll(String city) {
        String sql = "";
        PreparedStatement preparedStatement = null;

        List<Weather> weathers = new ArrayList<>();
        try{
            Connection con = DBHelper.getConnection();
            if (city.equals("")){
                sql = "select * from weather order by weatherid desc limit 50";
                preparedStatement = con.prepareStatement(sql);
            }else{
                sql = "select * from weather where city = ? order by weatherid desc limit 50";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, city); //绑定参数
            }
            ResultSet resultSet = preparedStatement.executeQuery(); //执行查询
            while (resultSet.next()){ //遍历结果集
                Weather weather = new Weather();
                weather.setWeatherid(resultSet.getInt("weatherid"));
                weather.setCity(resultSet.getString("city"));
                weather.setWeather(resultSet.getString("weather"));
                weather.setTemperature(resultSet.getString("temperature"));
                weather.setWinddirection(resultSet.getString("winddirection"));
                weather.setWindpower(resultSet.getString("windpower"));
                weather.setHumidity(resultSet.getString("humidity"));
                weather.setReporttime(resultSet.getString("reporttime"));

                weathers.add(weather);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return weathers;
    }

    @Override
    public Weather findByCity(String city) {
        //city为城市代码，key为需要自己申请
        String url = "https://restapi.amap.com/v3/weather/weatherInfo?city="+city+"&key=4493ae16b80ac13e6f9afe812137b731"; //这里改为自己的key
        Weather weather = new Weather();

        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder(URI.create(url)).build();
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String strResult = response.body();

            JSONObject object = JSONObject.parseObject(strResult);
            if(object.getString("count").equals("1")){
                JSONArray lives = object.getJSONArray("lives");
                JSONObject object1 = JSONObject.parseObject(lives.getString(0));

                weather.setCity(object1.getString("city"));
                weather.setWeather(object1.getString("weather"));
                weather.setTemperature(object1.getString("temperature"));
                weather.setWinddirection(object1.getString("winddirection"));
                weather.setWindpower(object1.getString("windpower"));
                weather.setHumidity(object1.getString("humidity"));
                weather.setReporttime(object1.getString("reporttime"));
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return weather;
    }

    @Override
    public int create(Weather weather) {
        System.out.println(weather);
        String sql = "insert into weather(city,weather,temperature," +
                "winddirection,windpower,humidity,reporttime) values(?,?,?,?,?,?,?)";
        try {
            Connection conn = DBHelper.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setString(1,weather.getCity());
            pre.setString(2,weather.getWeather());
            pre.setString(3,weather.getTemperature());
            pre.setString(4,weather.getWinddirection());
            pre.setString(5,weather.getWindpower());
            pre.setString(6,weather.getHumidity());
            pre.setString(7,weather.getReporttime());

            int affectedRows = pre.executeUpdate(); //执行修改
            System.out.println("成功插入"+affectedRows+"条数据");
        }catch (SQLException e){
            return -1;
        }
        return 0;
    }

    @Override
    public int removeByID(int id) {
        String sql = "delete from weather where weatherid = ?";
        int result;
        try{
            Connection conn = DBHelper.getConnection();
            PreparedStatement pre = conn.prepareStatement(sql);
            pre.setInt(1, id); //绑定参数
            result = pre.executeUpdate(); //执行修改
        }catch (SQLException e){
            return -1;
        }
        return result;
    }
}
