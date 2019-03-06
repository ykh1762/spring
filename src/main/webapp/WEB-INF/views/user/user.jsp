<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must 
    		come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Dashboard</title>

    <!-- Bootstrap core CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="${cp }/css/dashboard.css" rel="stylesheet">

<%@include file="/WEB-INF/views/module/jsLib.jsp" %>

</head>
<body>
<%@include file="/WEB-INF/views/module/header.jsp" %> 

<div class="container-fluid">
	<div class="row">
		<div class="col-sm-3 col-md-2 sidebar">
			<%@include file="/WEB-INF/views/module/left.jsp"%>
		</div>
		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			<h1 class="page-header">사용자 정보 조회</h1>
			
			<form class="form-horizontal" role="form">
			
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사진</label>
					<div class="col-sm-10">
<%-- 						<c:choose> --%>
<%-- 							<c:when test="${userVo.fileName == null }"> --%>
<%-- 								<img src="${cp }/upload/noimg.png"/> --%>
<%-- 							</c:when> --%>
<%-- 							<c:otherwise> --%>
<%-- 								<img src="${cp }/upload/${userVo.fileName }"/> --%>
<%-- 							</c:otherwise> --%>
<%-- 						</c:choose> --%>
						<img src="${cp }/user/profileImg?userId=${userVo.userId }">
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.userId }</label>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.userNm}</label>
					</div>
				</div>
				<!-- dfjdihfkdjfidjfl;kjdijfkdjvkdfjcfsekfjdklskdkfjeifjdkfjeiiiiifioodijfkeijkjidjkfjikdjfejjdifjnckdmmmccmvnfdjdjfjdkffjjdjdkkfkekfkkdkdkkckkkkvkkkdkkfkkfkdlslfkkfjdkdkkekekekkdkfksllskdjfdivjdkjckdkfjdiidjfidjsksldkfjielsldkfjsldkmckvjsdfssdlfkdkdfjdislslsfjdkslslsxzkckdkkdfjfjdkkdldifjeisjdkslckjvkcjdkkdjfjvckslldjfiwoowidjvkckxlkcjvnc,cmvjsidjfjslxzkdkfjeiwopdoicjcxilxzlzcxjvkjxnckdjfiskmckdjfiejkskksfjfjkkdjfijejeidkkdjffkslxoxodidifjkfkekjfjfjdkdkfjfjdkdksisidjjfkejflskdjixzizjcxivjcxizjlkdjflkadjflakdfjaldijcidjicjajdknakdnxckjkxjckzxjcisjcddjffwodjzkdsfjfjdiiddiidjccjidssojffijeijfjoaoijsijdoijzkljcjlkjcidjcijkzjsicjskaslkjdlaisaoiajsdjcdklskzjkclskcjlcjzksjcklzksjdklzjsdijwdjlasdjskldaksjdoiwquqwieqwpoeiqwpoeiqwoudksjdskjcksjckzcjzkxcjzkcjjcjskjcksjcdijcicjcidjaiojsjjdisoaoaisjddjjdsiisjddjisdijdjjdisaosijdaiosjdaoisdjoaisdjdjisodjaoisdjaoisdjaisd -->
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">별명</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.alias }</label>
					</div>
				</div>
				
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">우편번호</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.zipcode}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">주소</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.addr1}</label>
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-2 control-label">상세주소</label>
					<div class="col-sm-10">
						<label class="control-label">${userVo.addr2}</label>
					</div>
				</div>

				
				<div class="form-group">
					<label for="pass" class="col-sm-2 control-label">등록일자</label>
					<div class="col-sm-10">
						<label class="control-label">
							<fmt:formatDate value="${userVo.reg_dt }" pattern="yyyy/MM/dd"/>
						</label>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button id="btn_edit" type="button" class="btn btn-default">사용자 수정</button>
					</div>
				</div>
			</form>			
			
		</div>
	</div>
</div>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script>
    	// ${msg} != null 이렇게 써서 alert가 안떴었네.
		<c:if test="${msg != null}">
			alert("${msg}");
		<%	session.removeAttribute("updateMsg"); %>
		</c:if>
		
    	$(document).ready(function(){
    		console.log("document ready");
   			console.log("${userVo.userId}"+11);
   			
   			
    		
    		$("#btn_edit").on("click", function(){
    			var userId = "${userVo.userId}";
    			$("#userId").val(userId);
    			
    			$("#frm").submit();
    			
    		});
    		
    	});
    </script>
    
    <!-- UserModifyFormController로 요청을 보냄. -->
    <form action="${cp }/user/userModifyForm" id="frm">
    	<input type="hidden" id="userId" name="userId">
    </form>

</body>
</html>

<%-- localhost/module/main.jsp --%>

















