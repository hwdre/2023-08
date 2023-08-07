package com.Luke.Board;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Luke.util.Util;

//이게 없으면 그냥 BoardService 타입으로 저장되지만 뒤에 이걸 넣으면 
//boardService라는 이름이 생깁니다.
@Service("boardService")
public class BoardService {

	@Inject // 주입하다
	@Named("boardDAO")
	private BoardDAO boardDAO;
	
	@Autowired
	private Util util;


	// 보드리스트 불러오는 메소드
	public List<BoardDTO> boardList(PageDTO page) {
		
		return boardDAO.boardList(page);

	}

	public BoardDTO detail(BoardDTO resultdto) {
		//좋아요 수 +1하기 기능을 넣어주겠습니다.
		boardDAO.ilyou(resultdto);
		BoardDTO dto = boardDAO.detail(resultdto);
		//System.out.println(dto);
		
		if (dto != null) { // 내 글이 아닐때 null이 들어옵니다. 즉, null이 아닐때라고 검사해주세요.

			if (dto.getBip() != null && dto.getBip().indexOf(".") != -1) {
				String ip = dto.getBip();
				String[] ipArr = ip.split("[.]");
				ipArr[1] = "♡";
				dto.setBip(String.join(".", ipArr));
			}
		}
		return dto;

	}

	public void write(BoardDTO dto) {
		dto.setBtitle(util.exchange(dto.getBtitle()));
		dto.setBip(util.getIp());// 얻어온 ip도 저장해서 데이터베이스로 보내겠습니다. 항항
		boardDAO.write(dto); // 실행만 시키고 결과를 받지 않습니다.
		// select를 제외한 나머지는 영향받은 행의 수(int)를 받아오기도 합니다.

	}

	public void delete(BoardDTO dto) {

		boardDAO.delete(dto);

	}

	public void edit(BoardDTO dto) {
		boardDAO.edit(dto);
	}
	
	//전체 게시물의 수를 가져오기 2023-07-26 sql 응용
	public int totalCount() {
		return boardDAO.totalCount();
	}

	public List<Map<String, Object>> commentList(int bno) {
		return boardDAO.commentList(bno);
	}

	public int cdel(Map<String, Object> map) {
		
		return boardDAO.cdel(map);
	}

}
