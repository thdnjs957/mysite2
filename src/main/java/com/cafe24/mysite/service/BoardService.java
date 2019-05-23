package com.cafe24.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;

@Service
public class BoardService {

    public static final int BOARD_CNT = 5;    //한번에 보여질 게시글
    public static final int PAGE_CNT = 5;    //페이지 버튼 개수
    
    @Autowired
    private BoardDao boardDao;
    
    public List<BoardVo> getList(Map<String, Object> map) {

        return boardDao.getList(map);

    }

    public boolean insert(BoardVo boardVo) {
        
        return boardDao.insert(boardVo);
    }
    
    public boolean insertReply(BoardVo boardVo) {
        
        boardDao.updateOrderNo(boardVo);
        return boardDao.reply(boardVo);
        
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

    public boolean updateOrderNo(BoardVo boardGetVo) {
        return boardDao.updateOrderNo(boardGetVo);
    }
    
    
}

