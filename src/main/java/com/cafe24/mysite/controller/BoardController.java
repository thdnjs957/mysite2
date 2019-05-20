package com.cafe24.mysite.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired  
	private BoardService boardService;
	
	@Autowired  
	private UserService userService;
	
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		Map<String, Object> map = new HashMap<String, Object>();

		List<BoardVo> list = boardService.getList();
		
		model.addAttribute("list",list);
		
		return "board/list";
	}
	
	@RequestMapping(value = "/write",method=RequestMethod.GET)
	public String write(HttpSession session) {
		
		if(session.getAttribute("authUser") == null) {
			return "redirect:/board/list";
		}
		
		return "board/write";
	}
	
	@RequestMapping(value = "/write",method=RequestMethod.POST)
	public String write(@ModelAttribute BoardVo boardVo) {
		boardService.insert(boardVo);
		return "redirect:/board/list";
	}
	
	
}
