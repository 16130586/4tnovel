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
	<div class="container">
	<div class="row u-margin-top--2rem">
		<div class="left-container col-lg-8">
		<c:if test="${not empty currentRead }">
		<c:set var="chap" value="${currentRead }"/>
		<c:set var="novel" value="${chap.novelOwner }"/>
		<section class="section section-current-read">
			<h2 class="section__title">Đang đọc</h2>
				<div class="section__content">
					<%@include file="/jsps/components/_card-novel.jsp"%>
				</div>
		</section>
		</c:if>
		
		<c:if test="${not empty topFiveNewestThreads }">
		<section class="section section-threads">
			<h2 class="section__title">Thảo luận</h2>
			<div class="section__content">
				<c:forEach var="thread" items="${topFiveNewestThreads }">
					<%@include file="/jsps/components/_index.section.content.thread.jsp"%>
				</c:forEach>
			</div>
		</section>
		</c:if>
		
		<section class="section section-lastest-update">
			<h2 class="section__title">Tác phẩm mới cập nhật</h2>
			<div class="section__content" >
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
				<button id="loadMoreLastestUpdateBtn" class="btn btn-info u-margin-bottom--2rem u-color-white u-margin-right--2rem">Xem thêm...
					</button>
				<a href="see2" class="btn btn-info u-margin-bottom--2rem u-color-white">Xem tất cả</a>
			</div>
		</section>
	</div>
	<div class="right-container col-lg-4">
		<c:if test="${ not empty newTrendingNovels }">
		<section class="section section-new-trending">
			<h2 class="section__title">Tác phẩm mới nổi</h2>
			<div class="section__content u-align-center" style="background-color: #f9f9f9">
				<c:set var="chap" value="${null }"/>
				<c:forEach var="novel" items="${newTrendingNovels }">
					<%@ include file="/jsps/components/_card-novel-img.jsp"%>
				</c:forEach>
			</div>
		</section>
		</c:if>
		
		<c:if test="${not empty monthlyTopNovels }">
		<section class="section section-weekly-top">
			<h2 class="section__title">Tác phẩm nổi bật trong tháng</h2>
			<div class="section__content u-align-center" style="background-color: #f9f9f9">
				<c:set var="chap" value="${null }"/>
				<c:forEach var="novel" items="${monthlyTopNovels }">
					<%@ include file="/jsps/components/_card-novel-img.jsp"%>
				</c:forEach>
			</div>
		</section>
		</c:if>
		
		<section class="section section-hot-comments">
			<h2 class="section__title">Bình luận nổi bật</h2>
			<div class="section__content" id="hotCommentContent">
				<c:forEach var="comment" items="${comments }">
					<%@include
						file="/jsps/components/_index.section.content.hot-comments.comment.jsp"%>
				</c:forEach>
			</div>
			<div class="u-container-full--width u-align-center ">
				<button id="loadMoreHotComments" class="btn btn-info u-margin-bottom--2rem u-color-white">Xem thêm...</button>
			</div>
		</section>
	</div>
	</div>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>
