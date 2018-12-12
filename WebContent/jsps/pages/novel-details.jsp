<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>${novel.name }</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese" rel="stylesheet"> 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css" integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css" integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css" integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I" crossorigin="anonymous">	
<link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css"/>
</head>
<body>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
	<%@ include file="/jsps/components/_header.jsp" %>
<div class="detail">
        <div class="detail__top u-centered">
            <div class="row">
                <div class="col-md-3 detail__top-cover">
                    <img class="img-cover" src="${baseURL}/resources/imgs?id=${novel.coverId }" alt="đoán xem">
                </div>
                <div class="col-md-9 detail__top-info">
                    <div>
                        <h2 class="u-color-blue">
                            <a class="link">${novel.name}!</a>
                        </h2>
                        <div>
                            <span class="u-block"> Người đăng: <a class="link u-color-blue" href="#">${novel.owner.userName}</a></span> 
                            <div class="u-block u-margin-top--1rem">  
								<ul class="horizontal-menu--showcase">
									<c:forEach var="genre" items="${novel.genres}">
										<li class="menu-item">
										<form action="search" method="post">
											<input type="hidden" name="genre" value="${genre.getValue()}">
											<button class="btn btn-belike-a">${genre.getDisplayName()}</button>
										</form>
										</li>
									</c:forEach>
								</ul> 
							</div>                   
						</div>
                    </div>
                    <div class="u-margin-top--1rem">
                    	<c:if test="${not empty novel.vols && not empty novel.vols.get(0) && not empty novel.vols.get(0).chaps }">
                       	 	<a href="read?id=${novel.vols.get(0).chaps.get(0).id }" class="btn btn-info u-color-white">Đọc từ đầu</a>
                        </c:if>
                        <c:if test="${not empty account && not account.isFollowNovel(novel.id)}">
                        	<form action="follow" method="post" style="display:inline-block;">
                        		<input name="action" value="subcribe" type="hidden">
                        		<input name="targetId" value="${novel.id }" type="hidden">
                        		<input name="stream" value="novel" type="hidden">
                        		<button class="btn btn-danger u-color-white">+Theo dõi</button>
                        	</form>
                        </c:if>
                        <c:if test="${not empty account && account.isFollowNovel(novel.id)}">
                        	<form action="follow" method="post" style="display:inline-block;">
                        		<input name="action" value="unSubcribe" type="hidden">
                        		<input name="targetId" value="${novel.id }" type="hidden">
                        		<input name="stream" value="novel" type="hidden">
                        		<button class="btn btn-danger u-color-white">-Bỏ theo dõi</button>
                        	</form>
                        </c:if>
                        <c:if test="${empty account }">
                        	<a href="login" class="btn btn-danger u-color-white">+Theo dõi</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail__body u-centered">
            <section class="tab u-block">
                <div>
                    <button class="btn tab-btn u-2x" onclick="showOrHide('introduce')">Giới thiệu</button>
                </div>
                <div id="introduce" class="tab-content" style="display: block">
					<c:set var="newLine" value="\n"/>
					<c:set var="paragraphs" value="${novel.description.split(newLine) }" />
					<c:forEach var="paragraph" items="${paragraphs }">
						<p>${paragraph }</p>
					</c:forEach>
                </div>
            </section>
            <section class="tab u-block">
                <button class="btn tab-btn u-2x" onclick="showOrHide('note')">Chú thích</button>
                <div id="note" class="tab-content" style="display: none">
                	<span>Adding later!</span>
                </div>
            </section>
            <c:forEach var="vol" items="${novel.vols}">
            	 <section class="tab u-block">
               	 	<button class="btn tab-btn u-2x" onclick="showOrHide('${vol.id}')">${vol.title}</button>
	                <div id="${vol.id }" class="tab-content" style="display: none">
	                	<c:forEach var="chap" items="${vol.chaps}">
	                    	<a class="link u-block" href="read?id=${chap.id}">${chap.title}</a>
	                    </c:forEach>
	                </div>
            	</section>
            </c:forEach>
        </div>
    </div>
    <script>
        function showOrHide(id) {
            var x = document.getElementById(id);
            if (x.style.display =='none') {
                x.style.display = 'block';
            } else {
                x.style.display = 'none';
            }
        }
    </script>
    
	<%@include file="/jsps/components/_footer.jsp" %>
</body>
</html>