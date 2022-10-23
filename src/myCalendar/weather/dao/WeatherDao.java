package myCalendar.weather.dao;
import myCalendar.weather.domain.Weather;
import java.util.List;
public interface WeatherDao {
    // 根据城市名查询所有
    List<Weather> findAll(String city);

    // 根据城市查询一条信息(api)
    Weather findByCity(String city);

    // 创建信息
    int create(Weather weather);

    // 删除信息
    int removeByID(int id);
}
