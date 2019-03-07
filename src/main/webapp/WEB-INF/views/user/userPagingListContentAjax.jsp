<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<h1 class="page-header">전체 사용자 리스트(tiles)(ajax)</h1>
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
					<tbody id="userListTbody">
						<!-- begin="0"이면 'end' < 0 에러 발생함. (향상된 for문이면 상관없음. 'items') -->
<%-- 						<c:forEach var="i" begin="1" end="${userList.size() }"> --%>
<%-- 							<tr class="userTr" data-userid="${userList.get(i-1).getUserId() }"> --%>
<%-- 								<td>${i }</td> --%>
<%-- 								<td>${userList.get(i-1).getUserId() }</td> --%>
<%-- 								<td>${userList.get(i-1).getUserNm() }</td> --%>
<!-- 								<td>-</td> -->
<%-- 								<td><fmt:formatDate value="${userList.get(i-1).reg_dt }" pattern="yyyy/MM/dd"/></td> --%>
<!-- 							</tr> -->
<%-- 						</c:forEach> --%>
					</tbody>
				</table>
				
				<form action="${cp }/user/userForm" method="get">
					<button type="submit" class="btn btn-default">사용자 등록</button>
				</form>
				
				<nav style="text-align: center;">
					<ul id="pagination" class="pagination">	
					
					</ul>
				</nav>
								
			</div>
			
	<script>
	
	// 사용자 배열을 이용하여 사용자 리스트 html을 생성.
	function makeUserList(userList){
		var html = "";
		
		for(var i=0; i<userList.length; i++){
			var user = userList[i];
			
			// Alt+Shift+A : 세로편집.
 			html += "<tr class='userTr' data-userid='"+user.userId+"'>";
 			html += "	<td></td>";	 			html += "	<td>"+user.userId+"</td>"; 			html += "	<td>"+user.userNm+"</td>"; 			html += "	<td></td>";
 			html += "	<td>"+user.reg_dt_fmt+"</td>";
 			html += "</tr>";
 					}
		
		$("#userListTbody").html(html);
	
	}
	
	function makePagination(userCnt, pageSize, page){
		// <c:set var="lastPage" value="${Integer(userCnt / pageSize + (userCnt % pageSize > 0 ? 1 : 0)) }" />
		var lastPage = parseInt(userCnt/pageSize) + (userCnt%pageSize > 0 ? 1 : 0);
		console.log("lastPage : " + lastPage);
		
		var html = "";
		
		if(page == 1){
			html += "<li class='disabled'><a aria-label='Previous'>";
			html += "	<span aria-hidden='true'>&laquo;</span>";
			html += "</a></li>";
		}else{
// 			html += "<li><a href='${cp }/user/userPagingList?page=1' aria-label='Previous'>";
			html += "<li><a href='javascript:getUserPageList(1);' aria-label='Previous'>";
			html += "	<span aria-hidden='true'>&laquo;</span>";
			html += "</a></li>";
		}
		
		for(var i=1; i<=lastPage; i++){
			var active ="";
			if(i == page)
				active = "active";
			
			html += "<li class'" + active + "'> ";
// 			html += "	<a href='${cp}/user/userPagingList?page="+i+"'>"+i+"</a>";
			html += "	<a href='javascript:getUserPageList("+i+");'>"+i+"</a>";
			html += "</li>";
		}
		
		
		if(page == lastPage){
			html += "<li class='disabled'><a aria-label='Next'>";
			html += "	<span aria-hidden='true'>&raquo;</span>";
			html += "</a></li>";
		}else{
// 			html += "<li><a href='${cp }/user/userPagingList?page=1' aria-label='Previous'>";
			html += "<li><a href='javascript:getUserPageList("+lastPage+");' aria-label='Next'>";
			html += "	<span aria-hidden='true'>&raquo;</span>";
			html += "</a></li>";
		}		
		
		$("#pagination").html(html);
		
	}
	
	function getUserPageList(page){
// 		$.ajax({}); // {}가 json 객체.
		$.ajax({
			url : "${cp }/user/userPagingListAjax",
			data : "page="+page,
			success : function(data){
				console.log(data);
				
				makeUserList(data.userList);
				makePagination(data.userCnt, data.pageSize, data.page);
			}
		});
	}
	
	function getUserPageListHtml(page){
		$.ajax({
			url : "${cp }/user/userPagingListAjaxHtml",
			data : "page="+page,
			success : function(data){
				/*
					사용자리스트 html...
					===seperator===
					페이지네이션 html...
				*/
				console.log(data.split("===seperator==="));
				var htmlArr = data.split("===seperator===");
				
				$("#userListTbody").html(htmlArr[0]);
				$("#pagination").html(htmlArr[1]);
			}
		});
	}
	
		$(document).ready(function(){
			console.log("document ready");
			
			//getUserPageList(1);
			getUserPageListHtml(1);
			
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
	
<form action="${cp }/user/user" id="frm">
	<input type="hidden" name="userId" id="userId"/>
</form>
	

















