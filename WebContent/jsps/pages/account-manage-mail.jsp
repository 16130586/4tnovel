<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Đặt lại Mail khôi phục</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="account-manage__content u-row--1140 u-centered">
			<div>
				<p class="u-3x u-align-center">Đổi email</p>
			</div>
			<form action="changeEmail" method="post">
				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5 u-align-right">Nhập email mới:</div>
					<div class="col-md-7">
						<input name="new-mail" class="u-width--50" type="mail" required>
					</div>
				</div>
				<div class="col-md-12 u-align-center">
						<p class="u-paragraph--failed u-centered">${newMailError}</p>
					</div>
				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5 u-align-right">Nhập mã xác nhận:</div>
					<div class="col-md-7">
						<input name="otp" type="text" required>
						<button type="button" id="accquireVerifyCode"
							class="btn btn-primary u-color-white">Gửi mã xác nhận</button>
						<small id="textDisplayer" style="color: #009624; font-size: 2rem;">Hết
							hạn sau 5 phút</small>
					</div>
					<c:if test="${empty verifyCodeError}">
						<div class="col-md-12 u-align-center">
							<p class="u-paragraph--failed u-centered">${reVerifyCodeError}</p>
						</div>
					</c:if>
				</div>
				<div class="col-md-12 u-padding-bottom--1rem u-align-center">
						<p class="u-paragraph--failed u-centered">${verifyCodeError}</p>
					</div>
				<div class="row u-padding-bottom--1-5rem">
					<div class="col-md-5"></div>
					<div class="col-md-7">
						<button class="btn btn-primary u-color-white">Đổi</button>
						<a href="manage" class="btn btn-danger u-color-white">Hủy</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		document.addEventListener("DOMContentLoaded" , function(){
			if(accquireVerifyCode == null)
				accquireVerifyCode = document.getElementById("accquireVerifyCode");
			if(textDisplayer == null)
				textDisplayer = document.getElementById("textDisplayer");
			accquireVerifyCode.onclick = function(){
				accquireVerifyCode.disabled = true;
				var xhttp = new XMLHttpRequest();
  				xhttp.onreadystatechange = function() {
    			if (this.readyState == 4 && this.status == 200) {
    				textDisplayer.innerHTML = "Vui lòng kiểm tra thư!";
    				accquireVerifyCode.disabled = false;
    				}
    				
  				else if(this.readyState == 4 && this.status != 200) {
  					textDisplayer.innerHTML = "Đã xảy ra lỗi, vui lòng thử lại!";
  					accquireVerifyCode.disabled = false;
  					}
				};
  				xhttp.open("POST", "send-mail-code", true);
  				xhttp.send();
				
			}
		})
  </script>
</body>
</html>