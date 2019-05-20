package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")//이건 요청 url
public class UserController {
	
	@Autowired  //spring DI를 사용한 것이다
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";//이건 view forward
	} 

	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		
		userService.join(userVo);
		return "redirect:/user/joinsuccess";
		
	} 
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	} 
	

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	} 
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
			@RequestParam(value="email", required=true, defaultValue="") String email,
			@RequestParam(value="password", required=true, defaultValue="") String password,
			HttpSession session,Model model) 
	{	
		UserVo authUser = userService.getUser(new UserVo(email,password));
		
		if(authUser == null) {
			model.addAttribute("result","fail");
			return "user/login";
		}
		
		//session 처리
		session.setAttribute("authUser", authUser);
		
		return "redirect:/"; //main으로 redirect
	} 
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model model,HttpSession session) {
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		UserVo userVo = userService.getUser(authUser.getNo());
		
		model.addAttribute("userVo",userVo);
		
		return "user/updateform";
	}
	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(HttpSession session,@ModelAttribute UserVo userVo) {
		
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		userService.update(userVo);
		
		// session의 authUser 변경
		authUser.setName(userVo.getName());
				
		return "redirect:/";
	}
	
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	} 
	
	@RequestMapping("/myinfo")
	public String myinfo() {
		
		return "user/myinfo";
	} 
	
	
//	@ExceptionHandler( Exception.class )
//	public String handleUserDaoException() {
//		return "error/exception";
//	}

	
}
