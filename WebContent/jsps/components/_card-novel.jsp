<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div class="row card-novel">
	<div class="col-lg-4 novel-img--box u-align-center">
		<a href="#" class="img-linking"> <img style="width: 80%; height: 100%; padding: 1.5rem" class="novel-hero"
			src="${baseURL}/resources/imgs?id=${novel.coverId}">
		</a>
	</div>
	<div class="col-lg-8 novel-info--box">
		<div class="novel-short-info">
			<h2 class="u-text-overflow--hidden">
				<a class="novel__title" title="${novel.name }" href="detail?id=${novel.id }">${novel.name }</a>
			</h2>
			<div class="u-text-overflow--hidden">
				<a href="read?id=${chap.id }" title="${chap.title }" class="link u-text-overflow--hidden"
					style="color: #10b591">
					<h3>${chap.title }</h3>
				</a>
			</div>
			<c:set var="description" value="${novel.description }" />
			<c:if test="${description.length() < 335 }">
				<c:set var="length" value="${description.length() }" />
			</c:if>
			<c:if test="${description.length() > 335 }">
				<c:set var="length" value="335" />
			</c:if>
			<span class="novel__description">${description.substring(0, length)}...</span>
		</div>
		<div class="row u-width--full u-margin-top--1rem">
			<ul class="horizontal-menu--showcase text-centered">
				<li class="menu-item u-margin-right--2rem">
					<i class="fas fa-eye"></i>
					<p class="u-inline-block">${novel.view}</p>
				</li>
				<li class="menu-item u-margin-right--2rem">
					<i class="fas fa-thumbs-up"></i>
					<p class="u-inline-block">${novel.like}</p>
				</li>
			</ul>
		</div>
		<div class="row u-width--full">
			<div class="novel__gender">
				<ul class="horizontal-menu--showcase text-centered">
				<c:set var="description" value="${novel.description }" />
				<c:if test="${fn:length(novel.genres) <= 4 }">
					<c:set var="length" value="${fn:length(novel.genres) }" />
				</c:if>
				<c:if test="${fn:length(novel.genres) > 4 }">
					<c:set var="length" value="4" />
				</c:if>
					<c:forEach var="i" begin="0" end="${length-1 }">
						<li class="menu-item u-margin-right--2rem u-rounded--tag">
							<form action="search" method="post">
								<input type="hidden" name="genre" value="${novel.genres[i].value }">
								<button class="btn btn-belike-a">${novel.genres[i].getDisplayName()}</button>
							</form>
						</li>
					</c:forEach>
				</ul>
			</div>
	</div>
	</div>
	
</div>
