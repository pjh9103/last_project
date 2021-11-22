package kr.co.tjoeun.movie.service;



import org.assertj.core.api.Assertions;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static kr.co.tjoeun.movie.service.ExternalTMDBClient.BASE_URL2;

class ExternalTMDBClientTest {

    @Test
    void name() {

        ExternalTMDBClient externalTMDBClient = new ExternalTMDBClient();
        String s = externalTMDBClient.makeUrl("movie", "popular");
        System.out.println("s = " + s);
    }

    @Test
    void getMovie() throws ParseException {
        ExternalTMDBClient externalTMDBClient = new ExternalTMDBClient();
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(BASE_URL2, HttpMethod.GET, null, String.class);

        HttpStatus statusCode = response.getStatusCode();
        Assertions.assertThat(statusCode).isEqualTo(HttpStatus.OK);


        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(response.getBody());
        JSONArray results = (JSONArray) jsonObject.get("results");
        for (int i = 0; i < results.size(); i++) {
            JSONObject resultObject = (JSONObject) results.get(i);
            String title = resultObject.get("title").toString();
            String overView = resultObject.get("overview").toString();
            String backdropPath = resultObject.get("backdrop_path").toString();
        }
    }
}