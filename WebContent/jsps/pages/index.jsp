<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Trang chủ | 4TNOVEL</title>
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
	href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css"
	integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css"
	integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css"
	integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />


</head>
<body>
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="row u-margin-top--2rem">
		<div class="left-container col-lg-8">
		<c:if test="${not empty currentRead }">
		<c:set var="chap" value="${currentRead }"/>
		<c:set var="novel" value="${chap.novelOwner }"/>
		<section class="section section-current-read">
			<h2 class="section__title">Current read</h2>
			<hr>
				<div class="section__content">
					<%@include file="/jsps/components/_card-novel.jsp"%>
				</div>
		</section>
		</c:if>

		<section class="section section-threads">
			<h2 class="section__title">Threads</h2>
			<hr>
			<div class="section__content">
				<!-- //c:forEach var="thread" items="topFiveNewestThreads" -->
				<%@include file="/jsps/components/_index.section.content.thread.jsp"%>
			</div>
		</section>
		
		<c:if test="${ not empty newTrends }">
		<section class="section section-new-trending">
			<h2 class="section__title">New trending</h2>
			<hr>
			<div class="section__content">
				<!-- //c:forEach var="novel" items="newTrendingNovels" -->
				<ul class="vertical-menu--showcase">
					<c:forEach begin="1" end="5">
						<li class="menu-item u-margin-bottom--2rem"><%@ include
								file="/jsps/components/_card-novel.jsp"%>
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>
		</c:if>
		
		<c:if test="${not empty weeklyTop }">
		<section class="section section-weekly-top">
			<h2 class="section__title">Weekly top</h2>
			<hr>
			<div class="section__content">
				<!-- //c:forEach var="novel" items="weeklyTopNovels" -->
				<ul class="vertical-menu--showcase">
					<c:forEach begin="1" end="3">
						<li class="menu-item u-margin-bottom--2rem"><%@ include
								file="/jsps/components/_card-novel.jsp"%>
						</li>
					</c:forEach>
				</ul>
			</div>
		</section>
		</c:if>
		
		<section class="section section-lastest-update">
			<h2 class="section__title">Lastest update</h2>
			<hr>
			<div class="section__content" >
				<!-- c:forEach var="novel" items="lastestUpdateNovels" -->
				<ul id="lastestUpdateContent" class="vertical-menu--showcase">
					<c:forEach var="chap" items="${newChaps }">
					<c:set var="novel" value="${chap.novelOwner }"/>
						<li class="menu-item u-margin-bottom--2rem">
						<%@ include file="/jsps/components/_card-novel.jsp"%>
						</li>
					</c:forEach>
				</ul>
			</div>
			<div class="u-container-full--width u-align-center ">
				<button id="loadMoreLastestUpdateBtn" class="btn btn-info u-margin-bottom--2rem u-color-white u-margin-right--2rem">LOAD MORE
					FOR ME</button>
				<a href="see?author=all" class="btn btn-info u-margin-bottom--2rem u-color-white">SEE ALL</a>
			</div>
		</section>
	</div>
	<div class="right-container col-lg-4">
		<section class="section section-hot-comments">
			<h2 class="section__title">Hot comments</h2>
			<hr>
			<div class="section__content" id="hotCommentContent">
				<!-- //c:forEch var="comment" items="comments" -->
				<c:forEach var="comment" begin="1" end="5">
					<%@include
						file="/jsps/components/_index.section.content.hot-comments.comment.jsp"%>
				</c:forEach>
			</div>
			<div class="u-container-full--width u-align-center ">
				<button id="loadMoreHotComments" class="btn btn-info u-margin-bottom--2rem u-color-white">5 INTERESTING
					COMMENT</button>
			</div>
		</section>
	</div>
	</div>
	
	<%@include file="/jsps/components/_footer.jsp"%>
</body>
<script>
	
	document.addEventListener("DOMContentLoaded" , function(){
		var loadMoreLastestUpdateBtn = document.getElementById('loadMoreLastestUpdateBtn')
		var pageNumber = 1;
		
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function (){
			if (this.readyState == 4 && this.status == 200) {
			    var datas = JSON.parse(this.responseText);
				if(datas.length == 0 ){
					loadMoreLastestUpdateBtn.onclick = null;
					loadMoreLastestUpdateBtn.style.background = 'gray';
		        }
				else {
					pageNumber++
					// process data to card novel
					for(var i = 0 ; i < datas.length ; i ++){
					
					}
				}
			}
		}
		
		
		var url = location.origin.concat('${pageContext.request.contextPath}').concat('/ajax-lastest-update');
		loadMoreLastestUpdateBtn.onclick  = function(){
			var urlWithParam = url.concat('?page-number=').concat(pageNumber)
			alert('sending get method to : ' + urlWithParam)
			xhttp.open('GET', urlWithParam);
			xhttp.setRequestHeader("Content-Type", "text/html;charset=utf-8");
			xhttp.send("status=true");
		}
		
	})
</script>
</html>