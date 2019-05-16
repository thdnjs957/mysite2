<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.cafe24.mysite.vo.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="user">
				<form id="update-form" name="updateform" method="post" action="<%=request.getContextPath() + "/user" %>">
					<input type="hidden" name="a" value="update" />
					<input type="hidden" name="no" value="${authUser.no}" />
					
					<h4>기존 이름 : </h4> 
					${name }<br />
					<h4>기존 이메일 : </h4> 
					${email }<br />
					<h4>기존 비밀번호 : </h4>
					${password }<br />
					
					<label class="block-label" for="name">수정할 이름</label>
					<input id="name" name="name" type="text" value="">					
					<label class="block-label" for="email">수정할 이메일</label>
					<input id="email" name="email" type="text" value="">
					<label class="block-label" >수정할 패스워드</label>
					<input name="password" type="password" value="">
					
					<input type="submit" value="수정">
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>