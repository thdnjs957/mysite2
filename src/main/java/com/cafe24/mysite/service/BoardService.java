package com.cafe24.mysite.service;

import java.util.List;

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

	public boolean delete(BoardVo boardVo) {
		
		return false;
		//return boardDao.delete(boardVo);
	}
	
}
