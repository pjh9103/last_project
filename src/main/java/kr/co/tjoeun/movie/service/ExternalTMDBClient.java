package kr.co.tjoeun.movie.service;

import kr.co.tjoeun.movie.MovieInfo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

// https://api.themoviedb.org/3/movie/popular?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b // movie
// https://api.themoviedb.org/3/movie/278/credits?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b // 출연진
//
@Service
public class ExternalTMDBClient {

    public static final String BASE_URL = "https://api.themoviedb.org/3/movie/top_rated?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b";

    public List<MovieInfo> post() throws IllegalAccessException {
        ExternalTMDBClient externalTMDBClient = new ExternalTMDBClient();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL, HttpMethod.GET, null, String.class);
        try {
            if (!(response.getStatusCode() == HttpStatus.OK)) {
                throw new IllegalAccessException("요청에 실패하셨습니다. 요청을 잠시 후 다시 해주시기 바랍니다.");
            }

            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(Objects.requireNonNull(response.getBody()));
            JSONArray results = (JSONArray) jsonObject.get("results");
            List<MovieInfo> movieInfoList = new ArrayList<>();

            for (Object result : results) {
                JSONObject resultObject = (JSONObject) result;
                String title = resultObject.get("title").toString();
                String posterImg = resultObject.get("poster_path").toString();
                movieInfoList.add(new MovieInfo(title, posterImg));
            }

            return movieInfoList;
        } catch (IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }
        throw new IllegalAccessException();
    }

    public String makeUrl(String channel, String category) {
        return format(BASE_URL, channel, category);
    }

}
