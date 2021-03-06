<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<title>Đọc ${chap.title} - ${chap.novelOwner.name }</title>
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
	href="https://use.fontawesome.com/releases/v5.4.2/css/solid.css"
	integrity="sha384-uKQOWcYZKOuKmpYpvT0xCFAs/wE157X5Ua3H5onoRAOCNkJAMX/6QF0iXGGQV9cP"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.2/css/fontawesome.css"
	integrity="sha384-HU5rcgG/yUrsDGWsVACclYdzdCcn5yU8V/3V84zSrPDHwZEdjykadlgI6RHrxGrJ"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body style="background-image: none;">
<div id="fb-root"></div>
	<script>
		(function(d, s, id) {
			var js, fjs = d.getElementsByTagName(s)[0];
			if (d.getElementById(id))
				return;
			js = d.createElement(s);
			js.id = id;
			js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v3.2&appId=364583107662411&autoLogAppEvents=1';
			fjs.parentNode.insertBefore(js, fjs);
		}(document, 'script', 'facebook-jssdk'));
	</script>
	<div id="onTop"></div>
	<%@ include file="/jsps/components/_header.jsp"%>
	<div id="body" onclick="showHideSetup()">
	<div id="chap" class="chap">
		<div class="chap__header u-align-center u-margin-bottom--1rem">
			<h1>${chap.novelOwner.name }</h1>
			<h2>${chap.title }</h2>
			<p>${chap.dateUp}</p>
		</div>

		<div class="chap__body">
			<c:set var="previousChap" scope="request"
				value="${chap.volOwner.getPreviousChap(chap.id)}" />
			<c:set var="nextChap" scope="request"
				value="${chap.volOwner.getNextChap(chap.id)}" />
			<div class="chap__body__content" >
				<div id="content">
				<c:set var="newLine" value="\n"/>
				<c:set var="paragraphs" value="${chap.content.split(newLine) }" />
