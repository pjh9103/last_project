package kr.co.tjoeun.movie.common;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

import static java.lang.String.format;

public class MovieRestTemplate {

    private static final String BASE_URL = "https://api.themoviedb.org/%s?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b&%s";

    public static ResponseEntity<String> apiCall(String baseUrl) throws IllegalAccessException {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(baseUrl, HttpMethod.GET, null, String.class);
        if (!(response.getStatusCode() == HttpStatus.OK)) {
            throw new IllegalAccessException("요청에 실패하셨습니다. 요청을 잠시 후 다시 해주시기 바랍니다.");
        }
        return response;
    }

    public static JSONObject toJson(ResponseEntity<String> stringResponseEntity) throws ParseException {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(stringResponseEntity.getBody()));
        return jsonObject;
    }

    public static String makeUrl(String category) {
        return format(BASE_URL, category, "");
    }

    public static String makeUrl(String category, String withGenre) {
        return format(BASE_URL, category, withGenre);
    }
}
