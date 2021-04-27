<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<main> <!-- Hero Area Start-->
	<div class="slider-area ">
		<div class="single-slider slider-height2 d-flex align-items-center">
			<div class="container">
				<div class="row">
					<div class="col-xl-12">
						<div class="hero-cap text-center">
							<h2>공지사항</h2>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section class="checkout_area section_padding">
		<div class="container">
			<div class="section-top-border" >
				<h3 class="mb-30">공지사항</h3>
				<div class="progress-table-wrap" style="overflow:hidden;">
					<div class="progress-table">
						<div class="table-head">
							<div class="serial">번호</div>
							<div class="percentage">제목</div>
							<div class="visit">작성자</div>
							<div class="visit">작성일</div>
							<div class="visit">조회수</div>
						</div>
						<div class="table-row">
							<div class="serial">1</div>
							<div class="percentage">ㅇㅇㅇㅇㅇ</div>
							<div class="visit">홍길동</div>
							<div class="visit">2021-09-02</div>
							<div class="visit">1</div>
						</div>	
						</div>
				</div>
			</div>
			<hr/>
			<c:if test="${ '9' eq userVO.userLevel }">
				<div class="form-group-ri" style="text-align: right;">
		        	<a href="/notice/write.do" class="genric-btn danger radius">글쓰기</a>
				</div>
			</c:if>			
			<nav class="blog-pagination justify-content-center d-flex">
	        	<ul class="pagination">
	            	<li class="page-item">
	                	<a href="#" class="page-link" aria-label="Previous">
	                    	<i class="ti-angle-left"></i>
	                   	</a>
	                </li>
	                <li class="page-item">
	                   <a href="#" class="page-link">1</a>
	                </li>
	                <li class="page-item active">
	                	<a href="#" class="page-link">2</a>
	                </li>
	                <li class="page-item">
	                	<a href="#" class="page-link" aria-label="Next">
	                		<i class="ti-angle-right"></i>
	                	</a>
	                </li>
	            </ul>
	        </nav>
		</div>
	</section>
</main>
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>