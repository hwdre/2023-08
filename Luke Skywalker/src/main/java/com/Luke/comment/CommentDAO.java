package com.Luke.comment;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository("commentDAO")
public class CommentDAO {
	
	@Autowired
	private SqlSession sqlSession;

	public int commentInsert(Map<String, Object> map) {
		return sqlSession.insert("comment.commentInsert", map);
	}
	
}
