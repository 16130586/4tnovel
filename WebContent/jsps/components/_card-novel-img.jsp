<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="req" value="${pageContext.request}" />
<c:set var="baseURL" value="${req.scheme}://${req.serverName}:${req.serverPort}${req.contextPath}" />
<div class="card">
	<a class="card-img" href="read?id=${chap.id }"
		style="background: linear-gradient(to bottom, rgba(133, 133, 133, 0), rgba(0, 0, 0, 0.7)), url(${baseURL}/resources/imgs?id=${novel.coverId})"></a>
	<div class="card-detail">
		<h3 class="u-text-overflow--hidden" style="margin-bottom: .5rem">
			<a href="detail?id=${novel.id }" class="link">${novel.name }</a>
		</h3>
		<h4 class="u-text-overflow--hidden" style="color: #43f2cc">
			<a href="read?id=${chap.id }" class="link">${chap.title }</a>
		</h4>
	</div>
</div>