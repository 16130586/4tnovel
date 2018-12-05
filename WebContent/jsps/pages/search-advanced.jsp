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
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
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
                <input class="input u-width--full" type="text" name="input" placeholder="Tìm kiếm...">
            </div>

            <div class="col-md-3 btn-search">
                <button type="submit" class="btn btn-primary">Tìm kiếm</button>
            </div>
            <div class="col-md-1"></div>
        </div>
        <div class="search-advanced row u-centered">
            <div class="col-md-3 u-align-right status">
                <label>Tình trạng</label>
                <select>
                  <option value="complete">Hoàn thành</option>
                  <option value="inprocess">Đang tiến hành</option>
                  <option value="pause">Dừng</option>
                </select>
            </div>
            <div class="col-md-9 genre-select-box">  
            	<div class="row">
			        <div class="col-4">
			            <input id="action" type="checkbox" name="genre" value="action"><label for="action">Action</label>
			        </div>
			        <div class="col-4">
			            <input id="adult" type="checkbox" name="genre" value="adult"><label for="adult">Adult</label>
			        </div>
			        <div class="col-4">
			            <input id="adventure" type="checkbox" name="genre" value="adventure"><label for="adventure">Adventure</label>
			        </div>
			        <div class="col-4">
			            <input id="comedy" type="checkbox" name="genre" value="comedy"><label for="comedy">Comedy</label>
			        </div>
			        <div class="col-4">
			            <input id="drama" type="checkbox" name="genre" value="drama"><label for="drama">Drama</label>
			        </div>
			        <div class="col-4">
			            <input id="ecchi" type="checkbox" name="genre" value="ecchi"><label for="ecchi">Ecchi</label>
			        </div>
			        <div class="col-4">
			            <input id="fantasy" type="checkbox" name="genre" value="fantasy"><label for="fantasy">Fantasy</label>
			        </div>
			        <div class="col-4">
			            <input id="gender-bender" type="checkbox" name="genre" value="gender-bender"><label for="gender-bender">Gender Bender</label>
			        </div>
			        <div class="col-4">
			            <input id="isekai" type="checkbox" name="genre" value="isekai"><label for="isekai">Isekai</label>
			        </div>
			        <div class="col-4">
			            <input id="incest" type="checkbox" name="genre" value="incest"><label for="incest">Incest</label>
			        </div>
			        <div class="col-4">
			            <input id="horror" type="checkbox" name="genre" value="horror"><label for="horror">Horror</label>
			        </div>
			        <div class="col-4">
			            <input id="josei" type="checkbox" name="genre" value="josei"><label for="josei">Josei</label>
			        </div>
			        <div class="col-4">
			            <input id="mature" type="checkbox" name="genre" value="mature"><label for="mature">Mature</label>
			        </div>
			        <div class="col-4">
			            <input id="mecha" type="checkbox" name="genre" value="mecha"><label for="mecha">Mecha</label>
			        </div>
			        <div class="col-4">
			            <input id="mystery" type="checkbox" name="genre" value="mystery"><label for="mystery">Mystery</label>
			        </div>
			        <div class="col-4">
			            <input id="romance" type="checkbox" name="genre" value="romance"><label for="romance">Romance</label>
			        </div>
			        <div class="col-4">
			            <input id="school-life" type="checkbox" name="genre" value="school-life"><label for="school-life">School Life</label>
			        </div>
			        <div class="col-4">
			            <input id="shoujo-ai" type="checkbox" name="genre" value="shoujo-ai"><label for="shoujo-ai">Shoujo ai</label>
			        </div>
			        <div class="col-4">
			            <input id="shoujo" type="checkbox" name="genre" value="shoujo"><label for="shoujo">Shoujo</label>
			        </div>
			        <div class="col-4">
			            <input id="seinen" type="checkbox" name="genre" value="seinen"><label for="seinen">Seinen</label>
			        </div>                
			        <div class="col-4">
			            <input id="slice-of-life" type="checkbox" name="genre" value="slice-of-life"><label for="slice-of-life">Slice of Life</label>
			        </div>
			        <div class="col-4">
			            <input id="sports" type="checkbox" name="genre" value="sports"><label for="sports">Sports</label>
			        </div>
			        <div class="col-4">
			            <input id="shounen" type="checkbox" name="genre" value="shounen"><label for="shounen">Shounen</label>
			        </div>
			        <div class="col-4">
			            <input id="shounen-ai" type="checkbox" name="genre" value="shounen-ai"><label for="shounen-ai">Shounen ai</label>
			        </div>
			        <div class="col-4">
			            <input id="super-power" type="checkbox" name="genre" value="super-power"><label for="super-power">Super Power</label>
			        </div>
			        <div class="col-4">
			            <input id="supernatural" type="checkbox" name="genre" value="supernatural"><label for="supernatural">Supernatural</label>
			        </div>
			        <div class="col-4">
			            <input id="suspense" type="checkbox" name="genre" value="suspense"><label for="suspense">Suspense</label>
			        </div>
			        <div class="col-4">
			            <input id="tragedy" type="checkbox" name="genre" value="tragedy"><label for="tragedy">Tragedy</label>
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