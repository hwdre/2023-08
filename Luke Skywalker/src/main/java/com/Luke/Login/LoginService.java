package com.Luke.Login;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Inject //주입하다
	@Named("loginDAO") //@Autowired써도 상관없습니다.
	private LoginDAO loginDAO;
	
	public LoginDTO login(LoginDTO dto) {
		
		
		
		return loginDAO.login(dto);
		
	}

	public int join(JoinDTO joindto) {
		
		return loginDAO.join(joindto);
	}

	public List<JoinDTO> members() {

		return loginDAO.members();
	}

	public int checkID(String id) {
		return loginDAO.checkID(id);
	}

	public List<Map<String, Object>> boardList2(int pageNo) {
		
		return loginDAO.boardList2(pageNo);
	}

	public int totalCount() {
		
		return loginDAO.totalCount();
	}

}
