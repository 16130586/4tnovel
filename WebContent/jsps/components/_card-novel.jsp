<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div class="row card-novel">
	<div class="col-md-4 novel-img--box">
		<div style="width: 190px; height: 280px; margin: auto">
			<a href="detail?id=${novel.id }" class="card-img" style="background: url(${baseURL}/resources/imgs?id=${novel.coverId})">
			</a>
		</div>
	</div>
	<div class="col-md-8 novel-info--box">
		<div class="novel-short-info">
			<h2 class="u-text-overflow--hidden">
				<a class="novel__title" title="${novel.name }" href="detail?id=${novel.id }">${novel.name }</a>
			</h2>
			<div>
				<h3 class=" u-text-overflow--hidden">
					<a href="read?id=${chap.id }" title="${chap.title }" class="link"
						style="color: #10b591">
						${chap.title }
					</a>
				</h3>
			</div>
			<c:set var="description" value="${novel.description }" />
			<c:if test="${description.length() < 240 }">
				<c:set var="length" value="${description.length() }" />
			</c:if>
			<c:if test="${description.length() > 240 }">
				<c:set var="length" value="240" />
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
				<c:if test="${fn:length(novel.genres) <= 3 }">
					<c:set var="length" value="${fn:length(novel.genres) }" />
				</c:if>
				<c:if test="${fn:length(novel.genres) > 3 }">
					<c:set var="length" value="3" />
				</c:if>
					<c:forEach var="i" begin="0" end="${length-1 }">
						<li class="menu-item u-margin-right--2rem u-rounded--tag">
							<a class="btn btn-belike-a" href="see3?genre=${novel.genres[i].value }">${novel.genres[i].getDisplayName()}</a>
						</li>
					</c:forEach>
				</ul>
			</div>
	</div>
	</div>
	
</div>
