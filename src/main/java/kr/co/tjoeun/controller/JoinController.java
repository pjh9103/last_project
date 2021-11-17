package kr.co.tjoeun.controller;

import kr.co.tjoeun.service.JoinService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.tjoeun.controller.dto.JoinDto;
import kr.co.tjoeun.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class JoinController {
	
	private final UserDetailsService memberService;
	private final JoinService joinService;

	@GetMapping("/join")
	public String index() {
		return "join";
	}
	
	@PostMapping("/join")
	public String index(@ModelAttribute JoinDto joinDto) {
		joinService.join(joinDto);
		return "redirect:/";
	}
}
