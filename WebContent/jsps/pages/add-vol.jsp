<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Thêm tập mới</title>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
<link
	href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese"
	rel="stylesheet">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="add">
			<div class="u-align-center">
				<p class="u-5x">Thêm tập</p>
				<p class="u-paragraph--sucessed">${sucessed}</p>
			</div>

			<form action="add-vol" method="post">
				<table class="table u-2x u-centered u-width--95">
					<tr>
						<td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu
								đề: <span style="color: red">*</span>
						</label></td>
						<td><input name="title" class="u-width--full" type="text"
							style="padding: .5rem" required></td>
					</tr>
					<c:if test="${not empty titleError }">
						<tr class="u-align-center">
							<td colspan="3"><p class="u-paragraph--failed">${titleError}</p></td>
						</tr>

					</c:if>

					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Thuộc
								truyện: <span style="color: red">*</span>
						</label></td>
						<td><select name="in-novel" style="padding: .5rem">
								<!-- option value="novelOwnerID-Integer" -->
								<c:forEach var="novel" items="${account.ownNovels}">
									<option value="${novel.id}">${novel.name}</option>
								</c:forEach>

						</select> <c:if test="${empty account.ownNovels}">
								<a class="u-paragraph--sucessed u-margin-left--2rem"
									href="add?type=add-novel" target="_blank">Create new novel
									here!</a>
							</c:if></td>
					</tr>
					<tr>
						<td class="u-align-right"><label>Tóm tắt:</label></td>
						<td><textarea name="description" class="u-width--full"
								style="padding: .5rem" rows="7"></textarea></td>
					</tr>

				</table>
				<div class="u-align-center u-color-white">
					<button type="submit" class="btn btn-primary u-2x">Thêm</button>
					<a href="manage" type="button" class="btn btn-danger u-2x">Hủy</a>
				</div>

			</form>

		</div>
	</div>
</body>
</html>