<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header id="header--on-top" class="header--on-top">
	<%@ include file="/jsps/components/_header.navigation-mobile.jsp"%>
	<%@ include file="/jsps/components/_header.navigation-desktop.jsp"%>
	<c:if test="${not empty account }">
		<%@ include file="/jsps/components/_header.user-functions.jsp"%>
	</c:if>
</header>
