<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>msgView.jsp</h2>
	
<spring:message code="hello"/> <br><br>
	
<!-- select box를 변경(change) -> 해당 언어로 locale 변경.
	 기존 : <fmt:setLocale "en"/> 
	 spring -> 'interceptor, localeResolver 이용.		-->
<form action="">	 
	<select id="langSelect" name="language">
		<option value="ko">한국어</option>
		<option value="en">영어</option>
		<option value="ja">일본어</option>
	</select>	<br><br>
	<input type="submit" value="전송">
</form>
	
	
</body>
</html>














