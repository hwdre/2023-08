package com.Luke.Board;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.Luke.util.Util;
import com.Luke.Index.*;
import com.Luke.Login.*;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
public class BoardController {
	// user -> Controller -> Service -> DAO -> mybatis ->DB

	// autowired말고 Resource로 연결
	@Resource(name = "boardService")
	private BoardService boardService;

	@Autowired
	private Util util; // 숫자변환을 사용하기위한 객체를 연결했습니다.
	
	//localhost/board?pageNo=10
	@GetMapping("/board")
	public String board(@RequestParam(value = "pageNo", required = false, 
	defaultValue = "1") int pageNo, Model model) {
		//근데 얘를 왜 필수값 아니라고 적어주지? 필수값인데? 강사말... 
		//required = false 이거말하는 거임
		//만약 아무것도 안들어오면 0으로 해줍니다. 그게 defaultValue = "0"이거 입니다.
		// 서비스에서 값 가져오기
		//페이지네이션인포 만들고, 값 넣고, 데이터베이스로 
		//보내서 필요한 값을 가져오고 그 값들을 jsp로 보냅니다.
		//PaginationInfo에 필수 정보를 넣어 줍니다.
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(pageNo); //현재 페이지 번호입니다.
		// 이건 고정되면 안되요~~ 파라미터 페이지엔오 쓸거에요.
		paginationInfo.setRecordCountPerPage(10); //한 페이지에 개시되는 게시물의 수입니다.
		paginationInfo.setPageSize(10); //페이징 리스트의 사이즈입니다. 
		//하드 코딩: 값 변경 안되게 변수안쓰는 것입니다.
		
		//전체 게시물의 수를 가져오는 명령문을 여기아래에 작성했습니다.
		int totalCount = boardService.totalCount();
		paginationInfo.setTotalRecordCount(totalCount); //전체 게시물의 수 입니다.
		//얘는 계속 변하는 것이니까 그때 그때 데이터베이스에 물어봐야 합니다.
		
		int firstRecordIndex = paginationInfo.getFirstRecordIndex(); 
		//시작위치입니다.
		int recordCountPerPage = paginationInfo.getRecordCountPerPage(); 
		//페이지당 게시글을 몇 개 보여줄지 가져옵니다.
		//이제 위에 두 개 dto로 만들어서 보낼겁니다. ㅜㅜ
		//pagedto만들꺼야....
		
		//System.out.println(firstRecordIndex);
		//System.out.println(recordCountPerPage);
		//System.out.println(pageNo);
		//System.out.println(totalCount);
		
		PageDTO page = new PageDTO();
		page.setFirstRecordIndex(firstRecordIndex);
		page.setRecordCountPerPage(recordCountPerPage);
		
		//보드서비스 수정합니다.
		List<BoardDTO> list = boardService.boardList(page);
		
		model.addAttribute("list", list);
		//페이징과 관련된 정보가 있는 PaginationInfo 객체를 모델에 반드시 넣어야 합니다.
		model.addAttribute("paginationInfo", paginationInfo);

		return "board";

	}

	// http://localhost:8080/pro1/detail?bno=426
	// 파라미터로 들어오는 값 잡기
	@GetMapping("/detail")
	public String detail(HttpServletRequest request, Model model) {

		// String bno = request.getParameter("bno");
		// bno에 요청하는 값이 있습니다. 이 값을 db까지 보내겠습니다.
		// System.out.println("bno: " + bno);
		int bno = util.sti(request.getParameter("bno"));

		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);

