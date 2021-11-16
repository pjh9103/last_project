package kr.co.tjoeun.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

	@GetMapping("/main")
	public String index() {
		return "main";
	}
}
