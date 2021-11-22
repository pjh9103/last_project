package kr.co.tjoeun.member.controller;

import kr.co.tjoeun.member.controller.dto.JoinDto;
import kr.co.tjoeun.member.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @GetMapping("/join")
    public String index() {
        return "join";
    }

    @PostMapping("/join")
    public String index(@ModelAttribute JoinDto joinDto) {
        joinService.join(joinDto);
        return "redirect:/main";
    }
}
