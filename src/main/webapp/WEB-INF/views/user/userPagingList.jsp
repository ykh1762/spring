<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Paging</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="${cp }/css/dashboard.css" rel="stylesheet">

<%@include file="/WEB-INF/views/module/jsLib.jsp" %>

</head>
<body>
<%@include file="/WEB-INF/views/module/header.jsp" %> 


<%-- 정적 include --%>


<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@include file="/WEB-INF/views/module/left.jsp"%>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">전체 사용자 리스트</h1>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>사용자 아이디</th>
							<th>사용자 이름</th>
							<th>별명</th>
							<th>등록일시</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="i" begin="0" end="${userList.size() - 1 }">
							<tr class="userTr" data-userid="${userList.get(i).getUserId() }">
								<td>${i+1 }</td>
								<td>${userList.get(i).getUserId() }</td>
								<td>${userList.get(i).getUserNm() }</td>
								<td>-</td>
								<td><fmt:formatDate value="${userList.get(i).reg_dt }" pattern="yyyy/MM/dd"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<form action="${cp }/userForm" method="get">
					<button type="submit" class="btn btn-default">사용자 등록</button>
				</form>
				
				<!-- lastPage값이 double값이 되므로 Integer로 형변환 -->
				<c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }" />
				
				<nav style="text-align: center;">
					<ul class="pagination">	
							<!-- cpage가 아니고 속성(attribute)에 저장된 page를 가져와야 한다. -->
						<c:choose>
							<c:when test="${page == 1 }"> 
								<li class="disabled"><a aria-label="Previous"> 
									<span aria-hidden="true">&laquo;</span>
								</a></li>							
							</c:when>
							<c:otherwise>
								<li><a href="${cp }/user/userPagingList?page=1" aria-label="Previous"> 
									<span aria-hidden="true">&laquo;</span>
								</a></li>								
							</c:otherwise>
						</c:choose>
						
						<!-- 먼저 lastPage를 기본 객체의 속성으로 저장해야 함. -->
						<c:forEach var="i" begin="1" end="${lastPage }">
							<!-- <li>태그 안에 c:if를 넣어서 써도 됨. -->
							<c:choose>
								<c:when test="${i == page }">
									<li class="active"><a href="${cp }/user/userPagingList?page=${i }">${i }</a></li>							
								</c:when>
								<c:otherwise>
									<li><a href="${cp }/user/userPagingList?page=${i }">${i }</a></li>							
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
								<li><a href="${cp }/user/userPagingList?page=${lastPage }" aria-label="Next"> 
									<span aria-hidden="true">&raquo;</span>
								</a></li>								
							</c:otherwise>
						</c:choose>						
					</ul>
				</nav>
								
			</div>
		</div>
	</div>
</div>


    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			console.log("document ready");
			
			// msg 속성이 존재하면 alert, 없을땐 넘어가기.
			<c:if test="${msg != null}">
				alert("${msg}");
				<%
					// msg를 다시 삭제해줘야함.
					session.removeAttribute("msg");
				%>
			</c:if>
			
			
			$(".userTr").on("click", function(){
				var userId = $(this).data("userid");
				
				$("#userId").val(userId);
				$("#frm").submit();
			});
		});
	</script>
	
<form action="${cp }/user" id="frm">
	<input type="hidden" name="userId" id="userId"/>
</form>
	
</body>
</html>

<%-- localhost/module/main.jsp --%>

















