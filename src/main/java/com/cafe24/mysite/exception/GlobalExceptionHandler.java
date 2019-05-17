package com.cafe24.mysite.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler( Exception.class )
	public void handleException( HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception,IOException{
		
		//e.printStackTrace(); 이렇게 하면 console로 나오고 끝임 파일로 저장 필요
		//1.로깅
		
		StringWriter errors = new StringWriter();
		
		e.printStackTrace(new PrintWriter(errors));
		
		System.out.println(errors.toString());
		
		//LOGGER.error(errors.toString());
		
		//2. 안내 페이지 가기 + 정상종료(response)
		request.setAttribute("uri", request.getRequestURI());
		request.setAttribute("exception", errors.toString());
		
		request.getRequestDispatcher("/WEB-INF/views/error/exception.jsp").forward(request, response);

	}
	
	
	
}
