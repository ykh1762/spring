<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="${cp }/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function(){
		console.log("ajaxView.jsp");
		
		// jsonData 요청
		$("#jsonReqBtn").on("click", function(){
// 			jsonView();
// 			responseBody();
			requestBody();
		});
	});
	
	function renderTable(data){
		
		console.log("render function");
		
		var text = "";
		
		// length -> attribute, length() -> method (X)
		for(var i=0; i<data.rangerList.length; i++){
			var ranger = data.rangerList[i];
			
			text += "<tr>";
			text += "	<td>"+ranger+"</td>";
			text += "</tr>";
		}
		
		// text -> <tr><td>brown</td></tr><tr><td>cony</td></tr>...
		
		$("#jsonRecvTbody").html(text);
	}
	
	function requestBody(){
		var reqData = { userId : "brown", userNm : "브라운" };
		
		$.ajax({
			url : "${cp }/ajax/requestBody",
			method : "post",
			
			// param 추가
// 			data : "userId=brown&userNm=브라운",

			// serialize() : form - input - value를 자동으로 넣어줌.
// 			data : $("#frm").serialize(),

			// client가 전송하는 데이터 타입.
			contentType : "application/json; charset=utf-8",

			// data를 json 문자열로 전송.
			data : JSON.stringify(reqData),

			dataType : "json",			
			success : function(data){
				console.log("data : " + data);
				
				$("#jsonRecvTbody").html("<tr><td>"+data.userId+"</td></tr>");
				
// 				renderTable_responseBody(data);
			}
		});
	}
	
	function renderTable_responseBody(data){
		
		console.log("render function");
		
		var text = "";
		
		for(var i=0; i<data.length; i++){
			var ranger = data[i];
			
			text += "<tr>";
			text += "	<td>"+ranger+"</td>";
			text += "</tr>";
		}
		
		$("#jsonRecvTbody").html(text);
	}
	
	function responseBody(){
		$.ajax({
			url : "${cp }/ajax/responseBody",
			method : "post",
			
			// dataType json 요청 추가 (server에 희망하는 리턴타입을 명시)
			dataType : "json",			
			
			// ajax/jsonView랑 data 형식이 다름.
			// ajax/jsonView : {rangerList : ["brown", "cony", ...]}
			// ajax/responseBody : ["brown", "cony", ...]
			success : function(data){
				console.log("data : " + data);
				
				renderTable_responseBody(data);
			}
		});
	}
	
	function jsonView(){
		$.ajax({
//				url : "${cp }/ajax/jsonView",
			url : "${cp }/ajax/jsonView",
			method : "post",
			success : function(data){
				// data.rangerList -> array.
				
				// chrome -> DevTools(F12) -> Source -> breakpoint(line click) -> 
				// debug(expression)
				console.log("data : " + data);
				
				renderTable(data);
				
			}
		});
	}
	
	
</script>

</head>
<body>
	<form id="frm">
		<input type="text" name="userId" value="brown"/>
		<input type="text" name="userNm" value="브라운"/>
	</form>
	

	<h2>ajaxView.jsp</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData 요청</button> <br><br>
		<div id="jsonRecv">
			<table border="1">
				<thead>
					<tr>
						<th>이름</th>
					</tr>
				</thead>
				<tbody id="jsonRecvTbody">
<!-- 
					<tr>
						<td>brown</td>
					</tr>
					<tr>
						<td>cony</td>
					</tr>
 -->
				</tbody>
			</table>
		</div>
	</div>




</body>
</html>
















