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
					<input type="text" id="keyword" name="keyword" value="${keyword }">
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
							<td style ="text-align:left; padding-left:${15*vo.depth}px">
							<c:if test="${0 ne vo.depth }">
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>
							</c:if>	
								<a href="${pageContext.servletContext.contextPath }/board/view?boardNo=${vo.no }" >${vo.title }</a></td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td><a href="${pageContext.servletContext.contextPath }/board/delete?boardNo=${vo.no }&userNo=${vo.userNo}" class="del">삭제</a></td>
						</tr>
					</c:forEach>
				</table>
				
				<div class="pager">
					<ul class="btn-group pagination">
					    <c:if test="${prev }"> <!-- prev가 있으면 -->
						    <li>
						        <a href='<c:url value="/board/list?curPage=${startPage-1 }"/>'><i class="fa fa-chevron-left"></i></a>
						    </li>
					    </c:if>
					    <c:forEach begin='${blockBegin }' end='${blockEnd }' var="idx">
						    <li>
						        <a href='<c:url value="/board/list?curPage=${idx }"/>'><i class="fa">${idx }</i></a>
						    </li>
					    </c:forEach>
					    <c:if test="${next && endPage >0 }">
						    <li>
						        <a href='<c:url value="/board/list?curPage=${endPage+1 }"/>'><i class="fa fa-chevron-right"></i></a>
						    </li>
					    </c:if>
					</ul>
				</div>
				
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