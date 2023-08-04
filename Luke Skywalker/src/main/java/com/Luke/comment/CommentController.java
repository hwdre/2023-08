package com.Luke.comment;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@PostMapping("/comment")
	public String comment(@RequestParam Map<String, Object> map, HttpSession session) {
		// System.out.println(map);
		// 로그인 한 여부 확인합니다.
		if(session.getAttribute("mid") != null) {
			// 로그인한 사람의 m_id값을 뽑아서 map에 저장합니다.
			map.put("mid", session.getAttribute("mid"));
			int result = commentService.commentInsert(map);
			if(result == 1) {
				return "redirect:/detail?bno=" + map.get("bno");
			} else {
				return "warning";
			}
		} else {
			return "warning";
		}
		// System.out.println(session.getAttribute("mid"));

		
		// 뭐가 나왔나 보려고 이거 만든겁니다.
		
	}

}
