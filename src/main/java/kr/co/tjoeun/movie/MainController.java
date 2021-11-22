package kr.co.tjoeun.movie;

import kr.co.tjoeun.movie.service.ExternalTMDBClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ExternalTMDBClient externalTMDBClient;

    @GetMapping(value = {"/", "/main"})
    public String index(Model model) throws IllegalAccessException {
        List<MovieInfo> post = externalTMDBClient.post();

        model.addAttribute("movie", post);
        return "main";
    }
}
