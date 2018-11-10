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
    <div class="search-form u-centered">
    <form action="search" method="post">
        <div class="search-bar row u-2x">
            <div class="col-sm-2"></div>
            <div class="col-sm-6">
                <input class="input u-width--full" type="text" name="input" placeholder="Tìm kiếm tên truyện...">
            </div>

            <div class="col-sm-3">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button><br>
                <a type="submit" href="search?type=advanced" class="link l-advanced-search">Tìm kiếm nâng cao</a>
            </div>
            <div class="col-sm-1"></div>
        </div>
    </form>
</div>
</div>
    <%@include file="/jsps/components/_footer.jsp"%>	
</body>
</html>