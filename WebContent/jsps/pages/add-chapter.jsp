<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Thêm chương mới</title>
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
<body onload="openVol(document.getElementById('novel').selectedIndex)">
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="add">
			<div class="u-align-center">
				<p class="u-5x">Thêm chương</p>
				<p class="u-paragraph--sucessed">${sucessed}</p>
			</div>

			<form action="add-chapter" method="post">
				<table class="table u-2x u-centered u-width--95">
					<tr>
						<td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu
								đề: <span style="color: red">*</span>
						</label></td>
						<td><input name="title" class="u-width--full" type="text"
							style="padding: .5rem" required></td>
					</tr>
					<tr>
						<td></td>
						<td><input id="pin" type="checkbox"><label
							class="u-margin-left--2rem" for="pin">Ghim bài lên top</label></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Thuộc
								truyện: <span style="color: red">*</span>
						</label></td>
						<td><select id="novel" name="in-novel" style="padding: .5rem"
							onchange="openVol(this.selectedIndex)">
								<c:forEach var="novel" items="${account.ownNovels}">
									<option value="${novel.id }">${novel.name }</option>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Thuộc
								tập: <span style="color: red">*</span>
						</label></td>
						<td><c:forEach var="novel" items="${account.ownNovels}">
								<select name="in-vol" style="padding: .5rem">
									<c:forEach var="vol" items="${novel.vols}">
										<option value="${vol.id}">${vol.title}</option>
									</c:forEach>
								</select>
							</c:forEach></td>
					</tr>
					<tr>
						<td class="u-align-right"><label>Nội dung: <span
								style="color: red">*</span></label></td>
						<td><textarea name="content" class="u-width--full"
								style="padding: .5rem" rows="8" required></textarea></td>
					</tr>
				</table>
				<div class="u-align-center u-color-white">
					<button type="submit" class="btn btn-primary u-2x">Thêm</button>
					<a href="#" type="button" class="btn btn-danger u-2x">Quay lại</a>
				</div>
			</form>

		</div>

		<script>
			function openVol(x) {
				var vols = document.getElementsByName('in-vol');
				for (var i = 0; i < vols.length; i++) {
					if (i == x) {
						vols[i].style.display = 'block';
						vols[i].selectedIndex = 0;
						continue;
					}
					vols[i].style.display = 'none';
					vols[i].selectedIndex = -1;
				}
			}
		</script>
	</div>
</body>
</html>