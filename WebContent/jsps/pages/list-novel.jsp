<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<title>Danh sách truyện | 4TNOVEL</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
	href="https://use.fontawesome.com/releases/v5.4.2/css/solid.css"
	integrity="sha384-uKQOWcYZKOuKmpYpvT0xCFAs/wE157X5Ua3H5onoRAOCNkJAMX/6QF0iXGGQV9cP"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.2/css/fontawesome.css"
	integrity="sha384-HU5rcgG/yUrsDGWsVACclYdzdCcn5yU8V/3V84zSrPDHwZEdjykadlgI6RHrxGrJ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<%@ include file="/jsps/components/_header.jsp" %>
	<div style="background: white;">
    <div class="u-width--80 u-centered">
        <h2 class="u-margin-bottom--2rem" style="padding-top: 2rem">DANH SÁCH TRUYỆN</h2>
    </div>
    <div class="row u-width--80 u-centered">
        <div class="col-3">
            <section class="u-margin-bottom--2rem u-padding--05rem" style="color: #61a4ad;
            text-transform: uppercase;
       		font-weight: bold;
        	font-size: 1.8rem;">
                <div class="sec-title"><span style="border-bottom: 3px solid #10b591">phân loại</span></div>
                <div class="u-margin-left--2rem">
                    <a href="#" class="link u-block u-padding--05rem">Truyện dịch</a>
                    <a href="#" class="link u-block u-padding--05rem">Truyện sáng tác</a>
                    <a href="#" class="link u-block u-padding--05rem">Tất cả</a>
                </div>
            </section>
            <section class="u-margin-bottom--2rem u-padding--05rem">
                <div class="sec-title"><span style="border-bottom: 3px solid #10b591">tình trạng</span></div>
                <div class="u-margin-left--2rem">
                    <a href="#" class="link u-block u-padding--05rem">Đang tiến hành</a>
                    <a href="#" class="link u-block u-padding--05rem">Tạm ngưng</a>
                    <a href="#" class="link u-block u-padding--05rem">Đã hoàn thành</a>
                </div>
            </section>
            <section class="u-margin-bottom--2rem u-padding--05rem">
                <div class="sec-title"><span style="border-bottom: 3px solid #10b591">thể loại</span></div>
                <div class="u-margin-left--2rem">
                    <a href="#" class="link u-block u-padding--05rem">Action</a>
                    <a href="#" class="link u-block u-padding--05rem">Adult</a>
                    <a href="#" class="link u-block u-padding--05rem">Adventure</a>
                    <a href="#" class="link u-block u-padding--05rem">Comedy</a>
                    <a href="#" class="link u-block u-padding--05rem">Drama</a>
                    <a href="#" class="link u-block u-padding--05rem">Ecchi</a>
                    <a href="#" class="link u-block u-padding--05rem">Fantasy</a>
                    <a href="#" class="link u-block u-padding--05rem">Gender Bender</a>
                    <a href="#" class="link u-block u-padding--05rem">Horror</a>
                    <a href="#" class="link u-block u-padding--05rem">Incest</a>
                    <a href="#" class="link u-block u-padding--05rem">Isekai</a>
                    <a href="#" class="link u-block u-padding--05rem">Josei</a>
                    <a href="#" class="link u-block u-padding--05rem">Mature</a>
                    <a href="#" class="link u-block u-padding--05rem">Mecha</a>
                    <a href="#" class="link u-block u-padding--05rem">Mystery</a>
                    <a href="#" class="link u-block u-padding--05rem">School Life</a>
                    <a href="#" class="link u-block u-padding--05rem">Slice of Life</a>
                    <a href="#" class="link u-block u-padding--05rem">Supernatural</a>
                    <a href="#" class="link u-block u-padding--05rem">Shounen</a>
                    <a href="#" class="link u-block u-padding--05rem">Super Power</a>
                    <a href="#" class="link u-block u-padding--05rem">Seinen</a>
                    <a href="#" class="link u-block u-padding--05rem">Sports</a>
                    <a href="#" class="link u-block u-padding--05rem">Web Novel</a>
                    <a href="#" class="link u-block u-padding--05rem">Shoujo ai</a>
                    <a href="#" class="link u-block u-padding--05rem">Shoujo</a>
                    <a href="#" class="link u-block u-padding--05rem">Suspense</a>
                    <a href="#" class="link u-block u-padding--05rem">Romance</a>
                    <a href="#" class="link u-block u-padding--05rem">Tragedy</a>
                    <a href="#" class="link u-block u-padding--05rem">Shounen ai</a>
                </div>
            </section>
        </div>
        <div class="col-9">
        
        </div>
    </div>
</div>
<%@include file="/jsps/components/_footer.jsp" %>

</body>
</html>