import org.json.JSONArray;
import org.json.JSONObject;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        final String apiKey = "";

        if (apiKey.isEmpty()) {
            System.out.println("Перед запуском программы нужно задать api-ключ!");
            return;
        }

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Введите широту: ");
            double latitude = scanner.nextDouble();

            System.out.print("Введите долготу: ");
            double longitude = scanner.nextDouble();

            System.out.print("Введите лимит прогноза: ");
            int limit = scanner.nextInt();
            if (limit < 1) {
                limit = 1;
            }

            JSONObject weather = new YandexWeatherAPI(apiKey).getWeather(latitude, longitude, limit);
            if (weather == null) {
                System.out.println("\nНе удалось получить данные :с. Возможно, что-то не так с координатами");
                return;
            }

            System.out.println("\nТемпература: " + weather.getJSONObject("fact").getDouble("temp"));
            System.out.println("Средний прогноз температуры: " + calculateAverageTemperature(weather.getJSONArray("forecasts")));
            System.out.println("\nОтвет API:\n\n" + weather.toString(2));

        } catch (InputMismatchException e) {
            System.out.println("Вводите только числа!");
        }

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
