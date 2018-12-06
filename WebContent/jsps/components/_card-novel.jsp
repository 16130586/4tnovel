<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row card-novel">
	<div class="col-lg-4 novel-img--box u-align-center">
		<a href="#" class="img-linking"> <img style="width: 80%; height: 100%; padding: 1.5rem" class="novel-hero"
			src="data:image/*;base64, ${novel.coverImg}">
		</a>
	</div>
	<div class="col-lg-8 novel-info--box">
		<div class="novel-short-info">
			<h2 class="u-align-center u-text-overflow--hidden">
				<a class="novel__title" href="detail?id=${novel.id }">${novel.name }</a>
			</h2>
			<div class="u-align-center u-text-overflow--hidden">
				<a href="read?id=${chap.id }" class="link u-text-overflow--hidden"
					style="color: #10b591">
					<h3>${chap.title }</h3>
				</a>
			</div>
			<c:set var="description" value="${novel.description }" />
			<c:if test="${description.length() < 400 }">
				<c:set var="length" value="${description.length() }" />
			</c:if>
			<c:if test="${description.length() > 400 }">
				<c:set var="length" value="400" />
			</c:if>
			<span class="novel__description">${description.substring(0, length)}...</span>
		</div>
	</div>
	<div class="row u-width--full">
		<div class="col-lg-12 u-align-center novel__gender"
			style="white-space: nowrap; overflow: hidden">
			<ul class="horizontal-menu--showcase text-centered">
				<c:forEach var="genre" items="${novel.genres }">
					<li class="menu-item u-margin-right--2rem">
						<form action="search" method="post">
							<input type="hidden" name="genre" value="${genre.value }">
							<button class="btn btn-belike-a">${genre.getDisplayName()}</button>
						</form>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</div>
