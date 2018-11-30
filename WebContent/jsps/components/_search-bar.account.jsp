<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="search-account u-centered">
	<form action="search-account" method="post">
		<div class="search-bar u-centered u-align-center u-2x row"
			style="padding: 1rem">
			<div class="col-sm-2">
				<select name="type" class="input">
					<option value="user-name">Tài khoản</option>
					<option value="display-name">Biệt hiệu</option>
					<option value="email">Mail</option>
				</select>
			</div>
			<div class="col-sm-8">
				<input id="input" class="input u-width--full u-2x" type="text"
					name="input" required>
			</div>
			<div class="col-sm-2">
				<button type="submit" class="btn btn-primary u-2x">Tìm kiếm</button>
				<br>
			</div>
		</div>
	</form>
	<hr>
	<div class="u-width--80 u-centered u-2x">
		<table class="table table-hover table-dark">
			<!-- c:for de do du lieu ra -->
			<c:if test="${ not empty searchResultAccount}">
				<tr class="row">
					<td class="col-md-3">Username</td>
					<td class="col-md-3">Nickname</td>
					<td class="col-md-3">Email</td>
					<td class="col-md-3">Action</td>
				</tr>
				<tr class="row">
					<td class="col-md-3"> ${searchResultAccount.userName}</td>
					<td class="col-md-3"> ${searchResultAccount.displayedName}</td>
					<td class="col-md-3"> ${searchResultAccount.gmail}</td>
					<td class="col-md-3">
						<input name="idAccount" id="accountResultId" onclick="mappingId()" type="radio" value="${searchResultAccount.id}"> 
						<label for="account1">Chọn</label>
					</td>
				</tr>
			</c:if>
		</table>
	</div>
</div>
<!-- 1 div de hien ket qua + 1 doan js-->