</body>
<script>
	
	document.addEventListener("DOMContentLoaded" , function(){
		var loadMoreLastestUpdateBtn = document.getElementById('loadMoreLastestUpdateBtn')
		var lastestUpdateContent = document.getElementById('lastestUpdateContent')
		var pageNumber = 1;
		
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function (){
			if(this.readyState == 4) {document.body.style.cursor='default'}
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
						var cardItem = document.createElement("li")
						cardItem.className="menu-item u-margin-bottom--2rem"
						cardItem.appendChild(buildNewCardNovel(datas[i]))
						lastestUpdateContent.appendChild(cardItem)
					}
				}
			}
		}
		
		
		var url = location.origin.concat('${pageContext.request.contextPath}').concat('/ajax-lastest-update');
		loadMoreLastestUpdateBtn.onclick  = function(){
			var urlWithParam = url.concat('?page-number=').concat(pageNumber)
			xhttp.open('GET', urlWithParam);
			xhttp.setRequestHeader("Content-Type", "text/html;charset=utf-8");
			xhttp.send("status=true");
			document.body.style.cursor='wait'
		}
		
	})
	function buildNewCardNovel(data){
		var cardNovel = document.createElement("div")
		var left = document.createElement("div")
		var right = document.createElement("div")
		var btm = document.createElement("div")
		
		cardNovel.className="row card-novel"
		
		left.className="col-md-4 novel-img--box u-align-center"
		right.className="col-md-8 novel-info--box"
		btm.className="row u-width--full"
		
		
		// left with clickable hero img
		var clickableImg = document.createElement("a")
		clickableImg.className="img-linking"
		var img = document.createElement("img")
		img.className="novel-hero"
		img.style.width="200px"
		img.src = location.origin.concat('${pageContext.request.contextPath}').concat('/resources/imgs?id=').concat(data.novelOwner.coverId)
		clickableImg.appendChild(img)
		left.appendChild(clickableImg)
		//end
		
		
		//start setup right infomation 
		var novelInfoBox = document.createElement("div")
		novelInfoBox.className="novel-short-info"
		
		var novelTitle = document.createElement("h2")
		novelTitle.className="u-text-overflow--hidden"
		
		var clickableNovelTitle = document.createElement("a")
		clickableNovelTitle.className="novel__title"
		clickableNovelTitle.innerHTML = data.novelOwner.name
		clickableNovelTitle.href = "detail?id=".concat(data.novelOwner.id)
		
		novelTitle.appendChild(clickableNovelTitle)
		
		var chapTitleBox = document.createElement("div")
		chapTitleBox.className="u-text-overflow--hidden"
		
		var clickableChapTitle = document.createElement("a")
		clickableChapTitle.className="link u-text-overflow--hidden"
		clickableChapTitle.style.color="#10b591"
		clickableChapTitle.href = "read?id=".concat(data.id)
		
		var chapTitle = document.createElement("h3")
		chapTitle.innerHTML=data.title
		
		clickableChapTitle.appendChild(chapTitle)
		chapTitleBox.appendChild(clickableChapTitle)
		
		var novelDescription = document.createElement("span")
		novelDescription.innerHTML= data.novelOwner.description.substring(0,400).concat('..')
		
		novelInfoBox.appendChild(novelTitle)
		novelInfoBox.appendChild(chapTitleBox)
		novelInfoBox.appendChild(novelDescription)
		
		//end
		
		//start setup view- like
		var likeViewRow = document.createElement("div")
		var ulViewLike = document.createElement("ul")
		
		likeViewRow.className="row u-width--full u-margin-top--1rem"
		ulViewLike.className="horizontal-menu--showcase text-centered"
		
		var liView = document.createElement("li")
		var liLike = document.createElement("li")
		
		liView.className="menu-item u-margin-right--2rem"
		liLike.className="menu-item"
		
		var iconView = document.createElement('i')
		var iconLike = document.createElement('i')
		
		iconView.className="fas fa-eye"
		iconLike.className="fas fa-thumbs-up"
		
		var numViews = document.createElement('p')
		var numLikes = document.createElement('p')
		
		numViews.className="u-inline-block"
		numLikes.className="u-inline-block"
		
		numViews.innerHTML = '&nbsp;'.concat(data.novelOwner.view)
		numLikes.innerHTML = '&nbsp;'.concat(data.novelOwner.like)
		
		liView.appendChild(iconView)
		liView.appendChild(numViews)
		
		liLike.appendChild(iconLike)
		liLike.appendChild(numLikes)
		
		ulViewLike.appendChild(liView)
		ulViewLike.appendChild(liLike)
		
		likeViewRow.append(ulViewLike)
		
		
		//end setup view-like
		
		right.appendChild(novelInfoBox)
		right.appendChild(likeViewRow)
		right.appendChild(btm)
		
		
		// start setup genre btm
		var genreBox = document.createElement("div")
		genreBox.className="novel__gender"
		genreBox.style.overflow = "hidden"
		
		var listGenre = document.createElement("ul")
		listGenre.className="horizontal-menu--showcase text-centered"
		
		var genreDatas = data.novelOwner.genres;
		var maxDisplay  = (genreDatas.length > 4 ? 4 : genreDatas.length);
		for(var i = 0 ; i < maxDisplay ; i ++){
			var li = document.createElement('li')
			li.className = "menu-item u-margin-right--2rem u-rounded--tag"
			
			var a = document.createElement('a')
			a.href = "see3?genre=".concat(genreDatas[i].value)
			a.className="btn btn-belike-a"
			a.innerHTML= genreDatas[i].displayName
			
			li.appendChild(a)
			listGenre.appendChild(li)
		}
		genreBox.appendChild(listGenre)
		btm.appendChild(genreBox)
		
		// end setup btm genre
		cardNovel.appendChild(left)
		cardNovel.appendChild(right)
		return cardNovel
		
	}
</script>
</html>