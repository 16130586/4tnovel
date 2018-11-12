<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- the following to include all needed things 
	font
	font awesome icon
	bootstraps custom-mize if you have using button , grid-system
	custom css
-->
    <link href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese" rel="stylesheet"> 
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/solid.css" integrity="sha384-uKQOWcYZKOuKmpYpvT0xCFAs/wE157X5Ua3H5onoRAOCNkJAMX/6QF0iXGGQV9cP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/fontawesome.css" integrity="sha384-HU5rcgG/yUrsDGWsVACclYdzdCcn5yU8V/3V84zSrPDHwZEdjykadlgI6RHrxGrJ" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<div id="onTop"></div>
	<%@ include file="/jsps/components/_header.jsp" %>
	<div class="search">
        <div class="u-width--80 u-centered">
            <h1>Kết quả tìm kiếm</h1>
        </div>
        <div class="search-result u-width--80 u-centered">
            <table class="table table-hover table-dark">
                <tr>
                    <th>Tên truyện</th>
                    <th class="u-width--15">Số chương</th>
                    <th class="u-width--15">Trạng thái</th>
                    <th class="u-width--15">Ngày đăng cuối</th>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
                <tr>
                    <td>
                        <a class="link" href="#">(Um, Sorry) I’ve Been Reincarnated!</a> <br>
                        <small>adult, hentai</small>
                    </td>
                    <td>20</td>
                    <td>Hoàn thành</td>
                    <td>29/10/18</td>
                </tr>
      

            </table>
			<div class="u-align-right u-color-white">
                <ul class="horizontal-menu--showcase">
                    <li class="menu-item"><a class="btn btn-dark">Previous</a></li>
                    <li class="menu-item"><a class="btn btn-dark">1</a></li>
                    <li class="menu-item"><a class="btn btn-dark">2</a></li>
                    <li class="menu-item"><a class="btn btn-dark">3</a></li>
                    <li class="menu-item"><a class="btn btn-dark">Next</a></li>
                </ul> 
            </div>
        </div>
        <a style="position: fixed; top: 60vh; right: 2rem;" href="#onTop" class="btn btn-success u-color-white" ><i class="fas fa-arrow-up"></i></a>
    </div>
	<%@ include file="/jsps/components/_footer.jsp" %>
</body>
</html>