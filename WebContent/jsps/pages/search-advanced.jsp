<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Tìm kiếm | 4TNOVEL</title>
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
	href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css"
	integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css"
	integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css"
	integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>

<body>
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="search">
		<div class="search-form u-centered">
			<form action="search" method="post">
				<div class="search-bar row u-2x">
					<div class="col-md-2"></div>
					<div class="col-md-6">
						<input class="input u-width--full" type="text" name="input"
							placeholder="Tìm kiếm...">
					</div>
					<div class="col-md-3 btn-search">
						<button name="type" type="submit" class="btn btn-primary"
							value="advance">Tìm kiếm</button>
					</div>
					<div class="col-md-1"></div>
				</div>
				<div class="search-advanced row u-centered">
					<div class="col-md-3 u-align-right status">
						<div>
							<label style="width: 98.27px">Tình trạng</label> <select
								name="status">
								<option value="all">Tất cả</option>
								<option value="0">Hoàn thành</option>
								<option value="1">Đang tiến hành</option>
								<option value="2">Dừng</option>
							</select>
						</div>
						<div>
							<label>Loại truyện</label> <select name="kind"
								style="width: 143px;">
								<option value="all">Tất cả</option>
								<option value="compose">Sáng tác</option>
								<option value="translate">Truyện dịch</option>
							</select>
						</div>
					</div>
					<div class="col-md-9 genre-select-box">
						<div class="row">
							<div class="col-4">
								<input id="action" type="checkbox" name="genre" value="0"><label
									for="action">Action</label>
							</div>
							<div class="col-4">
								<input id="adult" type="checkbox" name="genre" value="8"><label
									for="adult">Adult</label>
							</div>
							<div class="col-4">
								<input id="adventure" type="checkbox" name="genre" value="15"><label
									for="adventure">Adventure</label>
							</div>
							<div class="col-4">
								<input id="comedy" type="checkbox" name="genre" value="22"><label
									for="comedy">Comedy</label>
							</div>
							<div class="col-4">
								<input id="drama" type="checkbox" name="genre" value="1"><label
									for="drama">Drama</label>
							</div>
							<div class="col-4">
								<input id="ecchi" type="checkbox" name="genre" value="9"><label
									for="ecchi">Ecchi</label>
							</div>
							<div class="col-4">
								<input id="fantasy" type="checkbox" name="genre" value="16"><label
									for="fantasy">Fantasy</label>
							</div>
							<div class="col-4">
								<input id="gender-bender" type="checkbox" name="genre"
									value="23"><label for="gender-bender">Gender
									Bender</label>
							</div>
							<div class="col-4">
								<input id="isekai" type="checkbox" name="genre" value="2"><label
									for="isekai">Isekai</label>
							</div>
							<div class="col-4">
								<input id="incest" type="checkbox" name="genre" value="10"><label
									for="incest">Incest</label>
							</div>
							<div class="col-4">
								<input id="horror" type="checkbox" name="genre" value="17"><label
									for="horror">Horror</label>
							</div>
							<div class="col-4">
								<input id="josei" type="checkbox" name="genre" value="24"><label
									for="josei">Josei</label>
							</div>
							<div class="col-4">
								<input id="mature" type="checkbox" name="genre" value="3"><label
									for="mature">Mature</label>
							</div>
							<div class="col-4">
								<input id="mechanic" type="checkbox" name="genre" value="11"><label
									for="mechanic">Mecha</label>
							</div>
							<div class="col-4">
								<input id="mystery" type="checkbox" name="genre" value="18"><label
									for="mystery">Mystery</label>
							</div>
							<div class="col-4">
								<input id="romance" type="checkbox" name="genre" value="25"><label
									for="romance">Romance</label>
							</div>
							<div class="col-4">
								<input id="school-life" type="checkbox" name="genre" value="4"><label
									for="school-life">School Life</label>
							</div>
							<div class="col-4">
								<input id="shoujo-ai" type="checkbox" name="genre" value="12"><label
									for="shoujo-ai">Shoujo ai</label>
							</div>
							<div class="col-4">
								<input id="shoujo" type="checkbox" name="genre" value="19"><label
									for="shoujo">Shoujo</label>
							</div>
							<div class="col-4">
								<input id="seinen" type="checkbox" name="genre" value="26"><label
									for="seinen">Seinen</label>
							</div>
							<div class="col-4">
								<input id="slice-of-life" type="checkbox" name="genre" value="5"><label
									for="slice-of-life">Slice of Life</label>
							</div>
							<div class="col-4">
								<input id="sports" type="checkbox" name="genre" value="13"><label
									for="sports">Sports</label>
							</div>
							<div class="col-4">
								<input id="shounen" type="checkbox" name="genre" value="20"><label
									for="shounen">Shounen</label>
							</div>
							<div class="col-4">
								<input id="shounen-ai" type="checkbox" name="genre"
									value="shounen-ai"><label for="27">Shounen ai</label>
							</div>
							<div class="col-4">
								<input id="super-power" type="checkbox" name="genre" value="6"><label
									for="super-power">Super Power</label>
							</div>
							<div class="col-4">
								<input id="supernatural" type="checkbox" name="genre" value="14"><label
									for="supernatural">Supernatural</label>
							</div>
							<div class="col-4">
								<input id="suspense" type="checkbox" name="genre" value="21"><label
									for="suspense">Suspense</label>
							</div>
							<div class="col-4">
								<input id="tragedy" type="checkbox" name="genre" value="28"><label
									for="tragedy">Tragedy</label>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>
</body>
</html>