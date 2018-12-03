<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    <link href="https://fonts.googleapis.com/css?family=Exo:400,400i,500,500i,800&amp;subset=vietnamese" rel="stylesheet"> 
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/solid.css" integrity="sha384-uKQOWcYZKOuKmpYpvT0xCFAs/wE157X5Ua3H5onoRAOCNkJAMX/6QF0iXGGQV9cP" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/fontawesome.css" integrity="sha384-HU5rcgG/yUrsDGWsVACclYdzdCcn5yU8V/3V84zSrPDHwZEdjykadlgI6RHrxGrJ" crossorigin="anonymous">
    <link rel="stylesheet" href="resources/vendors/css/bootstrap-customize.css">
    <link rel="stylesheet" href="resources/local/css/style.css" />
</head>
<body>
	<%@ include file="/jsps/components/_header.jsp"%>
    <div id="onTop"></div>
    <div class="chap">
        <div class="chap__header u-align-center u-margin-bottom--1rem">
            <h2>Nghịch Kiếm Cuồng Thần</h2>
            <p>23:48 - 10/12/15</p>
        </div>
        
        <div class="chap__body">
            <div class="u-align-center">
                <a href="#" class="btn btn-success u-margin-right--1rem">  <<  </a>
                <a href="#" class="btn btn-secondary u-margin-right--1rem"><i class="fas fa-list-ol"></i></a>
                <a href="#" class="btn btn-success u-margin-right--1rem">  >>  </a>
            </div>
            <div class="chap__body__content">
                <div>
                    <h3>${chap.title }</h3>
                </div>
                <div>
                	<span>${chap.content }</span>
                </div>
            </div>
            <div class="u-align-center">
                <a href="#" class="btn btn-success u-margin-right--1rem">  <<  </a>
                <a href="#" class="btn btn-secondary u-margin-right--1rem"><i class="fas fa-list-ol"></i></a>
                <a href="#" class="btn btn-success u-margin-right--1rem">  >>  </a>
            </div>
        </div>
        
         <div class="chap__comment">

        </div>    
        
        <div class="chap__setup u-centered u-margin-right--1rem">
            <ul>
                <li><a href="#onTop" class="btn btn-success" ><i class="fas fa-arrow-up"></i></a></li>
                <li><button id="btnFontChoice" href="#" class="btn btn-secondary"><i class="fas fa-font"></i></button></li>
                <li><button id="btnColorChoice" href="#" class="btn btn-secondary"><i class="fas fa-palette"></i></button></li>
                <li><a href="#onBottom" class="btn btn-success"><i class="fas fa-arrow-down"></i></a></li>
            </ul>
        </div>
        

    </div>
    <div id="onBottom"></div>
    <%@include file="/jsps/components/_footer.jsp"%>
</body>
</html>