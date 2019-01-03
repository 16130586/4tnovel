<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Sửa</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.5, user-scalable=yes">
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
				<p class="u-4x">Sửa truyện</p>
				<p class="u-paragraph--sucessed">${sucessed}</p>
			</div>
			<form action="fix-novel" method="POST"
				onsubmit="return checkCheckBoxes();" enctype="multipart/form-data">
				<table class="table u-2x u-centered u-width--95">
					<tr>
						<td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu
								đề: <span style="color: red">*</span>
						</label></td>
						<td><input name="title" class="u-width--full" type="text"
							style="padding: .5rem" required value="${fixingNovel.name}"></td>
					</tr>
					<tr>
						<td colspan="3" class="u-align-center"><p
								class="u-paragraph--failed">${titleError}</p></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Loại
								truyện: <span style="color: red">*</span>
						</label></td>
						<td><select name="type-novel" style="padding: .5rem">
								<option value="compose">Sáng tác</option>
								<option value="translate">Truyện dịch</option>
						</select></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Nhóm
								dịch: <span style="color: red">*</span>
						</label></td>

						<td><select name="group" style="padding: .5rem">
								<!-- option value="nhom dich id" -->
								<option value="${fixingNovel.group.getId()}">${fixingNovel.group.getName()}</option>
								<c:forEach var="group" items="${account.getJoinGroup() }">
								<c:if test="${group.getName() ne fixingNovel.group.getName() }">
									<option value="${group.getId()}">${group.getName()}</option>
								</c:if>
								</c:forEach>
						</select></td>

					</tr>
					<tr>
						<td class="u-align-right"><label>Thể loại: <span
								style="color: red">*</span></label> <small class="u-block"
							style="color: red" id="check"></small></td>
						<td class="genre">
							<div class="row">
								<div class="col-sm-3">
									<input id="action" type="checkbox" name="genre" value="0"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 0}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="action">Action</label>
								</div>
								<div class="col-sm-3">
									<input id="adult" type="checkbox" name="genre" value="8"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 8}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="adult">Adult</label>
								</div>
								<div class="col-sm-3">
									<input id="adventure" type="checkbox" name="genre" value="15"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 15}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="adventure">Adventure</label>
								</div>
								<div class="col-sm-3">
									<input id="comedy" type="checkbox" name="genre" value="22"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 22}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="comedy">Comedy</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="drama" type="checkbox" name="genre" value="1"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 1}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="drama">Drama</label>
								</div>
								<div class="col-sm-3">
									<input id="ecchi" type="checkbox" name="genre" value="9"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 9}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="ecchi">Ecchi</label>
								</div>
								<div class="col-sm-3">
									<input id="fantasy" type="checkbox" name="genre" value="16"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 16}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="fantasy">Fantasy</label>
								</div>
								<div class="col-sm-3">
									<input id="gender-bender" type="checkbox" name="genre"
										value="23"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 23}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="gender-bender">Gender Bender</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="isekai" type="checkbox" name="genre" value="2"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 2}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="isekai">Isekai</label>
								</div>
								<div class="col-sm-3">
									<input id="incest" type="checkbox" name="genre" value="10"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 10}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="incest">Incest</label>
								</div>
								<div class="col-sm-3">
									<input id="horror" type="checkbox" name="genre" value="17"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 17}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="horror">Horror</label>
								</div>
								<div class="col-sm-3">
									<input id="josei" type="checkbox" name="genre" value="24"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 24}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="josei">Josei</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="mature" type="checkbox" name="genre" value="3"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 3}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="mature">Mature</label>
								</div>
								<div class="col-sm-3">
									<input id="mecha" type="checkbox" name="genre" value="11"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 11}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="mecha">Mecha</label>
								</div>
								<div class="col-sm-3">
									<input id="mystery" type="checkbox" name="genre" value="18"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 18}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="mystery">Mystery</label>
								</div>
								<div class="col-sm-3">
									<input id="romance" type="checkbox" name="genre" value="25"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 25}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="romance">Romance</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="school-life" type="checkbox" name="genre" value="4"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 4}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="school-life">School Life</label>
								</div>
								<div class="col-sm-3">
									<input id="shoujo-ai" type="checkbox" name="genre" value="12"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 12}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="shoujo-ai">Shoujo ai</label>
								</div>
								<div class="col-sm-3">
									<input id="shoujo" type="checkbox" name="genre" value="19"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 19}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="shoujo">Shoujo</label>
								</div>
								<div class="col-sm-3">
									<input id="seinen" type="checkbox" name="genre" value="26"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 0}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="seinen">Seinen</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="slice-of-life" type="checkbox" name="genre"
										value="5"><label for="slice-of-life">Slice of
										Life</label>
								</div>
								<div class="col-sm-3">
									<input id="sports" type="checkbox" name="genre" value="13"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 13}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="sports">Sports</label>
								</div>
								<div class="col-sm-3">
									<input id="shounen" type="checkbox" name="genre" value="20"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 20}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="shounen">Shounen</label>
								</div>
								<div class="col-sm-3">
									<input id="shounen-ai" type="checkbox" name="genre" value="27"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 27}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="shounen-ai">Shounen ai</label>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-3">
									<input id="super-power" type="checkbox" name="genre" value="6"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 0}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="super-power">Super Power</label>
								</div>
								<div class="col-sm-3">
									<input id="supernatural" type="checkbox" name="genre"
										value="14"><label for="supernatural">Supernatural</label>
								</div>
								<div class="col-sm-3">
									<input id="suspense" type="checkbox" name="genre" value="21"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 21}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="suspense">Suspense</label>
								</div>
								<div class="col-sm-3">
									<input id="tragedy" type="checkbox" name="genre" value="28"
										<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 28}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
										for="tragedy">Tragedy</label>
								</div>
							</div>
							<div class="col-sm-3">
								<input id="web-novel" type="checkbox" name="genre" value="7"
									<c:forEach var="genre" items="${fixingNovel.genres}">
										<c:if test = "${genre.getValue() eq 7}">
         									checked="checked" 
     									 </c:if>
								</c:forEach>><label
									for="web-novel">Web Novel</label>
							</div>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="u-align-center"><p
								class="u-paragraph--failed">${genreError}</p></td>
					</tr>
					<tr>
						<td class="u-align-right"><label>Mô tả: <span
								style="color: red">*</span></label></td>
						<td><textarea class="u-width--full" style="padding: .5rem"
								rows="7" name="description" required>${fixingNovel.description}</textarea></td>
					</tr>
					<tr>
						<td colspan="3" class="u-align-center"><p
								class="u-paragraph--failed">${descriptionError}</p></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Tình
								trạng: <span style="color: red">*</span>
						</label></td>
						<td><select name="status" style="padding: .5rem">
								<option value="0">Hoàn thành</option>
								<option value="1">Đang tiến hành</option>
								<option value="2">Tạm ngưng</option>
						</select></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Ảnh
								bìa:</label></td>
						<td><input type="file" name="image" size="50"
							accept="imamge/*"></td>
					</tr>
				</table>
				<div class="u-align-center u-color-white">
					<input name="fixedNovelID" type="hidden" value="${fixingNovel.id}"></input>
					<button type="submit" class="btn btn-primary u-2x">Sửa</button>
					<a href="manage" type="button" class="btn btn-danger u-2x">Hủy</a>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
		function checkCheckBoxes() {
			var c = document.getElementsByName("genre");
			for (var i = 0; i < c.length; i++) {
				if (c[i].checked == true) {
					return true;
				}
			}
			document.getElementById("check").innerHTML = "pls check at least one";
			return false;
		}
	</script>

</body>
</html>