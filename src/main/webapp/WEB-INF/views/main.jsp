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

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${cp }/css/dashboard.css" rel="stylesheet">

<%@include file="/WEB-INF/views/module/jsLib.jsp"%>

</head>
<body>
	<%@include file="/WEB-INF/views/module/header.jsp"%>


	<%-- 정적 include --%>

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@include file="/WEB-INF/views/module/left.jsp"%>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">SPRING with LineFriends</h1>
			<br>
				<img src="../img/lineFriends.jpg" width="800" border="1">			
			<br>
			<br>
			<br>
			<label>수정되었습니다.</label>
			<form action="${cp }/userPassEncrypt">
				<input type="submit" class="btn btn-default" value="암호화1" disabled/>
			</form>

		</div>
	</div>
</div>





</body>
</html>

<%-- localhost/module/main.jsp --%>

















