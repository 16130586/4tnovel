<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
		<div class="account-manage__content">
			<div class="row u-height--100">
				<%@ include file="/jsps/components/_account-manage.admin-nav.jsp" %>	
				<div class="col-md-10">
					<div>
                        <p class="u-3x u-align-center">Bảng thư tố cáo</p>
                    </div>
                    <table class="table table-hover u-width--75 u-centered">
                        <tr>
                            <th class="u-width--15">Người dùng</th>
                            <th>Nội dung</th>
                            <th class="u-width--10">Địa điểm</th>
                            <th class="u-width--10">Thời gian</th>
                        </tr>
                        <tr>
                            <td><a class="link u-block" href="#">Tuyên</a></td>
                            <td>có phổng đạn -- 3que lul</td>
                            <td><a class="link u-block" href="#">xxx</a></td>
                            <td><label>dd/mm/yyyy</label></td>
                        </tr>
                        <tr>
                            <td><a class="link u-block" href="#">Tuyên</a></td>
                            <td>có phổng đạn -- 3que lul</td>
                            <td><a class="link u-block" href="#">xxx</a></td>
                            <td><label>dd/mm/yyyy</label></td>
                        </tr>
                        <tr>
                            <td><a class="link u-block" href="#">Tuyên</a></td>
                            <td>có phổng đạn -- 3que lul</td>
                            <td><a class="link u-block" href="#">xxx</a></td>
                            <td><label>dd/mm/yyyy</label></td>
                        </tr>
                        <tr>
                            <td><a class="link u-block" href="#">Tuyên</a></td>
                            <td>có phổng đạn -- 3que lul</td>
                            <td><a class="link u-block" href="#">xxx</a></td>
                            <td><label>dd/mm/yyyy</label></td>
                        </tr>
                    </table>
				</div>		
			</div>
		</div>
	</div>
</body>
</body>
</html>