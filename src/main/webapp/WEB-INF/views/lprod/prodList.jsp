<%@page import="kr.or.ddit.prod.model.ProdVo"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<h1 class="page-header">제품 리스트</h1>
			<%
				List<ProdVo> prodList = (List) request.getAttribute("prodList");
			%>
			<div class="table-responsive">
				<table class="table table-striped">
					<thead>
						<tr>
							<th>#</th>
							<th>상품번호</th>
							<th>상품이름</th>
							<th>거래처번호</th>
							<th>상품가격</th>
						</tr>
					</thead>
					<tbody>
					<%
						for(int i=0; i<prodList.size(); i++){
							out.write("<tr class='prodTr' data-prod_id='"+ prodList.get(i).getProd_id() +"'>");
							out.write("    <td>"+ (i+1) +"</td>");
							out.write("    <td>"+ prodList.get(i).getProd_id() +"</td>");
							out.write("    <td>"+ prodList.get(i).getProd_name() +"</td>");
							out.write("    <td>"+ prodList.get(i).getProd_buyer() +"</td>");
							out.write("    <td>"+ prodList.get(i).getProd_cost() +"</td>");
							out.write("</tr>");
						}
					%>
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
		// 문서로딩이 완료된 이후 이벤트 등록
		$(document).ready(function(){

		});
	</script>
	
<form id="frm">
	<input type="hidden" name="userId" id="userId"/>
</form>
	
</body>
</html>

<%-- localhost/module/main.jsp --%>

















