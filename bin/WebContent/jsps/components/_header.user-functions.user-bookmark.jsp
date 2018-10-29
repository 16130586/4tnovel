<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-heart u-3x"></i>
	</div>
	<div id="boorkmark-content"class="dropdown-content--bottom u-25vw u-25vh u-transformX-75 u-auto-scroll-y custom-scroll-y u-border-full card--white">
		<c:forEach begin="1" end="10">
			<%@ include file="/jsps/components/_header.user-functions.user-bookmark.bookmark-line.jsp" %>
		</c:forEach>
	</div>
</div>