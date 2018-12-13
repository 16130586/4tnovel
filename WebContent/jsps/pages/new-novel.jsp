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
        <h2 class="u-margin-bottom--2rem" style="padding-top: 2rem">DANH SÁCH TRUYỆN MỚI NHẤT</h2>
        <div class="u-align-center u-width--95 u-centered">
        	<c:forEach var="chap" items="${newChaps }">
       			<c:set var="novel" value="${chap.novelOwner }"/>
        		<%@ include file="/jsps/components/_card-novel-img.jsp" %>
        	</c:forEach>
        </div>
 		<div class="u-align-right u-color-white">
			<ul class="horizontal-menu--showcase">
				<c:if test="${currentPage != 1}">
					<li class="menu-item"><a
						href="${url}?page-number=${currentPage-1}" class="btn btn-dark">Previous</a></li>
				</c:if>
				<li class="menu-item"><a class="btn btn-dark"
					style="color: red;">${currentPage}</a></li>
				<c:if test="${currentPage < totalPage}">
					<c:forEach var="i" begin="1" end="2">
						<c:if test="${currentPage +  i  <= totalPage}">
							<li class="menu-item"><a
								href="${url}?page-number=${currentPage + i}"
								class="btn btn-dark">${currentPage + i}</a></li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${totalPage - currentPage > 2 }">
					<li class="menu-item"><a
						href="${url}?page-number=${totalPage}" class="btn btn-dark">${totalPage}</a></li>
				</c:if>
				<c:if
					test="${totalPage != currentPage && totalPage - currentPage > 4}">
					<li class="menu-item"><a
						href="${url}?page-number=${totalPage}" class="btn btn-dark">Last</a></li>
				</c:if>
			</ul>
		</div>
    </div>
	
	</div>
	<%@include file="/jsps/components/_footer.jsp" %>
	
</body>
</html>