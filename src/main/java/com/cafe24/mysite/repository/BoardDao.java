package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {

		int count = sqlSession.insert("board.insert",vo);
		
		return 1 == count;

	}

	public List<BoardVo> getList() {
		
		List<BoardVo> result = sqlSession.selectList("board.getList");
		return result;
	
	}
	
	public BoardVo getByBoardNo(long boardNo) {
		
		BoardVo vo = sqlSession.selectOne("board.getByBoardNo",boardNo);
		return vo;
	
	}

	public boolean modifyVo(BoardVo vo) {
		
		int count = sqlSession.update("board.update",vo);
		return 1 == count;
	
	}

	public boolean hitUpdate(Long boardNo) {
		
		int count = sqlSession.update("board.hitupdate",boardNo);
		return 1 == count;

	}

	public boolean updateOrderNo(Map<String, Object> map) {
		int count = sqlSession.update("board.updateOrderNo",map);
		return 1 == count;
	}

	public boolean delete(long boardNo) {
		int count = sqlSession.delete("board.delete",boardNo);
		return 1 == count;	
	}
	
	
}
