package kr.co.tjoeun.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class JoinController {

	@GetMapping("/join")
	public String index() {
		return "join";
	}
}
