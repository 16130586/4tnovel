<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@
taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-bookmark u-3x" onclick="showDropDownContent('boorkmark-content')"></i>
	</div>
	<div id="boorkmark-content" style="background-color: white" class="dropdown-content--bottom u-25vw u-25vh u-transformX-75 u-auto-scroll-y custom-scroll-y u-border-full">
		<c:if test="${empty account.bookMarkFolders }">
			<div id="emptyMessage" class="u-align-center">
			<h2 >Chưa có book mark!</h2>
			</div>
		</c:if>
		<c:forEach var="bookmarkFolder" items="${account.bookMarkFolders }">
			<c:forEach  var="bookmark" items="${bookmarkFolder.bookMarks}"  begin="0" end="5">
				<%@ include file="/jsps/components/_header.user-functions.user-bookmark.bookmark-line.jsp" %>
			</c:forEach>
		</c:forEach>
	</div>
</div>