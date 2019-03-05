<%@page import="kr.or.ddit.lprod.model.LprodVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <title>JSP Dashboard</title>

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
			<h1 class="page-header">전체 리스트</h1>
			
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>분류번호</th>
							<th>카테고리</th>
							<th>분류이름</th>
						</tr>
					</thead>
					<tbody>

						<c:forEach begin="0" end="${lprodList.size() - 1 }" var="i">
							<tr class="lprodTr" data-lprod_gu="${lprodList.get(i).lprod_gu }">
							<td>${i+1 }</td>
							<td>${lprodList.get(i).lprod_id }</td>
							<td>${lprodList.get(i).lprod_gu }</td>
							<td>${lprodList.get(i).lprod_nm }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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
			$(".lprodTr").on("click", function(){
				var lprod_gu = $(this).data("lprod_gu");
				
				$("#lprod_gu").val(lprod_gu);
				$("#frm").submit();
			});				
		});
	</script>
	
<form action="${cp }/lprod/prodList" id="frm">
	<input type="hidden" name="lprod_gu" id="lprod_gu"/>
</form>
	
</body>
</html>

<%-- localhost/module/main.jsp --%>

















