<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=divice-width, intitial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese" rel="stylesheet"> 
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/brands.css" integrity="sha384-Px1uYmw7+bCkOsNAiAV5nxGKJ0Ixn5nChyW8lCK1Li1ic9nbO5pC/iXaq27X5ENt" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/solid.css" integrity="sha384-osqezT+30O6N/vsMqwW8Ch6wKlMofqueuia2H7fePy42uC05rm1G+BUPSd2iBSJL" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/fontawesome.css" integrity="sha384-BzCy2fixOYd0HObpx3GMefNqdbA7Qjcc91RgYeDjrHTIEXqiF00jKvgQG0+zY/7I" crossorigin="anonymous"><link rel="stylesheet" href="resources/vendors/css/bootstrap-reboot.css" />	
<link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
<link rel="stylesheet" href="resources/local/css/style.css"/>
</head>
<body>
	<%@ include file="/jsps/components/_header.jsp" %>
<div class="detail">
        <div class="detail__top u-centered">
            <div class="row">
                <div class="col-md-4 detail__top-cover" style="border: 1px solid black">
                    <img class="img-cover" src="jsps/pages/a.jpg" alt="đoán xem">
                </div>
                <div class="col-md-8 detail__top-info">
                    <div>
                        <h1 class="u-color-blue">
                            <a class="link" href="#">Aishiteru yo!</a>
                        </h1>
                        <div>
                            <span class="u-block"> Người đăng: <a class="link u-color-blue" href="#">Watashi</a></span> 
                            <span class="u-block">  
								<ul class="horizontal-menu--showcase">
									<li class="menu-item">
									<form action="search" method="post">
										<input type="hidden" name="genre" value="advanture">
										<button class="btn btn-belike-a">Advanture</button>
									</form></li>
									<li class="menu-item">
									<form action="search" method="post">
										<input type="hidden" name="genre" value="drama">
										<button class="btn btn-belike-a">Drama</button>
									</form></li>
									<li class="menu-item">
									<form action="search" method="post">
										<input type="hidden" name="genre" value="school-life">
										<button class="btn btn-belike-a">School life</button>
									</form></li>
								</ul>   
							</span>                   
						</div>
                    </div>
                    <div>
                        <button class="btn btn-info u-color-white">Đọc từ đầu</button>
                        <button class="btn btn-danger u-color-white">+Theo dõi</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="detail__body u-centered">
            <section class="tab u-block">
                <div>
                    <button class="btn tab-btn u-2x" onclick="showOrHide('introduce')">Giới thiệu</button>
                </div>
                <div id="introduce" class="tab-content" style="display: block">
                    Paragraphs are the building blocks of papers. Many students define paragraphs in terms of length: a paragraph is a group of at least five sentences, a paragraph is half a page long, etc. In reality, though, the unity and coherence of ideas among sentences is what constitutes a paragraph. A paragraph is defined as “a group of sentences or a single sentence that forms a unit” (Lunsford and Connors 116). Length and appearance do not determine whether a section in a paper is a paragraph. 
                </div>
            </section>
            <section class="tab u-block">
                <button class="btn tab-btn u-2x" onclick="showOrHide('note')">chú thích</button>
                <div id="note" class="tab-content" style="display: none">
                    Nothing... <br>
                    Surprise mother fucker!!!
                </div>
            </section>
            <section class="tab u-block">
                <button class="btn tab-btn u-2x" onclick="showOrHide('vol1')">vol1</button>
                <div id="vol1" class="tab-content" style="display: none">
                    <a class="link u-block" href="#">Chương 1</a>
                    <a class="link u-block" href="#">Chương 2</a>
                </div>
            </section>
            <section class="tab u-block">
                <button class="btn tab-btn u-2x" onclick="showOrHide('vol2')">vol2</button>
                <div id="vol2" class="tab-content" style="display: none">
                    <a class="link u-block" href="#">Chương 1</a>
                    <a class="link u-block" href="#">Chương 2</a>
                </div>
            </section>
            <section class="tab u-block">
                <button class="btn tab-btn u-2x" onclick="showOrHide('vol3')">vol3</button>
                <div id="vol3" class="tab-content" style="display: none">
                    <a class="link u-block" href="#">Chương 1</a>
                    <a class="link u-block" href="#">Chương 2</a>
                </div>
            </section>
        </div>
    </div>
    <script>
        function showOrHide(id) {
            var x = document.getElementById(id);
            if (x.style.display =='none') {
                x.style.display = 'block';
            } else {
                x.style.display = 'none';
            }
        }
    </script>
    
	<%@include file="/jsps/components/_footer.jsp" %>
</body>
</html>