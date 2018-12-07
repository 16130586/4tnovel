<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-bell u-3x"></i>
	</div>
	<div id="notification-content" class="dropdown-content--bottom u-25vw u-25vh u-transformX-85 u-auto-scroll-y custom-scroll-y u-border-full card--white">
		<c:forEach  var="message" items="${account.messages }">
			<%@ include file="/jsps/components/_header.user_functions.user-notification.message.jsp" %>
		</c:forEach>
	</div>
</div>
<c:if test="${not empty account }">
	<script>
		var wsUrl = '${initParam.notificationUrl}${account.id}'
		document.addEventListener("DOMContentLoaded", function (){
			var notificationBox = document.getElementById('notification-content')
			var olderMessages = notificationBox.childNodes
			if(!window.WebSocket) {alert("Sorry, your browser is too old too have good using experience!");
				var webSocket = new WebSocket(wsUrl);
				webSocket.onopen = function (){
				}
				webSocket.onmessage = function(event){
					alert(event.data)
					if(!olderMessages)
						olderMessages = notificationBox.childNodes;
					if(olderMessages.length <= 0 )
						notificationBox.appendChild(htmlToElement(event.data))
					else {
						notificationBox.insertBefore(htmlToElement(event.data), olderMessages[0])
					}
					
				}
				webSocket.onclose = function(){
				}
				webSocket.onerror = function(event){
				}
				function htmlToElement(html) {
				    var template = document.createElement('template');
				    html = html.trim(); // Never return a text node of whitespace as the result
				    template.innerHTML = html;
				    return template.content.firstChild;
				}
			}
		})
	
	</script>
</c:if>