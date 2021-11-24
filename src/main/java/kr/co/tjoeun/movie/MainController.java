package kr.co.tjoeun.movie;

import kr.co.tjoeun.movie.external.MovieClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MovieClient externalTMDBClient;

    @GetMapping(value = {"/", "/main"})
    public String index(Model model) throws IllegalAccessException {
        Map<String, List<MovieInfo>> post = externalTMDBClient.post();

        model.addAttribute("movie", post);
        return "main";
    }
}
