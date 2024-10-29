import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexWeatherAPI {
    private static final String BASE_URL = "https://api.weather.yandex.ru/v2/forecast";
    private final String apiKey;
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public YandexWeatherAPI(String apiKey) {
        this.apiKey = apiKey;
    }

    public JSONObject getWeather(double latitude, double longitude) {
        URI uri = URI.create(BASE_URL + "?lat=" + latitude + "&lon=" + longitude);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("X-Yandex-Weather-Key", apiKey)
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return new JSONObject(response.body());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
