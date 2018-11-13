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
	<div class="add">
    <div class="u-align-center">
        <p class="u-4x">
        Thêm truyện
        </p>
    </div>
    <form action="add" method="post" onsubmit="return checkCheckBoxes();">
        <table class="table u-2x u-centered u-width--95">
            <tr>
                <td class="u-width--15 u-align-right u-vertical-align--middle"><label>Tiêu đề: <span style="color: red">*</span></label></td>
                <td><input class="u-width--full" type="text" style="padding: .5rem" name="title" required></td>
            </tr>
            <tr>
                <td class="u-align-right u-vertical-align--middle"><label>Loại truyện: <span style="color: red">*</span></label></td>
                <td><select name="type-novel" style="padding: .5rem">
                        <option value="0">Sáng tác</option>
                        <option value="1">Truyện dịch</option>
                    </select></td>
            </tr>
            <tr>
                <td class="u-align-right u-vertical-align--middle"><label>Nhóm dịch: <span style="color: red">*</span></label></td>
                <td><select name="group" style="padding: .5rem">
                <!-- option value="nhom dich id" -->
                	<option value="11322">KingTranlator</option>
                    </select></td>
            </tr>
            <tr>
                <td class="u-align-right">
                <label>Thể loại: <span style="color: red">*</span></label>
                <small class="u-block" style="color: red" id="check"></small>
                </td>
                <td class="genre">
                    <div class="row">
                    <div class="col-sm-3">
                        <input id="action" type="checkbox" name="genre" value="0"><label for="action">Action</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="adult" type="checkbox" name="genre" value="8"><label for="adult">Adult</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="adventure" type="checkbox" name="genre" value="15"><label for="adventure">Adventure</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="comedy" type="checkbox" name="genre" value="22"><label for="comedy">Comedy</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="drama" type="checkbox" name="genre" value="1"><label for="drama">Drama</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="ecchi" type="checkbox" name="genre" value="9"><label for="ecchi">Ecchi</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="fantasy" type="checkbox" name="genre" value="16"><label for="fantasy">Fantasy</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="gender-bender" type="checkbox" name="genre" value="23"><label for="gender-bender">Gender Bender</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="isekai" type="checkbox" name="genre" value="2"><label for="isekai">Isekai</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="incest" type="checkbox" name="genre" value="10"><label for="incest">Incest</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="horror" type="checkbox" name="genre" value="17"><label for="horror">Horror</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="josei" type="checkbox" name="genre" value="24"><label for="josei">Josei</label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-3">
                        <input id="mature" type="checkbox" name="genre" value="3"><label for="mature">Mature</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="mecha" type="checkbox" name="genre" value="11"><label for="mecha">Mecha</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="mystery" type="checkbox" name="genre" value="18"><label for="mystery">Mystery</label>
                    </div>
                    <div class="col-sm-3">
                        <input id="romance" type="checkbox" name="genre" value="25"><label for="romance">Romance</label>
                    </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="school-life" type="checkbox" name="genre" value="4"><label for="school-life">School Life</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shoujo-ai" type="checkbox" name="genre" value="12"><label for="shoujo-ai">Shoujo ai</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shoujo" type="checkbox" name="genre" value="19"><label for="shoujo">Shoujo</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="seinen" type="checkbox" name="genre" value="26"><label for="seinen">Seinen</label>
                        </div>                
                    </div>
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="slice-of-life" type="checkbox" name="genre" value="5"><label for="slice-of-life">Slice of Life</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="sports" type="checkbox" name="genre" value="13"><label for="sports">Sports</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shounen" type="checkbox" name="genre" value="20"><label for="shounen">Shounen</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="shounen-ai" type="checkbox" name="genre" value="27"><label for="shounen-ai">Shounen ai</label>
                        </div>
                    </div>             
                    <div class="row">
                        <div class="col-sm-3">
                            <input id="super-power" type="checkbox" name="genre" value="6"><label for="super-power">Super Power</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="supernatural" type="checkbox" name="genre" value="14"><label for="supernatural">Supernatural</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="suspense" type="checkbox" name="genre" value="21"><label for="suspense">Suspense</label>
                        </div>
                        <div class="col-sm-3">
                            <input id="tragedy" type="checkbox" name="genre" value="28"><label for="tragedy">Tragedy</label>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <input id="web-novel" type="checkbox" name="genre" value="7"><label for="web-novel">Web Novel</label>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="u-align-right"><label>Mô tả: <span style="color: red">*</span></label></td>
                <td><textarea class="u-width--full" style="padding: .5rem" rows="7" name="description" required></textarea></td>
            </tr>
            <tr>
				<td class="u-align-right u-vertical-align--middle"><label>Tình trạng: <span style="color: red">*</span></label></td>
                <td><select name="status" style="padding: .5rem">
                        <option value="0">Hoàn thành</option>
                        <option value="1">Đang tiến hành</option>
                        <option value="2">Tạm ngưng</option>
                    </select></td>
            </tr>
        </table>
        <div class="u-align-center u-color-white">
            <button type="submit" class="btn btn-primary u-2x">Thêm</button>
            <a href="#" type="button" class="btn btn-danger u-2x">Quay lại</a>
        </div>
    </form>
	</div>
	<%@include file="/jsps/components/_footer.jsp"%>
	
	<script type="text/javascript">
    function checkCheckBoxes() {
        var c = document.getElementsByName("genre");
        for (var i = 0; i < c.length; i++) {
            if (c[i].checked == true) {
                return true;
            }
        }
        document.getElementById("check").innerHTML="pls check at least one";
        return false;
    }		
	</script>
	
</body>
</html>