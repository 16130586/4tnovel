<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ABC</title>
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
<body onload="loadVol()">
	<div class="account-manage">
		<%@ include file="/jsps/components/_account-manage.header.jsp"%>
		<div class="add">
			<div class="u-align-center">
				<p class="u-5x">Thêm chương</p>
			</div>

			<form action="add" method="post">
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
						<td><select name="in-novel" style="padding: .5rem"
							onchange="loadVol()">
								<option value="112">A</option>
								<option value="113">B</option>
								<option value="114">C</option>
						</select></td>
					</tr>
					<tr>
						<td class="u-align-right u-vertical-align--middle"><label>Thuộc
								tập: <span style="color: red">*</span>
						</label></td>
						<td><select name="in-vol" id="list-vol"
							style="padding: .5rem">

						</select></td>
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
			var a = [ "1", "2", "3" ];
			var b = [ "4", "5", "6" ];
			var c = [ "7", "8", "9" ];
			function loadVol() {
				var x = document.getElementsByName("select-novel");
				var temp;
				var result;
				for (var i = 0; i < x.length; i++) {
					if (x[i].selected == true) {
						temp = x[i].text;
						break;
					}
				}
				switch (temp) {
				case "A":
					result = a;
					break;
				case "B":
					result = b;
					break;
				case "C":
					result = c
					break;
				default:

				}
				var y = document.getElementById("list-vol");
				y.innerHTML = "";
				var option;
				for (var i = 0; i < result.length; i++) {
					option = document.createElement("option");
					option.setAttribute("name", "select-vol");
					option.text = result[i];
					y.add(option);
				}
			}
		</script>
	</div>
</body>
</html>