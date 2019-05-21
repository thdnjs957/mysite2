<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>

		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.servletContext.contextPath }/board/list" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					
					<!-- 여기서 부터 리스트 뿌리기 -->
					<c:set var ='count' value ='${fn:length(list) }' />
				
					<c:forEach items = '${list }' var = 'vo' varStatus = 'status'>
				
					<tr>
						<td>[${count - status.index}]</td>	<!-- 원래는 0대신 vo.depth로 -->
						<td style ="text-align:left; padding-left:${20*0}px"><a href="${pageContext.servletContext.contextPath }/board/view?boardNo=${vo.no }" >${vo.title }</a></td>
						<td>${vo.userName }</td>
						<td>${vo.hit }</td>
						<td>${vo.regDate }</td>
						<td><a href="${pageContext.servletContext.contextPath }/board/delete?boardNo=${vo.no }&userNo=${vo.userNo}" class="del">삭제</a></td>
					</tr>
					
					</c:forEach>
					
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<a href="${pageContext.servletContext.contextPath }/board/write" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
				<c:param name="menu" value="board"/>
		</c:import>
			
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>