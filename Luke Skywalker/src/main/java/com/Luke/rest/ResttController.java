package com.Luke.rest;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Luke.Board.BoardService;
import com.Luke.Login.LoginService;

@RestController
public class ResttController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private BoardService boardService;
	
	//아이디 중복검사 2023-08-02
		@PostMapping("/checkID") //join.jsp에서 post 방식으로 보낸 데이터 여기서 받습니다.
		public String checkID(@RequestParam("id") String id) {
			//System.out.println("id: " + id);
			//return "1"; join.jsp 28번줄의 data에 1이 들어가서 화면에 1이 출력됩니다.
			int result = loginService.checkID(id);
			//json
			JSONObject json = new JSONObject();
			json.put("result", result);
			//System.out.println(json.toString());
			return json.toString(); //{"result": 1}
		}
		
		//boardList2
		@GetMapping(value ="/boardList2", produces = "application/json; charset=UTF-8")
		public String boardList2(@RequestParam("pageNo") int pageNo) {
			System.out.println("jq가 보내주는: " + pageNo);
			List<Map<String,Object>> list = loginService.boardList2((pageNo - 1) * 10); 
			//리스트 생성합니다.
			//System.out.println(list);
			JSONObject json = new JSONObject(); //json 타입 공간을 생성합니다.
			JSONArray arr = new JSONArray(list); 
			//리스트를 배열로 만들어서 json안에 넣어줍니다.
			json.put("totalCount", loginService.totalCount());
			json.put("pageNo", pageNo);
			json.put("list", arr); //그리고 그 배열을 다시 json으로 감싸줍니다.
			//System.out.println(json.toString());
			return json.toString(); //그리고 그걸 리턴합니다.
			//리스트 타입은 배열 형태니까 jsonarray를 감싸서 그걸 다시 json이 포장해서 
			//tostirng한 다음에 리턴해요.
		}
		
		/* json.put("totalCount", 128);
		 * json.put("pageNo", 1);
		 * json.put("list", arr);
		 *  boardList2 = {totalCount: 128, pageNo: 1, list: [
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...},
		 * {bno:1, btitle:...}
		 * ]}
		 * 커다란 오브젝트 안에 값이 3개 있는데 지금은 그 중에 리스트 뽑는 중입니다.
		 * 객체: {키: 값, 이름: 값,....} 계속 반복되는데 이 값 부분에 object가 들어가요
		 * 커다란 오브젝트 속에 오브젝트가 있어요. 
		 */
	
}