<!-- 				<span>${chap.content }</span> -->
					<c:forEach var="paragraph" items="${paragraphs }">
						<p>${paragraph }</p>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div style="background-color: white; text-align: center">
		<div style="max-width: 1140px" class="fb-comments" data-href="http://tieuthuyetonline.azurewebsites.net/read?id=${chap.id}" data-numposts="5" data-width="100%"></div>
	</div>
	<div id="set-up" style="display: block"
		class="chap__setup u-centered u-color-white">
		<ul>
			<li><a href="#onTop" class="btn btn-secondary"><i
					class="fas fa-arrow-up"></i></a></li>
			<li>
				<c:if test="${not empty previousChap}">
					<a id="preChap" href="read?id=${previousChap.id}"
						class="btn btn-secondary"><i class="fa fa-backward"></i></a>
				</c:if>
				<c:if test="${empty previousChap }">
					<a href="/" onclick="return false;"
						class="btn btn-secondary u-disabled" ><i class="fa fa-backward"></i></a>
				</c:if>
			</li>
			<li>
				<a href="detail?id=${chap.novelOwner.id}"
				class="btn btn-secondary"><i class="fa fa-home"></i></a>
			</li>
			<li><button id="btnFontChoice" onclick="showSetting()"
					class="btn btn-secondary">
					<i class="fas fa-font"></i>
				</button></li>
			<c:if test="${not empty account }">
				<li><button class="btn btn-secondary" onclick="bookmark(this)"><i class="fa fa-bookmark"></i></button></li>
			</c:if>
			<li>
				<c:if test="${not empty nextChap}">
					<a id="nextChap" href="read?id=${nextChap.id}"
						class="btn btn-secondary"><i class="fa fa-forward"></i></a>
				</c:if>
				<c:if test="${empty nextChap }">
					<a href="/" onclick="return false;"
						class="btn btn-secondary u-disabled"><i class="fa fa-forward"></i></a>
				</c:if>
			</li>
			<li><a href="#onBottom" class="btn btn-secondary"><i
					class="fas fa-arrow-down"></i></a></li>
		</ul>
	</div>
	<div id="setting" class="setting-box u-border-full"
		style="display: none">
		<div class="main">
			<div style="overflow: auto">
				<label
					style="float: left; font-size: 28px; color: #1d5268; border-bottom: 3px solid #10b591">Tùy
					chỉnh</label> <span onclick="hideSetting()"
					style="float: right; font-size: 30px; padding-right: 10px;"><i
					class="fas fa-times"></i></span>
			</div>
			<div class="row" style="margin-top: 1.5rem">
				<label class="col-sm-4 u-2x setting-box_label label-color">Màu
					nền</label>
				<div class="col-sm-8">
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(255, 255, 255); padding: 12px 20px; cursor: pointer"></span>
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(230, 240, 230); padding: 12px 20px; cursor: pointer"></span>
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(227, 245, 250); padding: 12px 20px; cursor: pointer"></span>
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(246, 244, 236); padding: 12px 20px; cursor: pointer"></span>
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(245, 233, 239); padding: 12px 20px; cursor: pointer"></span>
					<span class="color" onclick="selectColor(this)"
						style="background-color: rgb(0, 0, 0); padding: 12px 20px; cursor: pointer"></span>
				</div>
			</div>

			<div class="row" style="margin-top: 2rem">
				<label class="col-sm-4 u-2x setting-box_label">Font
					chữ</label>
				<div class="col-sm-8">
					<select id="font" onchange="changeFont(this)" class="u-width--95">
						<option value="Times New Roman">Times New Roman</option>
						<option value="Palatino Linotype">Palatino Linotype</option>
						<option value="SourceSansPro">SourceSansPro</option>
						<option value="Segoe UI">Segoe UI</option>
						<option value="Arial">Arial</option>
						<option value="Verdana">Verdana</option>
					</select>
				</div>
			</div>

			<div class="row" style="margin-top: 1.5rem">
				<label class="col-sm-4 u-2x setting-box_label">Cỡ
					chữ</label>
				<div class="col-sm-8">
					<span class="u-2x" style="cursor: pointer; color: #10b591"
						onclick="decreaseFSize()"><i class="fa fa-chevron-left"></i></span>
					<input id="size" type="text"
						style="width: 170px; margin: auto 15px"
						disabled> 
					<span class="u-2x"
					style="cursor: pointer; color: #10b591"
					onclick="increaseFSize()"><i class="fa fa-chevron-right"
					aria-hidden="true"></i></span>
				</div>
			</div>
			<div class="row" style="margin-top: 1.5rem">
				<label class="col-sm-4 u-2x setting-box_label">Dãn
					dòng</label>
				<div class="col-sm-8">
					<span class="u-2x" style="cursor: pointer; color: #10b591"
						onclick="decreaseLineHeight()"><i
						class="fa fa-chevron-left"></i></span> <input id="line-height"
						type="text"
						style="width: 170px; margin: auto 15px"
						disabled> <span class="u-2x"
						style="cursor: pointer; color: #10b591"
						onclick="increaseLineHeight()"><i
						class="fa fa-chevron-right" aria-hidden="true"></i></span>
				</div>
			</div>
		</div>
	</div>
	
	<div id="onBottom"></div>
	<%@include file="/jsps/components/_footer.jsp"%>
	<script type="text/javascript">
		var font = getCookie('font-family') == ""? "Times New Roman" : getCookie('font-family');
		var fontSize = getCookie('fontSize') == "" ? 20
				: parseInt(getCookie('fontSize'));
		var lineHeight = getCookie('lineHeight') == "" ? 26
				: parseInt(getCookie('lineHeight'));
		var backGroundColor = getCookie('backGroundColor') == "" ? "rgb(255, 255, 255)"
				: getCookie('backGroundColor');

		window.onload = function() {
			setFont(font);
			document.getElementById('font').value = font;
			document.getElementById('size').value = fontSize + "px";
			document.getElementById('line-height').value = lineHeight + "px";
			setSize(fontSize);
			setLineHeight(lineHeight);
			document.getElementById('body').style.backgroundColor = backGroundColor;
			setTextColor();
			setChoiceColor();

		}

		window.onbeforeunload = function() {
			setCookie('fontSize', fontSize, 30);
			setCookie('lineHeight', lineHeight, 30);
			setCookie('backGroundColor', backGroundColor, 30);
			setCookie('font-family', font, 30);
			setCookie('currentRead', '${chap.id}', 30);
		}

		function getCookie(cname) {
			var name = cname + "=";
			var ca = document.cookie.split(';');
			for (var i = 0; i < ca.length; i++) {
				var c = ca[i];
				while (c.charAt(0) == ' ') {
					c = c.substring(1);
				}
				if (c.indexOf(name) == 0) {
					return c.substring(name.length, c.length);
				}
			}
			return "";
		}

		function setCookie(cname, cvalue, exdays) {
			var d = new Date();
			d.setTime(d.getTime() + (exdays * 24 * 60 * 60 * 1000));
			var expires = "expires=" + d.toGMTString();
			document.cookie = cname + "=" + cvalue + ";" + expires;
		}
	</script>
	
	<script type="text/javascript">
		window.onkeyup = function(event) {
			var x = event.which;
			if (x == 37 || x == 65)
				document.getElementById('preChap').click();
			if (x == 39 || x == 68)
				document.getElementById('nextChap').click();
			if (x == 87)
				window.scrollBy(0, -37);
			if (x == 83)
				window.scrollBy(0, 37);
			}
	
		function setFont() {
			document.getElementById('chap').style.fontFamily = font;	
		}
			
		function changeFont(x) {
			font = x.value;
			setFont();
		}
	
	
		function setChoiceColor() {
			var colors = document.getElementsByClassName('color');
			for (var i = 0; i < colors.length; i++) {
				if (colors[i].style.backgroundColor != backGroundColor)
					colors[i].style.outline = '';
				else
					colors[i].style.outline = '3px solid #10b591';
			}
		}

		function selectColor(x) {
			backGroundColor = x.style.backgroundColor;
			document.getElementById('body').style.backgroundColor = backGroundColor;
			setTextColor();
			setChoiceColor();
		}
		
		function setTextColor() {
			if (backGroundColor == 'rgb(0, 0, 0)') {
				document.getElementById('chap').style.color = 'rgba(255, 255, 255, 0.83)';
			} else
				document.getElementById('chap').style.color = 'rgb(3, 3, 3)';
			
		}

		function increaseFSize() {
			fontSize += 2;
			setSize();
			document.getElementById('size').value = fontSize + "px";
		}

		function increaseLineHeight() {
			lineHeight += 2;
			setLineHeight();
			document.getElementById('line-height').value = lineHeight + "px";
		}

		function decreaseLineHeight() {
			lineHeight -= 2;
			setLineHeight();
			document.getElementById('line-height').value = lineHeight + "px";
		}

		function decreaseFSize() {
			fontSize -= 2;
			setSize();
			document.getElementById('size').value = fontSize + "px";
		}

		function setSize() {
			document.getElementById('content').style.fontSize = fontSize + "px";

		}

		function setLineHeight() {
			document.getElementById('content').style.lineHeight = lineHeight
					+ "px";
		}

		function showHideSetup() {
			var setUp = document.getElementById('set-up');
			var setting = document.getElementById('setting');
			
			if (setting.style.display == 'none') {
				if (setUp.style.display == 'block')
					setUp.style.display = 'none';
				else
					setUp.style.display = 'block';
			} else {
				hideSetting();
			}
		}

		function showSetting() {
			document.getElementById('setting').style.display = 'block';
		}

		function hideSetting() {
			document.getElementById('setting').style.display = 'none';
		}
	</script>
	<script>
		var url = location.origin.concat('${pageContext.request.contextPath}').concat('/bookmark');
		var xhttp = new XMLHttpRequest();
		function bookmark(btn) {
			xhttp.onreadystatechange = function() {
				if (xhttp.readyState == 4 && xhttp.status == 200) {
					btn.className += " u-disabled";
					alert("Đã bookmark: ${chap.novelOwner.name} \n${chap.title}!!")
					createBM();
				}
			}
			xhttp.open('POST', url);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send("title=${chap.novelOwner.name}&detail=${chap.title}&url=${pageContext.request.contextPath}/read?id=${chap.id}");
		}
		function createBM() {
			var bmLine = document.createElement("div");
			bmLine.style.borderBottom = "1px solid #00000029";
			bmLine.style.padding = ".7rem";
			
			var href = document.createElement("a");
			href.className = "u-text-overflow--hidden";
			href.style.color = "#444";
			href.style.display = "block";
			href.style.fontSize = "1.7rem";
			href.href = "${pageContext.request.contextPath}/read?id=${chap.id}";
			href.title = "${chap.novelOwner.name} - ${chap.title}";
			href.innerHTML = "${chap.novelOwner.name} <br>";
			var span = document.createElement("span");
			span.style.fontSize = "1.5rem";
			span.style.paddingLeft = "1.5rem";
			span.style.color = "#10b591";
			span.innerHTML = "${chap.title}";
			href.appendChild(span);
			bmLine.appendChild(href);
			
			var bmBox = document.getElementById("boorkmark-content");
			bmBox.insertBefore(bmLine, bmBox.firstChild);
			
		}
	</script>
</body>
</html>