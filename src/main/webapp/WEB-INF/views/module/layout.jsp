<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Dashboard</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="${cp }/css/dashboard.css" rel="stylesheet">
    
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>    

<tiles:insertAttribute name="jsLib"/>

</head>
<body>
<tiles:insertAttribute name="header"/>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
<%-- 			<%@include file="/WEB-INF/views//module/left.jsp"%> --%>
			<tiles:insertAttribute name="left"/>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
			<tiles:insertAttribute name="content"/>
			
		</div>
	</div>
</div>



	<script>
		// 문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){
			console.log("document ready");
			
			// 사용자 tr 태그 클릭시 이벤트 핸들러
			$(".userTr").on("click", function(){
				
				// 클릭한 userTr 태그의 userId 값을 출력.
				var userId = $(this).data("userid");
				
				// /user
				// 1. document
// 				document.location = "/user?userId=" + userId;
				
				// 2. form
				$("#userId").val(userId);
				
// 				$("#frm").attr("action", "/userAllList");
				// --> form 태그의 속성 변경 가능.
				
				$("#frm").submit();
				
			});
		});
	</script>
	
<%
	pageContext.getRequest().equals(request);
	pageContext.getSession().equals(session);

	request.getContextPath();
	((HttpServletRequest) pageContext.getRequest()).getContextPath();
	
	application.getContextPath();
	pageContext.getServletContext().getContextPath();
	
	// pageContext : 해당 jsp 페이지의 정보를 담고 있고 다른 기본 객체를 얻어올 수 있다.
	// el에서 기본객체에 바로 접근할 수 없어서 pageContext를 이용.
	
%>
<form action="${cp }/user" id="frm">
	<input type="hidden" name="userId" id="userId"/>
</form>
	
</body>
</html>

<%-- localhost/module/main.jsp --%>

















