<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div style="border-bottom: 1px solid #00000029; padding: .7rem">
	<a href="${bookmark.url }" class="u-text-overflow--hidden" title="${bookmark.title } - ${bookmark.detail }" style="color: #444; display: block; font-size: 1.7rem">
		${bookmark.title }
		<br>
		<span style="font-size: 1.5rem; padding-left: 1.5rem; color: #10b591">${bookmark.detail }</span>
	</a>
</div>