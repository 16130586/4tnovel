<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstrap reboot
	bootstrap grid
	custom css
-->
<link href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese" rel="stylesheet"> 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css" integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css" integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css" integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I" crossorigin="anonymous"><link rel="stylesheet" href="resources/vendors/css/bootstrap-reboot.css" />	
<link rel="stylesheet" href="resources/vendors/css/bootstrap-grid.css" />	
<link rel="stylesheet" href="resources/local/css/style.css"/>
<link style="text/scss" rel="stylesheet" href="resources/local/scss/components/_card-novel.scss">

</head>
<body>
	<%@ include file="/jsps/components/_header.jsp" %>
	<section class="section section-current-read">
		<h2 class="section__title">Current read</h2>
		<hr>
		<div class="section__content">
		<!-- lastestReadNovel -->
			<%@include file="/jsps/components/_card-novel.jsp" %>
		</div>
	</section>
	<section class="section section-threads">
		<h2 class="section__title">Threads</h2>
		<hr>
		<div class="section__content">
		<!-- //c:forEach var="thread" items="topFiveNewestThreads" -->
			<%@include file="/jsps/components/_index.section.content.thread.jsp"%>
		</div>
	</section>
	<section class="section section-new-trending">
		<h2 class="section__title">New trending</h2>
		<hr>
		<div class="section__content">
		<!-- //c:forEach var="novel" items="newTrendingNovels" -->
			<c:forEach begin="1" end ="5">
				<%@ include file="/jsps/components/_card-novel.jsp" %>
			</c:forEach>
		</div>
	</section>
	<section class="section section-weekly-top">
		<h2 class="section__title">Weekly top</h2>
		<hr>
		<div class="section__content">
		<!-- //c:forEach var="novel" items="weeklyTopNovels" -->
			<c:forEach begin="1" end ="3">
				<%@ include file="/jsps/components/_card-novel.jsp" %>
			</c:forEach>
		</div>
	</section>
	<section class="section section-lastest-update">
		<h2 class="section__title">Lastest update</h2>
		<hr>
		<div class="section__content" id="lastestUpdateContent">
		<!-- c:forEach var="novel" items="lastestUpdateNovels" -->
			<c:forEach begin="1" end ="3">
				<%@ include file="/jsps/components/_card-novel.jsp" %>
			</c:forEach>
		</div>
		<div class="u-container-full--width">
			<button id="loadMoreLastestUpdate" class="btn u-centered">LOAD MORE FOR ME</button>
		</div>
	</section>
	<section class="section section-hot-comments">
		<h2 class="section__title">Hot comments</h2>
		<hr>
		<div class="section__content" id="hotCommentContent">
		<!-- //c:forEch var="comment" items="comments" -->
			<c:forEach var="comment" begin="1" end ="5">
				<%@include file="/jsps/components/_index.section.content.hot-comments.comment.jsp" %>
			</c:forEach>
		</div>
		<div class="u-container-full--width">
			<button id="loadMoreHotComments" class="btn u-centered">5 INTERESTING COMMENT</button>
		</div>
	</section>
	<%@include file="/jsps/components/_footer.jsp" %>
</body>
</html>