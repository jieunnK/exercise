<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>	
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>About exercise..</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="manifest" href="site.webmanifest">
<link rel="shortcut icon" type="image/x-icon"
	href="/assets/img/favicon.ico">

<!-- CSS here -->
<link rel="stylesheet" href="/assets/css/bootstrap.min.css">
<link rel="stylesheet" href="/assets/css/owl.carousel.min.css">
<link rel="stylesheet" href="/assets/css/flaticon.css">
<link rel="stylesheet" href="/assets/css/slicknav.css">
<link rel="stylesheet" href="/assets/css/animate.min.css">
<link rel="stylesheet" href="/assets/css/magnific-popup.css">
<link rel="stylesheet" href="/assets/css/fontawesome-all.min.css">
<link rel="stylesheet" href="/assets/css/themify-icons.css">
<link rel="stylesheet" href="/assets/css/slick.css">
<link rel="stylesheet" href="/assets/css/nice-select.css">
<link rel="stylesheet" href="/assets/css/style.css">
<link rel="stylesheet" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.4/themes/smoothness/jquery-ui.min.css" />
<script src="/assets/js/vendor/jquery-1.12.4.min.js"></script>

</head>
<title>Insert title here</title>
</head>
<body>
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<!--  <!--? Preloader Start -->
	<div id="preloader-active">
		<div
			class="preloader d-flex align-items-center justify-content-center">
			<div class="preloader-inner position-relative">
				<div class="preloader-circle"></div>
				<div class="preloader-img pere-text">
					<h>About exercise</h>
				</div>
			</div>
		</div>
	</div>
	<!-- Preloader Start -->
	<header>
		<!-- Header Start -->
		<div class="header-area">
			<div class="main-header header-sticky">
				<div class="container-fluid">
					<div class="menu-wrapper">
						<!-- Logo -->
						<div class="logo">
							<a href="/main.do"><h1>About exercise</h1></a>
						</div>
						<!-- Main-menu -->
						<div class="main-menu d-none d-lg-block">
							<nav>
								<ul id="navigation">
									<li><a href="index.html">??????</a></li>
									<li><a href="shop.html">?????????</a></li>
									<li class="hot"><a href="about.html">??????</a></li>
									<li><a href="#">?????????</a>
										<ul class="submenu">
											<li><a href="/notice/list.do"> ????????????</a></li>
											<li><a href="/normal/list.do"> ???????????????</a></li>
											<li><a href="/question/list.do"> ??????</a></li>
											<li><a href="/qna/list.do"> ??????????????????</a></li>
										</ul></li>
									<li><a href="blog.html">?????? ????????????</a>
										<ul class="submenu">
											<li><a href="blog.html">??????</a></li>
											<li><a href="blog-details.html">??????</a></li>
											<li><a href="blog-details.html">??????</a></li>
										</ul></li>
									<li><a href="#">shop</a>
										<ul class="submenu">
											<li><a href="login.html">??????</a></li>
											<li><a href="cart.html">??????</a></li>
											<li><a href="elements.html">??????</a></li>
											<li><a href="confirmation.html">??????</a></li>
											<li><a href="checkout.html">????????????</a></li>
										</ul></li>
								</ul>
							</nav>
						</div>
						<!-- Header Right -->
						<div class="header-right">
							<ul>
								<li>
									<div class="nav-search search-switch">
										<span class="flaticon-search" style="font-size: 25px;"></span>
									</div>
								</li>			
								<li><a href="cart.html"><span class="flaticon-shopping-cart" style="font-size: 25px;"></span></a></li>
								<c:set var="userVO" value="${userVO}" />
								<c:choose>
								    <c:when test="${ !empty  userVO}">
								      	<li><a href="/myPage.do"><span class="flaticon-user" style="font-size: 25px;"></span></a></li>
										<li><a href="/login/logout/action.do" class="genric-btn danger circle">Logout</a></li>
								    </c:when>
								 
								    <c:when test="${empty  userVO}">
								        <li><a href="/login.do" class="genric-btn danger circle">Login</a></li>
								    </c:when>		 
								</c:choose>

							</ul>
						</div>
					</div>
					<!-- Mobile Menu -->
					<div class="col-12">
						<div class="mobile_menu d-block d-lg-none"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- Header End -->
	</header>
	<main> <!-- Hero Area Start-->
	<div class="slider-area ">
		<div class="single-slider slider-height2 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap text-center">
							<h2>${title}</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<section class="login_part section_padding ">
		<div class="container">