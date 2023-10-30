<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width" , initial-scale="1">
<link rel="stylesheet" href="css/bootstrap.css">
<link rel="stylesheet" href="css/custom.css">
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<!--  카카오 로그아웃 기능 구현 -->
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script>
window.Kakao.init("827d4f30a15338ad1ad51eb550f87cc2"); 
function kakaoLogout() {
   /*  if (!Kakao.Auth.getAccessToken()) {
      alert("Not logged in.");
      return;
    } */
    Kakao.Auth.logout(function (response) {
    	  if(response == true)
    	 {
             Kakao.Auth.setAccessToken(undefined);		//토큰 제거
             sessionStorage.clear();					//세션 제거
             localStorage.clear();						//로컬스토리지 
    	 } 
      		location.href = 'Login.jsp';
    	
    	
    
    });
}
  
		
</script>


<ul class="nav navbar-nav navbar-right">
				<li class="dropdown">
				<a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false">더보기<span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="javascript:kakaoLogout();">로그아웃</a></li>
					</ul>
					
				</li>
			</ul>
			
			
			
			
			
			
			
			
			
			
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="js/bootstrap.js"></script>

</body>
</html>