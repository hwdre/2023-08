package com.Luke.Login;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("loginDAO")
public class LoginDAO {
	
	/*@Inject
	@Named("sqlSession")
	private SqlSession sqlSession;
	
public List<Map<String, Object>> loginList(){
		
		return sqlSession.selectList("login.loginList");
		
	}
	
	public void login(LoginDTO dto) {
		
		sqlSession.insert("login.login", dto);
		
	} 내가 작성한 코드*/
	
	@Autowired
	private SqlSession sqlSession;

	public LoginDTO login(LoginDTO dto) {
		
		return sqlSession.selectOne("login.login",dto);
		
	}

	public int join(JoinDTO joindto) {
		
		return sqlSession.insert("login.join",joindto);
	}

	public List<JoinDTO> members() {
		
		return sqlSession.selectList("login.members");
	}

	public int checkID(String id) {
		return sqlSession.selectOne("login.checkID", id);
	}

	public List<Map<String, Object>> boardList2(int pageNo) {
	
		return sqlSession.selectList("login.boardList2", pageNo);
	}

	public int totalCount() {
		
		return sqlSession.selectOne("login.totalCount");
	}
	
	
	
	
}
