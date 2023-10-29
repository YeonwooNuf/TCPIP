<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<title>채팅</title>
</head>
<body>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
<!-- 	827d4f30a15338ad1ad51eb550f87cc2 -->
	window.Kakao.init("827d4f30a15338ad1ad51eb550f87cc2");
	
	function kakaoLogin(){
		window.Kakao.Auth.login({
			scope:'profile_nickname, profile_image',
			success: function(authObj){
				console.log(authObj);
				window.Kakao.API.request({
					url:'/v2/user/me',
					success: res => {
						const kakao_account = res.kakao_account;
						console.log(kakao_account);
					}
				});
			}
		});
		
	}

</script>

	<div class="container">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
			<div class="jumbotron" style="padding-top: 20px;">
				<form method="post" action="loginAction.jsp">
					<h3 style="text-align:center;">로그인 화면</h3>
					<div class="form-group">
						<input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20"> 
					</div>
					<div class="form-group">
						<input type="password" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20"> 
					</div>
					
					<div class="form-group">
					<input type="submit" class="btn btn-primary form-control" value="로그인">
					</div>
					
					<div class="form-group">	
					<a href="join.jsp" class="btn btn-primary form-control">회원가입</a>
					</div>
					<div class="form-group">
					<a href="javascript:kakaoLogin();"><img src=".\img\kakao_login_medium_wide.png" alt="kakao"
											style="height:34px; width:230px;" /></a>
					</div>
				</form>
			</div>
		</div>
		<div class="col-lg-4"></div>
	</div>
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

</body>
</html>