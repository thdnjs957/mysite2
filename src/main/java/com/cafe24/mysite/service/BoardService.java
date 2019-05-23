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

    public static final int BOARD_CNT = 5;    //페이지당 게시물 수
    public static final int PAGE_CNT = 5;    //화면당 페이지 수 
    
    @Autowired
    private BoardDao boardDao;
    
    public List<BoardVo> getList(Map<String, Object> map,Model model) { //map.put("keyword", keyword) , map.put("curPage",curPage);
    	
    	int curPage = (int) map.get("curPage"); //현재 있는 페이지
    	int prevPage;
    	int nextPage;
    	int totalPage = boardDao.getCount(map); 
    	int totalBlock = (int)Math.ceil(totalPage)/PAGE_CNT;
    	int curBlock = (int)Math.ceil((curPage-1)/PAGE_CNT)+1;
    	int nextBlock;
    	int pageBegin = (curPage-1) * BOARD_CNT +1;; //#{start}
    	int pageEnd= pageBegin+BOARD_CNT-1; //#{end}
    	int blockBegin = (curBlock-1)*PAGE_CNT+1;
    	int blockEnd = blockBegin+PAGE_CNT-1;
    	if(blockEnd > totalPage) blockEnd = totalPage;
    	prevPage = (curPage == 1)? 1:(curBlock-1)*PAGE_CNT;
    	//nextPage = curBlock > totalBlock ? (curBlock*PAGE_CNT);
    	//if(nexPage >= totalPage) next

    	map.put("totalPage", totalPage);
    	map.put("boardCnt",BOARD_CNT);
    	map.put("pageCnt",PAGE_CNT);
    	map.put("pageBegin",pageBegin);
    	map.put("pageEnd",pageEnd);
    	map.put("blockBegin",blockBegin);
    	map.put("blockEnd",blockEnd);
    	map.put("prevPage",prevPage);
    	
    	model.addAttribute("blockBegin",map.get("blockBegin"));
    	model.addAttribute("blockEnd",map.get("blockEnd"));

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

