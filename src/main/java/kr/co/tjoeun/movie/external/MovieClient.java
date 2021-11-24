package kr.co.tjoeun.movie.external;

import kr.co.tjoeun.movie.MovieInfo;
import kr.co.tjoeun.movie.dto.MovieRequestDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static kr.co.tjoeun.movie.common.MovieRestTemplate.*;

// https://api.themoviedb.org/3/movie/popular?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b // movie
// https://api.themoviedb.org/3/movie/278/credits?language=ko-KR&api_key=f3d4fbc2369310d1ca2f901781ccb40b // 출연진
// https://api.themoviedb.org/3/discover/movie?api_key=f3d4fbc2369310d1ca2f901781ccb40b&with_genres=27 //공포 (horror)

@Service
public class MovieClient {

    public Map<String, List<MovieInfo>> post() throws IllegalAccessException {

        try {
            List<MovieRequestDto> urls = new ArrayList<>();
            urls.add(new MovieRequestDto("popular", "3/movie/popular"));
            urls.add(new MovieRequestDto("action", "3/discover/movie", "with_genres=28"));
            urls.add(new MovieRequestDto("Comedy", "3/discover/movie", "with_genres=35"));
            urls.add(new MovieRequestDto("Romance", "3/discover/movie", "with_genres=10749"));

            Map<String, List<MovieInfo>> resultMap = new HashMap<>();
            for (MovieRequestDto movieRequestDto : urls) {

                String url;
                if (movieRequestDto.getWithGenre() == null) {
                    url = makeUrl(movieRequestDto.getCategory());
                } else {
                    url = makeUrl(movieRequestDto.getCategory(), movieRequestDto.getWithGenre());
                }

                ResponseEntity<String> stringResponseEntity = apiCall(url);
                JSONObject jsonObject = toJson(stringResponseEntity);

                JSONArray results = (JSONArray) jsonObject.get("results");
                List<MovieInfo> movieInfoList = new ArrayList<>();

                for (Object result : results) {
                    JSONObject resultObject = (JSONObject) result;
                    String title = resultObject.get("title").toString();
                    String posterImg = resultObject.get("poster_path").toString();
                    movieInfoList.add(new MovieInfo(title, posterImg));
                }
                resultMap.put(movieRequestDto.getKey(), movieInfoList);
            }

            return resultMap;
        } catch (IllegalAccessException | ParseException e) {
            e.printStackTrace();
        }

        throw new IllegalAccessException();
    }
}
