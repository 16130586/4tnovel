<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<title>Danh sách truyện | 4TNOVEL</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
	<%@ include file="/jsps/components/_header.jsp"%>
	<div style="background: white;">
		<div class="u-width--80 u-centered">
			<h2 class="u-margin-bottom--2rem" style="padding-top: 2rem">DANH
				SÁCH TRUYỆN #${kind} #${statusString}</h2>
		</div>
		<div class="row u-width--80 u-centered">
			<div class="col-3">
				<section class="u-margin-bottom--2rem u-padding--05rem">
					<div class="sec-title"
						style="color: #61a4ad; text-transform: uppercase; font-weight: bold; font-size: 1.8rem;">
						<span style="border-bottom: 3px solid #10b591">Phân loại</span>
					</div>
					<div class="u-margin-left--2rem">
						<a href="see3?status=${status}&kind='TRANSLATE'&genre=${genre}"
							class="link u-block u-padding--05rem">Truyện dịch</a> <a
							href="see3?status=${status}&kind='COMPOSE'&genre=${genre}"
							class="link u-block u-padding--05rem">Truyện sáng tác</a> <a
							href="see3?status=${status}&kind='all'&genre=${genre}"
							class="link u-block u-padding--05rem">Tất cả</a>
					</div>
				</section>
				<section class="u-margin-bottom--2rem u-padding--05rem">
					<div class="sec-title"
						style="color: #61a4ad; text-transform: uppercase; font-weight: bold; font-size: 1.8rem;">
						<span style="border-bottom: 3px solid #10b591">Tình trạng</span>
					</div>
					<div class="u-margin-left--2rem">
						<a href="see3?status='1'&kind=${kind}&genre=${genre}"
							class="link u-block u-padding--05rem">Đang tiến hành</a> <a
							href="see3?status='2'&kind=${kind}&genre=${genre}"
							class="link u-block u-padding--05rem">Tạm ngưng</a> <a
							href="see3?status='0'&kind=${kind}&genre=${genre}"
							class="link u-block u-padding--05rem">Đã hoàn thành</a> <a
							href="see3?status='all'&kind=${kind}&genre=${genre}"
							class="link u-block u-padding--05rem">Tất cả</a>
					</div>
				</section>
				<section class="u-margin-bottom--2rem u-padding--05rem">
					<div class="sec-title"
						style="color: #61a4ad; text-transform: uppercase; font-weight: bold; font-size: 1.8rem;">
						<span style="border-bottom: 3px solid #10b591">Thể loại</span>
					</div>
					<div class="u-margin-left--2rem">
						<a href="see3" class="link u-block u-padding--05rem">All</a> <a
							href="see3?status='all'&kind='all'&genre='0'"
							class="link u-block u-padding--05rem">Action</a> <a
							href="see3?status='all'&kind='all'&genre='8'"
							class="link u-block u-padding--05rem">Adult</a> <a
							href="see3?status='all'&kind='all'&genre='15'"
							class="link u-block u-padding--05rem">Adventure</a> <a
							href="see3?status='all'&kind='all'&genre='22'"
							class="link u-block u-padding--05rem">Comedy</a> <a
							href="see3?status='all'&kind='all'&genre='1'"
							class="link u-block u-padding--05rem">Drama</a> <a
							href="see3?status='all'&kind='all'&genre='9'"
							class="link u-block u-padding--05rem">Ecchi</a> <a
							href="see3?status='all'&kind='all'&genre='16'"
							class="link u-block u-padding--05rem">Fantasy</a> <a
							href="see3?status='all'&kind='all'&genre='23'"
							class="link u-block u-padding--05rem">Gender Bender</a> <a
							href="see3?status='all'&kind='all'&genre='17'"
							class="link u-block u-padding--05rem">Horror</a> <a
							href="see3?status='all'&kind='all'&genre='10'"
							class="link u-block u-padding--05rem">Incest</a> <a
							href="see3?status='all'&kind='all'&genre='2'"
							class="link u-block u-padding--05rem">Isekai</a> <a
							href="see3?status='all'&kind='all'&genre='24'"
							class="link u-block u-padding--05rem">Josei</a> <a
							href="see3?status='all'&kind='all'&genre='3'"
							class="link u-block u-padding--05rem">Mature</a> <a
							href="see3?status='all'&kind='all'&genre='11'"
							class="link u-block u-padding--05rem">Mecha</a> <a
							href="see3?status='all'&kind='all'&genre='18'"
							class="link u-block u-padding--05rem">Mystery</a> <a
							href="see3?status='all'&kind='all'&genre='4'"
							class="link u-block u-padding--05rem">School Life</a> <a
							href="see3?status='all'&kind='all'&genre='5'"
							class="link u-block u-padding--05rem">Slice of Life</a> <a
							href="see3?status='all'&kind='all'&genre='14'"
							class="link u-block u-padding--05rem">Supernatural</a> <a
							href="see3?status='all'&kind='all'&genre='20'"
							class="link u-block u-padding--05rem">Shounen</a> <a
							href="see3?status='all'&kind='all'&genre='6'"
							class="link u-block u-padding--05rem">Super Power</a> <a
							href="see3?status='all'&kind='all'&genre='26'"
							class="link u-block u-padding--05rem">Seinen</a> <a
							href="see3?status='all'&kind='all'&genre='13'"
							class="link u-block u-padding--05rem">Sports</a> <a
							href="see3?status='all'&kind='all'&genre='12'"
							class="link u-block u-padding--05rem">Shoujo ai</a> <a
							href="see3?status='all'&kind='all'&genre='19'"
							class="link u-block u-padding--05rem">Shoujo</a> <a
							href="see3?status='all'&kind='all'&genre='21'"
							class="link u-block u-padding--05rem">Suspense</a> <a
							href="see3?status='all'&kind='all'&genre='25'"
							class="link u-block u-padding--05rem">Romance</a> <a
							href="see3?status='all'&kind='all'&genre='28'"
							class="link u-block u-padding--05rem">Tragedy</a> <a
							href="see3?status='all'&kind='all'&genre='27'"
							class="link u-block u-padding--05rem">Shounen ai</a>
					</div>
				</section>
			</div>
			<div class="col-9 u-align-center">
				<c:forEach var="novel" items="${novelList}">
					<%@ include file="/jsps/components/_card-novel-img.jsp"%>
				</c:forEach>
				<c:if test="${fn:length(novelList)>0}">
					<div class="u-align-right u-color-white">
						<ul class="horizontal-menu--showcase">
							<c:if test="${currentPage != 1}">
								<li class="menu-item"><a
									href="${url}page-number=${currentPage-1}" class="btn btn-dark">Previous</a></li>
							</c:if>
							<li class="menu-item"><a class="btn btn-dark"
								style="color: red;">${currentPage}</a></li>
							<c:if test="${currentPage < totalPage}">
								<c:forEach var="i" begin="1" end="2">
									<c:if test="${currentPage +  i  <= totalPage}">
										<li class="menu-item"><a
											href="${url}page-number=${currentPage + i}"
											class="btn btn-dark">${currentPage + i}</a></li>
									</c:if>
								</c:forEach>
							</c:if>
							<c:if test="${totalPage - currentPage > 2 }">
								<li class="menu-item"><a
									href="${url}page-number=${totalPage}" class="btn btn-dark">${totalPage}</a></li>
							</c:if>
							<c:if
								test="${totalPage != currentPage && totalPage - currentPage > 4}">
								<li class="menu-item"><a
									href="${url}page-number=${totalPage}" class="btn btn-dark">Last</a></li>
							</c:if>
						</ul>
					</div>
				</c:if>
				<c:if test="${fn:length(novelList)==0}">
					<h3>Chúng tôi đang cập nhật thêm!</h3>
				</c:if>
			</div>
		</div>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>

</body>
</html>