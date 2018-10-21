<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-bell 3x"></i>
	</div>
	<div class="dropdown-content--bottom u-25vw u-25vh bg-red transformX-75 auto-scroll-y">
		<c:forEach var="message" items="messages">
			<%@ include file="/jsps/components/_header.user_functions.user-notification.message.jsp" %>
		</c:forEach>
	</div>
</div>