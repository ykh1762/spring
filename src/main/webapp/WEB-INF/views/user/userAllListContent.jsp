<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

			<h1 class="page-header">전체 사용자 리스트(tiles)</h1>
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
				
				// 2. form
				$("#userId").val(userId);
				
				// --> form 태그의 속성 변경 가능.
				
				$("#frm").submit();
				
			});
		});
	</script>
	
<form action="${cp }/user/user" id="frm">
	<input type="hidden" name="userId" id="userId"/>
</form>
	














