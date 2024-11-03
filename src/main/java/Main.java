import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        final String apiKey = "27c0eceb-a88e-4515-aaa0-f8ea556a3442";
//        JSONObject weather = new YandexWeatherAPI(apiKey).getWeather(52.37125, 4.89388, 2);
        JSONObject weather = new YandexWeatherAPI(apiKey).getWeather(52.37125, 4.89388, 8);
//        System.out.println("Температура: " + weather.getJSONObject("fact").getDouble("temp"));
//        System.out.println("Температура: " + weather.getJSONArray("forecasts").getJSONObject(0));
        System.out.println("\nОтвет API:\n" + weather.toString(2));

        System.out.println(calculateAverageTemperature(weather.getJSONArray("forecasts")));
    }

    static private double calculateAverageTemperature(JSONArray forecasts) {
        int sum = 0;

        for (Object forecast : forecasts) {
            int averageDayTemperature = ((JSONObject) forecast)
                    .getJSONObject("parts")
                    .getJSONObject("day")
                    .getInt("temp_avg");

            sum += averageDayTemperature;
        }
        return (double) sum / forecasts.length();
    }
}
