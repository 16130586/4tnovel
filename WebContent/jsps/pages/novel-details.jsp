<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>${novel.name }</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<c:set var="req" value="${pageContext.request}" />
	<c:set var="baseURL"
		value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="detail">
		<div class="detail__top u-centered">
			<div class="row">
				<div class="col-md-3 detail__top-cover">
					<img class="img-cover"
						src="${baseURL}/resources/imgs?id=${novel.coverId }"
						alt="đoán xem">
				</div>
				<div class="col-md-9 detail__top-info">
					<div>
						<h2 class="u-color-blue">
							<a class="link">${novel.name}!</a>
						</h2>
						<div>
							<span class="u-block"> Người đăng : <a
								class="link u-color-blue" href="#">${novel.owner.userName}</a></span><span
								class="u-block"> Tình trạng : ${novel.status.text}</span> <span
								class="u-block"> Ngày đăng : <fmt:formatDate type="both"
									dateStyle="short" timeStyle="short" value="${novel.dateUp}" /></span>
							<div class="u-block u-margin-top--1rem">
								<ul class="horizontal-menu--showcase">
									<span>Thể loại : </span>
									<c:forEach var="genre" items="${novel.genres}">
										<li class="menu-item">
											<a class="novel__title" title="${novel.name }" href="detail?id=${novel.id }">${novel.name }</a>
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					<div>
						<c:if
							test="${not empty novel.vols && not empty novel.vols.get(0) && not empty novel.vols.get(0).chaps }">
							<a href="read?id=${novel.vols.get(0).chaps.get(0).id }"
								class="btn btn-info u-color-white u-padding--05rem u-margin-top--1rem">Đọc
								từ đầu</a>
						</c:if>
						<c:if
							test="${not empty account && not account.isFollowNovel(novel.id)}">
							<form action="follow" method="post"
								style="display: inline-block;">
								<input name="action" value="subcribe" type="hidden"> <input
									name="targetId" value="${novel.id }" type="hidden"> <input
									name="stream" value="novel" type="hidden">
								<button
									class="btn btn-danger u-color-white u-padding--05rem u-margin-top--1rem">+Theo
									dõi</button>
							</form>
						</c:if>
						<c:if
							test="${not empty account && account.isFollowNovel(novel.id)}">
							<form action="follow" method="post"
								style="display: inline-block;">
								<input name="action" value="unSubcribe" type="hidden"> <input
									name="targetId" value="${novel.id }" type="hidden"> <input
									name="stream" value="novel" type="hidden">
								<button
									class="btn btn-danger u-color-white u-padding--05rem u-margin-top--1rem">-Bỏ
									theo dõi</button>
							</form>
						</c:if>
						<c:if test="${empty account }">
							<a href="login"
								class="btn btn-danger u-color-white u-padding--05rem u-margin-top--1rem">+Theo
								dõi</a>
						</c:if>
						<c:if test="${not empty account and !isLikeThisNovel}">
							<button id="btnLike" data-event="like"
								onClick='sendLikeEventToServer("${novel.id}")'
								class="btn btn-info u-color-white u-padding--05rem u-margin-top--1rem">
								<i id="iconLike" class="fas fa-thumbs-up"></i>
								<p id="textLike" class="u-inline-block u-no--margin">${novel.like}</p>
							</button>
						</c:if>
						<c:if test="${not empty account and isLikeThisNovel }">
							<button id="btnLike" data-event="unlike"
								onClick='sendLikeEventToServer("${novel.id}")'
								class="btn btn-info u-color-white u-padding--05rem u-margin-top--1rem">
								<i id="iconLike" class="fas fa-thumbs-up"
									style="color: #0e39dc;"></i>
								<p id="textLike" class="u-inline-block u-no--margin">${novel.like}</p>
							</button>
						</c:if>
					</div>
					<div class="u-margin-top--1rem u-padding-left--1rem"
						id="description"
						style="max-height: 150px; overflow: hidden; border-left: 4px solid rgb(16, 181, 145)">
						<c:set var="newLine" value="\n" />
						<c:set var="paragraphs"
							value="${novel.description.split(newLine) }" />
						<c:forEach var="paragraph" items="${paragraphs }">
							<p>${paragraph }</p>
						</c:forEach>
					</div>
					<div id="btn-seemore-hide" class="u-align-right u-width--full"
						style="margin: 1rem">
						<button onclick="seeMoreOrHide(this)"
							class="btn u-color-white u-padding--05rem"
							style="background-color: #3f8296; width: 110px">
							<i class="fa fa-angle-double-down"></i> Xem thêm
						</button>
					</div>
				</div>
			</div>
		</div>
		<div class="detail__body u-centered">
			<section class="tab u-block">
				<div class="tab-btn" onclick="showOrHide('note')">
					<span style="border-bottom: 3px solid #10b591">Chú thích</span>
				</div>
				<div id="note" class="tab-content" style="display: block">
					<span>Adding later!</span>
				</div>
			</section>
			<c:forEach var="vol" items="${novel.vols}">
				<section class="tab u-block">
					<div class="tab-btn" onclick="showOrHide('${vol.id}')">
						<span style="border-bottom: 3px solid #10b591">${vol.title}</span>
					</div>
					<div id="${vol.id }" class="tab-content" style="display: block">
						<c:forEach var="chap" items="${vol.chaps}">
							<a class="link u-block u-text-overflow--hidden"
								href="read?id=${chap.id}">${chap.title}</a>
						</c:forEach>
					</div>
				</section>
			</c:forEach>
		</div>
	</div>
	<script>
		var btnLike = null
		window.onload = function() {
			btnLike = document.getElementById('btnLike')
			var des = document.getElementById('description');
			if (des.scrollHeight <= des.clientHeight)
				removeElement('btn-seemore-hide');
		}

		function removeElement(elementId) {
			// Removes an element from the document
			var element = document.getElementById(elementId);
			element.parentNode.removeChild(element);
		}

		function seeMoreOrHide(x) {
			var des = document.getElementById('description');
			if (des.style.maxHeight == "150px") {
				setBtnSeeMoreOrHide(true, x);
				des.style.maxHeight = null;
			} else {
				setBtnSeeMoreOrHide(false, x);
				des.style.maxHeight = "150px";
			}
		}

		function setBtnSeeMoreOrHide(isMore, x) {
			if (isMore) {
				x.innerHTML = "<i class='fa fa-angle-double-up'></i> Ẩn đi";
			} else {
				x.innerHTML = "<i class='fa fa-angle-double-down'></i> Xem thêm";
			}
		}

		function showOrHide(id) {
			var x = document.getElementById(id);
			if (x.style.display == 'none') {
				x.style.display = 'block';
			} else {
				x.style.display = 'none';
			}
		}
		var likeAjaxUrl = location.origin.concat(
				'${pageContext.request.contextPath}')
				.concat('/ajax-like-novel');

		function sendLikeEventToServer(novelId) {

			if (!btnLike)
				btnLike = document.getElementById('btnLike')
			var eventType = btnLike.getAttribute("data-event")
			var textLike = document.getElementById('textLike')
			var iconLike = document.getElementById('iconLike')
			var xhttp = new XMLHttpRequest();
			var urlWithParam = likeAjaxUrl.concat('?id=').concat(novelId)
			if (eventType === 'like') {
				urlWithParam = urlWithParam.concat('&action=like')
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4) {
						document.body.style.cursor = 'default'
					}
					if (this.readyState == 4 && this.status == 200) {
						var newestLike = this.responseText;
						btnLike.setAttribute('data-event', 'unlike')
						textLike.innerHTML = newestLike
						iconLike.style.color = '#0e39dc'
						return false;
					}
				}
			}

			if (eventType === 'unlike') {
				urlWithParam = urlWithParam.concat('&action=unlike')
				xhttp.onreadystatechange = function() {
					if (this.readyState == 4) {
						document.body.style.cursor = 'default'
					}
					if (this.readyState == 4 && this.status == 200) {
						var newestLike = this.responseText;
						btnLike.setAttribute('data-event', 'like')
						textLike.innerHTML = newestLike
						iconLike.style.color = '#F1F1F1'
						return false;
					}
				}
			}

			xhttp.open('POST', urlWithParam, true)
			xhttp.send("status=true")
			document.body.style.cursor = 'wait'
		}
	</script>

	<%@include file="/jsps/components/_footer.jsp"%>
</body>
</html>