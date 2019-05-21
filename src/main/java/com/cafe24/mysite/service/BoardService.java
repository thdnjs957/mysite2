package com.cafe24.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	public List<BoardVo> getList() {

		return boardDao.getList();

	}

	public boolean insert(BoardVo boardVo) {
		
		return boardDao.insert(boardVo);
	}

	public boolean delete(Long boardNo) {
		return boardDao.delete(boardNo);
	}

	public BoardVo getEachBoardVo(Long boardNo) {
		return boardDao.getByBoardNo(boardNo);
	}

	public boolean update(BoardVo boardVo) {
		return boardDao.modifyVo(boardVo);
	}

	public boolean hitIncrease(Long boardNo) {
		return boardDao.hitUpdate(boardNo);
	}

	public boolean replyInsert(Map<String, Object> map) {
		return boardDao.updateOrderNo(map);
	}
	
}
