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
	  <h1 class="page-header">사용자 정보 수정</h1>
	  
	  <form id="frm" action="${cp }/user/userModifyForm" method="post" 
	  		class="form-horizontal" role="form" enctype="multipart/form-data">
	  
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">사진</label>
	  		<div class="col-sm-7">
	  			<img src="" style="margin-bottom: 20px;">
	  			<input type="file" class="form-control" id="profile" name="profile"
	  				placeholder="사진">
	  		</div>
	  	</div>
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
	  		<div class="col-sm-7">
	  			<input type="text" class="form-control" id="userId" name="userId"
	  				placeholder="사용자 아이디" readonly>
	  		</div>
	  	</div>
	  
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
	  		<div class="col-sm-7">
	  			<input type="text" class="form-control" id="userNm" name="userNm"
	  				placeholder="사용자 이름">
	  		</div>
	  	</div>
	  	
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">별명</label>
	  		<div class="col-sm-7">
	  			<input type="text" class="form-control" id="alias" name="alias"
	  				placeholder="별명">
	  		</div>
	  	</div>
	  	
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">우편번호</label>
	  		<div class="col-sm-4">
	  			<input type="text" class="form-control" id="zipcode" name="zipcode"
	  				placeholder="우편번호" readonly>
	  		</div>
	  		<div class="col-sm-3">
	  			<button id="zipcodeBtn" type="button" class="btn btn-default">검색</button>
	  		</div>
	  	</div>	  	
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">주소1</label>
	  		<div class="col-sm-7">
	  			<input type="text" class="form-control" id="addr1" name="addr1"
	  				placeholder="주소" readonly>
	  		</div>
	  	</div>
	  	<div class="form-group">
	  		<label for="userNm" class="col-sm-2 control-label">주소2</label>
	  		<div class="col-sm-7">
	  			<input type="text" class="form-control" id="addr2" name="addr2"
	  				placeholder="상세주소">
	  		</div>
	  	</div>
	  	

	  	
	  	<div class="form-group">
	  		<label for="pass" class="col-sm-2 control-label">Password</label>
	  		<div class="col-sm-7">
	  			<input type="password" class="form-control" id="pass" name="pass">
	  		</div>
	  	</div>
	  
	  	<div class="form-group">
	  		<div class="col-sm-offset-4 col-sm-8">
	  			<button id="regBtn" type="button" class="btn btn-default">확인</button>
	  		</div>
	  	</div>
	  </form>			
	  
  	</div>
  </div>
</div>

<%
	UserVo user = (UserVo) request.getAttribute("user");
%>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function initData(){
 		<%-- $("#userId").val("<%=user.getUserId() %>"); --%>
		$("#userId").val("${user.userId}");
		$("#userNm").val("${user.userNm}");
		$("#alias").val("${user.alias}");
		$("#addr1").val("${user.addr1}");
		$("#addr2").val("${user.addr2}");
		$("#zipcode").val("${user.zipcode}");
		//$("#pass").val("${user.pass}");
		$("img").attr("src", "${cp}/user/profileImg?userId=${user.userId}");
		// 다시 입력해야할 때 입력한 값이 남아있게 해줌.
	}


	$(document).ready(function(){
		initData();
		
		// 우편번호 검색 버튼 클릭 이벤트. -> 다음 주소검색 팝업 open.
		$("#zipcodeBtn").on("click", function(){
		    new daum.Postcode({
		        oncomplete: function(data) {
		            $("#zipcode").val(data.zonecode);
		            
		            $("#addr1").val(data.roadAddress);
		            
		            $("#addr2").focus();
		        }
		    }).open();
		});
		
		// 사용자 등록 버튼 클릭 이벤트.
		$("#regBtn").on("click", function(){
			// 사용자 아이디
			if($("#userId").val().trim() == ""){
				alert("사용자 아이디를 입력해주세요.");
				$("#userId").focus();
				return false;
			}
			
			// 사용자 이름
			if($("#userNm").val().trim() == ""){
				alert("사용자 이름를 입력해주세요.");
				$("#userNm").focus();
				return false;
			}
			
			
			// 별명
			if($("#alias").val().trim() == ""){
				alert("별명를 입력해주세요.");
				$("#alias").focus();
				return false;
			}
			
			// 주소1, 우편번호
			if($("#addr1").val().trim() == ""){
				alert("주소를 입력해주세요.");
				$("#userNm").focus();
				$("#zipcodeBtn").trigger("click");
				return false;
			}
			
			// 주소2
			if($("#addr2").val().trim() == ""){
				alert("상세주소를 입력해주세요.");
				$("#addr2").focus();
				return false;
			}
			
			
			// 비밀번호
// 			if($("#pass").val().trim() == ""){
// 				alert("비밀번호를 입력해주세요.");
// 				$("#pass").focus();
// 				return false;
// 			}
			
			$("#frm").submit();
			// 정보 수정 요청은 post로 보냄.
			
		});
		
	});
</script>


</body>
</html>

<%-- localhost/module/main.jsp --%>

















