<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Đọc ${chap.title} - ${chap.novelOwner.name }</title>
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
<body style="background-image: none;">
	<%@ include file="/jsps/components/_header.jsp"%>
	<div id="onTop"></div>
	<div class="chap">
		<div class="chap__header u-align-center u-margin-bottom--1rem">
			<h1>${chap.novelOwner.name }</h1>
			<h3>${chap.title }</h3>
			<p>${chap.dateUp}</p>
		</div>

		<div class="chap__body">
		<c:set var="previousChap" scope="request" value="${chap.volOwner.getPreviousChap(chap.id)}"/>
		<c:set var="nextChap" scope="request" value="${chap.volOwner.getNextChap(chap.id)}"/>
			<div class="u-align-center u-color-white">
				<c:if test="${not empty previousChap}">
					<a href="read?id=${previousChap.id}"
						class="btn btn-success u-margin-right--1rem"> << </a>
				</c:if>
				<c:if test="${empty previousChap }">
						<a href="/" onclick="return false;"
						class="btn btn-success u-margin-right--1rem"> << </a>
				</c:if>
				<a href="detail?id=${chap.novelOwner.id}"
					class="btn btn-secondary u-margin-right--1rem"><i
					class="fas fa-list-ol"></i></a>

				<c:if test="${not empty nextChap}">
					<a href="read?id=${nextChap.id}"
						class="btn btn-success u-margin-right--1rem"> >> </a>
				</c:if>
				<c:if test="${empty nextChap }">
						<a href="/" onclick="return false;"  
						class="btn btn-success u-margin-right--1rem"> >> </a>
				</c:if>
			</div>
			<div class="chap__body__content" onclick="showHideSetup()">
				<div>
					<h3>${chap.title }</h3>
				</div>
				<div>
					<span>${chap.content }</span>
				</div>
			<div class="u-align-center u-color-white">
				<c:if test="${not empty previousChap}">
					<a href="read?id=${previousChap.id}"
						class="btn btn-success u-margin-right--1rem"> << </a>
				</c:if>
				<c:if test="${empty previousChap }">
						<a href="/" onclick="return false;"
						class="btn btn-success u-margin-right--1rem"> << </a>
				</c:if>
				<a href="detail?id=${chap.novelOwner.id}"
					class="btn btn-secondary u-margin-right--1rem"><i
					class="fas fa-list-ol"></i></a>

				<c:if test="${not empty nextChap}">
					<a href="read?id=${nextChap.id}"
						class="btn btn-success u-margin-right--1rem"> >> </a>
				</c:if>
				<c:if test="${empty nextChap }">
						<a href="/" onclick="return false;"  
						class="btn btn-success u-margin-right--1rem"> >> </a>
				</c:if>
			</div>
		</div>

		<div class="chap__comment"></div>

		<div id="set-up" class="chap__setup u-centered u-margin-right--1rem u-color-white">
			<ul>
				<li><a href="#onTop" class="btn btn-secondary"><i
						class="fas fa-arrow-up"></i></a></li>
				<li><button id="btnFontChoice" href="#"
						class="btn btn-secondary">
						<i class="fas fa-font"></i>
					</button></li>
				<li><button id="btnColorChoice" href="#"
						class="btn btn-secondary">
						<i class="fas fa-palette"></i>
					</button></li>
				<li><a href="#onBottom" class="btn btn-secondary"><i
						class="fas fa-arrow-down"></i></a></li>
			</ul>
		</div>

	</div>
	</div>
	<div id="onBottom"></div>
	<%@include file="/jsps/components/_footer.jsp"%>
	<script type="text/javascript">
		function showHideSetup() {
			var setUp = document.getElementById('set-up');
			if (setUp.style.display == 'block')
				setUp.style.display = 'none';
			else
				setUp.style.display = 'block';
		}
	</script>
</body>
</html>