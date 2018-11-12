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
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="search">
        <div class="search-account u-centered">
        <form action="manage" method="post">
            <div class="search-bar u-centered u-align-center u-2x row" style="padding: 1rem">
                <div class="col-sm-2">
                    <select class="input">
                        <option><label>Tài khoản</label></option>
                        <option><label>Mail</label></option>
                    </select>
                </div>
                <div class="col-sm-8">
                    <input id="input" class="input u-width--full u-2x" type="text" name="input" required>
                </div>
                <div class="col-sm-2">
                    <button type="submit" class="btn btn-primary u-2x">Tìm kiếm</button><br>
                </div>
            </div>
        </form>
        <hr>
        <div id="account-result" class="u-centered u-margin-top--2rem u-width--75">
            <div class="account-info row">
                <div class="col-sm-2 u-align-right"><img class="img"></div>
                <div class="col-sm-7"><p class="u-2x">Name</p></div>
                <div class="col-sm-3 u-align-center"><button class="btn btn-primary u-2x">+Quyền ghim</button></div>
            </div>
        </div>
    </div>
</div>
    <%@include file="/jsps/components/_footer.jsp"%>	
</body>
</html>