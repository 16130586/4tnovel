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
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
</head> 
<body>
	<%@ include file="/jsps/components/_header.jsp"%>
	<div class="add-novel">
    <div class="u-align-center">
        <p class="u-4x">
        Thêm truyện
        </p>
    </div>
    <form action="AddServlet" method="post">
        <table class="table u-2x u-centered u-width--95">
            <tr>
                <td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu đề: <span style="color: red">*</span></label></td>
                <td><input class="u-width--full" type="text" style="padding: .5rem" required></td>
            </tr>
            <tr>
                <td class="u-align-right u-vertical-align--middle"><label>Loại truyện:</label></td>
                <td><select style="padding: .5rem">
                        <option>Truyện dịch</option>
                        <option>Sáng tác</option>
                    </select></td>
            </tr>
            <tr>
                <td class="u-align-right u-vertical-align--middle"><label>Nhóm dịch:</label></td>
                <td><select style="padding: .5rem">
                    </select></td>
            </tr>
            <tr>
                <td class="u-align-right"><label>Thể loại:</label></td>
                <td class="genre">
                    <div class="row">
                    <div class="col-sm-3">
                        <input id="action" type="checkbox" name="genre" value="action"><label for="action">Action</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="adult" type="checkbox" name="genre" value="adult"><label for="adult">Adult</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="adventure" type="checkbox" name="genre" value="adventure"><label for="adventure">Adventure</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="comedy" type="checkbox" name="genre" value="comedy"><label for="comedy">Comedy</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="drama" type="checkbox" name="genre" value="drama"><label for="drama">Drama</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="ecchi" type="checkbox" name="genre" value="ecchi"><label for="ecchi">Ecchi</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="fantasy" type="checkbox" name="genre" value="fantasy"><label for="fantasy">Fantasy</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="gender-bender" type="checkbox" name="genre" value="gender-bender"><label for="gender-bender">Gender Bender</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="isekai" type="checkbox" name="genre" value="isekai"><label for="isekai">Isekai</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="incest" type="checkbox" name="genre" value="incest"><label for="incest">Incest</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="horror" type="checkbox" name="genre" value="horror"><label for="horror">Horror</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="josei" type="checkbox" name="genre" value="josei"><label for="josei">Josei</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="mature" type="checkbox" name="genre" value="mature"><label for="mature">Mature</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="mecha" type="checkbox" name="genre" value="mecha"><label for="mecha">Mecha</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="mystery" type="checkbox" name="genre" value="mystery"><label for="mystery">Mystery</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="romance" type="checkbox" name="genre" value="romance"><label for="romance">Romance</label>
                    </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="school-life" type="checkbox" name="genre" value="school-life"><label for="school-life">School Life</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shoujo-ai" type="checkbox" name="genre" value="shoujo-ai"><label for="shoujo-ai">Shoujo ai</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shoujo" type="checkbox" name="genre" value="shoujo"><label for="shoujo">Shoujo</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="seinen" type="checkbox" name="genre" value="seinen"><label for="seinen">Seinen</label>
                        </div>                
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="slice-of-life" type="checkbox" name="genre" value="slice-of-life"><label for="slice-of-life">Slice of Life</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="sports" type="checkbox" name="genre" value="sports"><label for="sports">Sports</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shounen" type="checkbox" name="genre" value="shounen"><label for="shounen">Shounen</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shounen-ai" type="checkbox" name="genre" value="shounen-ai"><label for="shounen-ai">Shounen ai</label>
                        </div>
                    </div>             
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="super-power" type="checkbox" name="genre" value="super-power"><label for="super-power">Super Power</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="supernatural" type="checkbox" name="genre" value="supernatural"><label for="supernatural">Supernatural</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="suspense" type="checkbox" name="genre" value="suspense"><label for="suspense">Suspense</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="tragedy" type="checkbox" name="genre" value="tragedy"><label for="tragedy">Tragedy</label>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <input id="web-novel" type="checkbox" name="genre" value="web-novel"><label for="web-novel">Web Novel</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="u-align-right"><label>Mô tả: <span style="color: red">*</span></label></td>
                <td><textarea class="u-width--full" style="padding: .5rem" rows="7" required></textarea></td>
            </tr>
        </table>
        <div class="u-align-center u-color-white">
            <button type="submit" class="btn btn-primary u-2x">Thêm</button>
            <a href="#" type="button" class="btn btn-danger u-2x">Quay lại</a>
        </div>
    </form>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>
</body>
</html>