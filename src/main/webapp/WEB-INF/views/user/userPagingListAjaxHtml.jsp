<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
					
<!-- 향상된 for문 -->
<%-- <c:forEach items="${userList }" var="vo"> 번호는 어떻게 출력했지? --%>
<c:forEach var="i" begin="0" end="${userList.size() - 1 }">
	<tr class="userTr" data-userid="${userList.get(i).getUserId() }">
		<td>${i+1 }</td>
		<td>${userList.get(i).getUserId() }</td>
		<td>${userList.get(i).getUserNm() }</td>
		<td>-</td>
		<td><fmt:formatDate value="${userList.get(i).reg_dt }" pattern="yyyy/MM/dd"/></td>
	</tr>
</c:forEach>	

===seperator===

<!-- lastPage값이 double값이 되므로 Integer로 형변환 -->
<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }" />

<c:choose>
	<c:when test="${page == 1 }"> 
		<li class="disabled"><a aria-label="Previous"> 
			<span aria-hidden="true">&laquo;</span>
		</a></li>							
	</c:when>
	<c:otherwise>
		<li><a href="javascript:getUserPageListHtml(1)" aria-label="Previous"> 
			<span aria-hidden="true">&laquo;</span>
		</a></li>								
	</c:otherwise>
</c:choose>

<!-- 먼저 lastPage를 기본 객체의 속성으로 저장해야 함. -->
<c:forEach var="i" begin="1" end="${lastPage }">
	<!-- <li>태그 안에 c:if를 넣어서 써도 됨. -->
	<c:choose>
		<c:when test="${i == page }">
			<li class="active"><a href="javascript:getUserPageListHtml(${i })">${i }</a></li>							
		</c:when>
		<c:otherwise>
			<li><a href="javascript:getUserPageListHtml(${i })">${i }</a></li>							
		</c:otherwise>
	</c:choose>
</c:forEach>
	
<c:choose>
	<c:when test="${page == lastPage }">
		<li class="disabled"><a aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span>
		</a></li>							
	</c:when>
	<c:otherwise>
		<li><a href="javascript:getUserPageListHtml(${lastPage })" aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span>
		</a></li>								
	</c:otherwise>
</c:choose>									








