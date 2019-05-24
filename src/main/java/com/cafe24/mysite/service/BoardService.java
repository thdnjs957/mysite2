package com.cafe24.mysite.service;

import java.util.HashMap;
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
    
    public Map<String,Object> getList(String keyword,int curPage) { //map.put("keyword", keyword) , map.put("curPage",curPage);
       
       
    	//1. 페이징을 위한 기본 데이터 계산
		int totalCount = boardDao.getCount( keyword ); 
		int pageCount = (int)Math.ceil( (double)totalCount / BOARD_CNT );
		int blockCount = (int)Math.ceil( (double)pageCount / PAGE_CNT );
		int currentBlock = (int)Math.ceil( (double)curPage / PAGE_CNT );
		
		//2. 파라미터 page 값  검증
		if( curPage > pageCount ) {
			curPage = pageCount;
			currentBlock = (int)Math.ceil( (double)curPage / PAGE_CNT );
		}		
		
		if( curPage < 1 ) {
			curPage = 1;
			currentBlock = 1;
		}
		
		//3. view에서 페이지 리스트를 렌더링 하기위한 데이터 값 계산
		int beginPage = currentBlock == 0 ? 1 : (currentBlock - 1)*PAGE_CNT + 1;
		int prevPage = ( currentBlock > 1 ) ? ( currentBlock - 1 ) * PAGE_CNT : 0;
		int nextPage = ( currentBlock < blockCount ) ? currentBlock * PAGE_CNT + 1 : 0;
		int endPage = ( nextPage > 0 ) ? ( beginPage - 1 ) + BOARD_CNT : pageCount;
       
       //boolean prev = pageStart != 1 ? true:false; // 페이징 이전 버튼 활성화 여부
       //boolean next = pageEnd * BOARD_CNT >= totalCount ? false : true;
       Map<String, Integer> pagerMap = new HashMap<String, Integer>();
       pagerMap.put("pageStart", beginPage);
//       pagerMap.put("pageEnd", pageEnd);
       
       
       Map<String, Object> listMap = new HashMap<String, Object>();
       listMap.put("curPage",curPage);
       listMap.put("pageCnt",PAGE_CNT);
       listMap.put("keyword", keyword);
       

       List<BoardVo> list = boardDao.getList(listMap);
       
      Map<String, Object> map = new HashMap<String, Object>();
      
      map.put("list", list);
      map.put("pagerMap", pagerMap);
      
      return map;
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