		BoardDTO resultdto = boardService.detail(dto);
		//System.out.println(resultdto.getCommentcount());
		if(resultdto.getCommentcount()>0) {
			//데이터 베이스에 물어봐서 jsp로 보냅니다.
			List<Map<String, Object>> commentList = boardService.commentList(bno);
			model.addAttribute("commentList", commentList);
		}
		model.addAttribute("dto", resultdto);
		return "detail";
	}

	@GetMapping("/write")
	public String write(HttpServletRequest req) {
		HttpSession session = req.getSession();
		if (session.getAttribute("mname") != null) {
			return "write";
		} else {
			return "redirect:/login";
		}
	}

	@PostMapping("/write")
	public String write2(HttpServletRequest request) {
		// 사용자가 입력한 데이터 변수에 담기
		// System.out.println(request.getParameter("title"));
		// System.out.println(request.getParameter("content"));
		// System.out.println("==============================");
		// Service ->DAO -> mybatis ->DB로 보내서 저장하기

		// 상대 ip 가져오기 23-07-19
		// HttpServletRequest가 있어야 합니다.

		HttpSession session = request.getSession();
		if (session.getAttribute("mid") != null) {

			// 로그인 했습니다. = 아래 로직을 여기로 가져오세요.
			BoardDTO dto = new BoardDTO();
			dto.setBtitle(request.getParameter("title"));
			dto.setBcontent(request.getParameter("content"));
			dto.setM_id((String) session.getAttribute("mid")); // 임시값
			//dto.setM_name((String) session.getAttribute("mname")); 세션에서 가져옴
			dto.setUuid(UUID.randomUUID().toString());
			//System.out.println("======================");
			//System.out.println(dto.getUuid());
			//System.out.println(dto.getUuid().length());
			//System.out.println("======================");
			
			// service-> dao -> mybatis-> db
			boardService.write(dto);

			return "redirect:/board"; // 다시 컨트롤러 지나가기 GET방식으로 지나갑니다.

		} else {

			// 로그인 안했어요. = 로그인 하세요.
			return "redirect:/login";

		}

	}

	@GetMapping("/delete")
	public String delete(@RequestParam(value = "bno", required = false, defaultValue = "0") int bno, HttpSession session) {
		//로그인 여부 확인해주세요.
		//System.out.println("mid: " + session.getAttribute("mid"));
	
		BoardDTO dto = new BoardDTO();
		dto.setBno(bno);
		dto.setM_id((String) session.getAttribute("mid"));
		// dto.setBwrite(null); 지금 이 게시글을 삭제하려는 사람이 누구인지 담아서 보냅니다.
		// 나중에 로그인을 하면 사용자의 정보도 담아서 보냅니다.

		boardService.delete(dto);

		return "redirect:/board";

	}

	@GetMapping("/edit")
	public ModelAndView edit(HttpServletRequest request) {
		// 로그인하지 않으면 로그인 화면으로 던져주세요.
		HttpSession session = request.getSession();
		ModelAndView mv = new ModelAndView();
		if (session.getAttribute("mid") != null) {

			// if문으로 만들어주세요.

			// dto를 하나 만들어서 거기에 담겠습니다.
			BoardDTO dto = new BoardDTO();
			dto.setBno(util.sti(request.getParameter("bno")));
			// 내 글만 수정할 수 있도록 세션에 있는 mid도 보냅니다.
			dto.setM_id((String) session.getAttribute("mid"));

			// 데이터베이스에 bno를 보내서 dto를 얻어옵니다.
			BoardDTO resultdto = boardService.detail(dto);
			if(resultdto != null) {
				// mv에 실어보냅니다.
				mv.addObject("dto",resultdto);
				mv.setViewName("edit"); // 이동할 jsp명을 적어줍니다.
			} else {
				mv.setViewName("warning");
			}
		} else {
			mv.setViewName("redirect:/login");
			// 로그인 안했어요. = 로그인 하세요.

		}
		return mv;
	}

	@PostMapping("/edit")
	public String edit(BoardDTO dto) {

		// System.out.println(map);

		// System.out.println(dto.getBtitle());
		// System.out.println(dto.getBcontent());
		// System.out.println(dto.getBno());

		boardService.edit(dto);

		return "redirect:/detail?bno=" + dto.getBno(); // 보드로 이동하겠습니다.

	}
	
	//2023-08-07, 프레임워크 프로그래밍
	@GetMapping("/cdel")
	public String cdel(@RequestParam Map<String, Object> map, HttpSession session) {
		//로그인여부 검사
		if(session.getAttribute("mid") != null) {
		//값 들어왔는지 여부 검사
//			
			if(map.containsKey("bno") && map.get("cno") != null &&
			!(map.get("bno").equals("")) && !(map.get("cno").equals("")) &&
			util.isNum(map.get("bno")) && util.isNum(map.get("cno"))) {
//				System.out.println("여기로 들어왔습니다.");
			 	map.put("mid", session.getAttribute("mid"));
			 	int result = boardService.cdel(map);
			 	System.out.println("삭제 결과: " + result);
			} //cno가 없는데 map에서 겟걸어서 가져옵니다. 없는데 가져와서 error 뜹니다.
			//map에서 bno값을 가져왔는데 그 속이 비어있어""지 않아, cno도 비어있지 않아 그리고 
			//bno,cno에 적힌 값이 숫자로 변환될 수 있다면 if문 실행
		}
		return "redirect:/detail?bno=" + map.get("bno");
	}

}
