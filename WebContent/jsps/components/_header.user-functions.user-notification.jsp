<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="dropdown-menu">
	<div class="dropdown-btn">
		<i class="fa fa-bell u-3x" onclick="showDropDownContent('notification-content')"></i>
	</div>
	<div id="notification-content" class="dropdown-content--bottom u-25vw u-25vh u-transformX-85 u-auto-scroll-y custom-scroll-y u-border-full card--white">
		<c:forEach  var="message" items="${account.messages}"  begin="0" end="4">
			<%@ include file="/jsps/components/_header.user_functions.user-notification.message.jsp" %>
		</c:forEach>
		<c:if test="${not empty account.messages}">
			<div class="u-align-center">
				<a id="loadMoreBtn" class="u-padding--05rem"onclick="loadingMoreNotifycationPage()" style="color: blue;cursor:pointer;">Xem thêm</a>
			</div>
		</c:if>
		<c:if test="${empty account.messages }">
			<div id="emptyMessage" class="u-align-center">
			<h2 >Thông báo rỗng!</h2>
			</div>
		</c:if>
		
	</div>
</div>
<c:if test="${not empty account }">
	<script>
	
		function htmlToElement(html) {
		    var template = document.createElement('template');
		    html = html.trim(); // Never return a text node of whitespace as the result
		    template.innerHTML = html;
		    return template.content.firstChild;
		}
		
		var baseWsUrl = "ws://".concat(location.hostname).concat(':').concat(location.port == undefined ? 80 : location.port).concat('${pageContext.request.contextPath}');
		var wsUrl = baseWsUrl.concat('/notify-system').concat('/${account.id}')
		document.addEventListener("DOMContentLoaded", function (){
			var emptyMessage = document.getElementById('emptyMessage')
			var notificationBox = document.getElementById('notification-content')
			var olderMessages = notificationBox.childNodes
			if(window.WebSocket) {
				var webSocket = new WebSocket(wsUrl);
				webSocket.onopen = function (){
				}
				webSocket.onmessage = function(event){
					alert("Bạn vừa có một thông báo mới!")
					if(emptyMessage){
						notificationBox.removeChild(emptyMessage)
						notificationBox.appendChild(htmlToElement(event.data))
						emptyMessage = null
						
					}
					else {
						notificationBox.insertBefore(htmlToElement(event.data), olderMessages[0])
					}
					
				}
				webSocket.onclose = function(){
				}
				webSocket.onerror = function(event){
				}
				
			}
			else {
				alert("Sorry, your browser is too old too have good using experience!");
			}
		})
		
	</script>
	<script>
		var notificationBox = document.getElementById('notification-content')
		var childsMessages = notificationBox.childNodes;
		var nextNotificationPage = 1;
		
		var url = location.origin.concat('${pageContext.request.contextPath}').concat('/ajax-notification');
		var loadMoreBtn = document.getElementById('loadMoreBtn')
		function loadingMoreNotifycationPage(){
			var urlWithParam  =  url.concat('?page-number=').concat(nextNotificationPage)
			var xhttp;
			  xhttp=new XMLHttpRequest();
			  xhttp.onreadystatechange = function() {
				if(this.readyState == 4) {document.body.style.cursor='default'}
			    if (this.readyState == 4 && this.status == 200) {
			    	nextNotificationPage++;
			        var datas = JSON.parse(this.responseText);
			        // out of messagge
			        if(datas.length == 0 ){
			        	loadMoreBtn.onclick = null;
			        	loadMoreBtn.style.color = 'gray';
			        	alert('Bạn đã xem hết tất cả thông báo!')
			        }
			        if(childsMessages.length > 0){
			        	var lastMessage = childsMessages[childsMessages.length - 2];
			        	for(var i = 0 ; i < datas.length ; i++ , lastMessage = childsMessages[childsMessages.length -2]){
			        		notificationBox.insertBefore(htmlToElement(datas[i].content), lastMessage)
			        	}
			        }
			    }
			  };
			  xhttp.open("GET", urlWithParam, true);
			  xhttp.setRequestHeader("Content-Type", "text/html;charset=utf-8");
			  xhttp.send("status=true");
			  document.body.style.cursor='wait'
		}
		
	</script>
</c:if>