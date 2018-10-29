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
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
    
</head>
<body>
	<%@ include file="/jsps/components/_header.jsp" %>
    <div class="thread u-centered">
        <div class="u-align-center u-margin-bottom--2rem u-margin-top--2rem">
            <h1>Thảo luận</h1>
        </div>
        <div class="row">
            <div class="col-md-10">
                <div>
                <table class="table table-dark table-bordered table-hover table-host-align--right">
                      <tr>
                        <th class="u-width--10">Chủ đề</th>
                        <th class="u-width--50">Tiêu đề</th>
                        <th class="u-width--10">Bình luận</th>
                        <th class="u-width--10">Lượt xem</th>
                        <th class="u-width--10">Ngày đăng</th>
                        <th class="u-width--10">Chủ</th>
                      </tr>
                      <tr>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td><a class="link u-padding--none" href="#">asda  </a></td>
                        <td>1.7k</td>
                        <td>2.2k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda  </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>600</td>
                        <td>200</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>200</td>
                        <td>1k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda  </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>1k</td>
                        <td>1.5k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr>
                      <tr>    
                        <td><a class="link u-padding--none" href="#">asda </a></td>
                          <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>200</td>
                        <td>500</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr>
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda  </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>1.7k</td>
                        <td>2.2k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda  </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>600</td>
                        <td>200</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda </a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>200</td>
                        <td>1k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr> 
                        <tr>
                        <td><a class="link u-padding--none" href="#">asda</a></td>
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td>1k</td>
                        <td>1.5k</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr>
                      <tr>    
                        <td><a class="link u-padding--none" href="#">Anime</a></td>
                        <td><a class="link u-padding--none" href="#">asda sdas asda sdas asda sdas asda sdas </a></td>
                        <td>200</td>
                        <td>500</td>
                        <td>29/10/18</td>
                        <td><a class="link u-padding--none" href="#">Tuyên</a></td>
                      </tr>

                </table>
                </div>
                <div class="u-align-right">
                    <ul class="horizontal-menu--showcase">
                        <li class="menu-item"><a class="btn btn-dark ">Previous</a></li>
                        <li class="menu-item"><a class="btn btn-dark ">1</a></li>
                        <li class="menu-item"><a class="btn btn-dark ">2</a></li>
                        <li class="menu-item"><a class="btn btn-dark ">3</a></li>
                        <li class="menu-item"><a class="btn btn-dark ">Next</a></li>
                    </ul> 
                </div>
            </div>

            <div class="col-md-2">
                <table class="table table-dark table-hover">
                    <thead>
                        <tr>
                            <th>Chủ đề nóng</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr><td><a class="link u-padding--none" href="#">Anime</a></td></tr>
                        <tr><td><a class="link u-padding--none" href="#">Anime</a></td></tr>
                        <tr><td><a class="link u-padding--none" href="#">Anime</a></td></tr>
                        <tr><td><a class="link u-padding--none" href="#">Anime</a></td></tr>
                        <tr><td><a class="link u-padding--none" href="#">Anime</a></td></tr>
                    
                    </tbody>
                
                </table>
            </div>
        </div>
    </div>
    </div>
	<%@include file="/jsps/components/_footer.jsp" %>    
</body>
</html>