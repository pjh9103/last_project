package kr.co.tjoeun.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JoinController {

	@GetMapping("/join")
	public String index() {
		return "join";
	}
}
