<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<div class="u-container-full--width message-card">
	<div class="u-container-full--width">
		<span class="message-content">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Reprehenderit doloribus sit distinctio inventore dolor ducimus natus excepturi repellendus esse. Optio tempora saepe quaerat nam ducimus earum officia nulla temporibus nostrum.</span>
	</div>
	<div class="u-container-full--width u-align-right">
		<p class="message-publish-time"><%=LocalDateTime.now() %></p>
	</div>
</div>