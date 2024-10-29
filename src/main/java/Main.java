import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        JSONObject weather = new YandexWeatherAPI("test").getWeather(52.37125, 4.89388);
        System.out.println(weather.toString(2));
    }
}
