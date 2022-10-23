package myCalendar.weather.dao;
import myCalendar.weather.domain.Weather;
import java.util.List;
public interface WeatherDao {
    // ���ݳ�������ѯ����
    List<Weather> findAll(String city);

    // ���ݳ��в�ѯһ����Ϣ(api)
    Weather findByCity(String city);

    // ������Ϣ
    int create(Weather weather);

    // ɾ����Ϣ
    int removeByID(int id);
}
