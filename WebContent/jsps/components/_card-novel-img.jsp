<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div class="card">
	<c:if test="${not empty chap }">
		<a class="card-img" href="read?id=${chap.id }"
		style="background: url(${baseURL}/resources/imgs?id=${novel.coverId})"></a>
	</c:if>
	<c:if test="${empty chap }">
		<a class="card-img" href="detail?id=${novel.id }"
		style="background: url(${baseURL}/resources/imgs?id=${novel.coverId})"></a>
	</c:if>
	<div class="card-detail">
		<h4 class="u-text-overflow--hidden" style="margin-bottom: .5rem">
			<a href="detail?id=${novel.id }" title="${novel.name }" class="link">${novel.name }</a>
		</h4>
		<h5 class="u-text-overflow--hidden" style="color: #43f2cc">
			<a href="read?id=${chap.id }" title="${chap.title }" class="link">${chap.title }</a>
		</h5>
	</div>
</div>