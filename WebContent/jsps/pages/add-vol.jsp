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
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
</head> 
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
	<div class="add">
	    <div class="u-align-center">
	        <p class="u-5x">Thêm tập</p>
	    </div>
	    
	    <form action="add" method="post">
	        <table class="table u-2x u-centered u-width--95">
	            <tr>
	                <td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu đề: <span style="color: red">*</span></label></td>
	                <td><input name="title" class="u-width--full" type="text" style="padding: .5rem" required></td>
	            </tr>
	            <tr>
	                <td class="u-align-right u-vertical-align--middle"><label>Thuộc truyện: <span style="color: red">*</span></label></td>
	                <td><select name="in-novel" style="padding: .5rem">
	                	<!-- option value="novelOwnerID-Integer" -->
	                        <option value="123123">NovelOwner Title - server genrated!</option>
	                    </select></td>
	            </tr>
	            <tr>
	                <td class="u-align-right"><label>Tóm tắt:</label></td>
	                <td><textarea name="description" class="u-width--full" style="padding: .5rem" rows="7"></textarea></td>
	            </tr>
	        
	        </table>
	        <div class="u-align-center u-color-white">
	            <button type="submit" class="btn btn-primary u-2x">Thêm</button>
	            <a href="#" type="button" class="btn btn-danger u-2x">Quay lại</a>
	        </div>
	    
	    </form>
	    
	</div>
	</div>
</body>
</html>