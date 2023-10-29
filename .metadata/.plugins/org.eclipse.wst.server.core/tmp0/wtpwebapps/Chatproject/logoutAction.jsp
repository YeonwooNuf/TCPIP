<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="user.UserDAO" %>
	<%@ page import="java.io.PrintWriter" %>
	
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		session.invalidate();
		%>
		<script>
		function kakaoLogout(kakaoKey){
			Kakao.init(kakaoKey);
			Kakao.isInitialized();
			
			if(Kakao.Auth.getAccessToken()){
				console.log('Not logged in.');
				return;
			}
			
			Kakao.Auth.logout(function(){
				console.log(Kakao.Auth.getAccessToken());
			});
			
			
		};

		
			location.href = 'Login.jsp';
		</script>
</body>
</html>