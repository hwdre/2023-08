package com.Luke.Index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping(value = { "/", "/index" }) // 첫 화면 로딩: index.jsp 호출
	public String index() {

		return "index"; // 데이터 붙임 없이 index.jsp페이지만 보여줍니다.

	}

	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}

	@GetMapping("/board2")
	public String board2() {
		return "board2";
	}

	@GetMapping("/monni")
	public String mooni() {
		return "mooni";
	}

	@GetMapping("/notice")
	public String notice() {
		return "notice";
	}

}
