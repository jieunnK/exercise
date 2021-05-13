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
		<div class="col-lg-15 col-md-15">
            <div class="row justify-content-center">
                <div class="col-lg-8">
	                <div class="single_product_text text-center" style="margin-bottom: 100px;">
	                    <h3 style="margin-bottom: 20px;">${boardVO.dataTitle }</h3>
	                    <p style="margin-bottom: 80px;">
	                    <c:if test="${!empty boardVO.beginDate && !empty boardVO.endDate}">
	                    	기간 : ${boardVO.simpleBeginDt} ~ ${boardVO.simpleEndDt} ｜  
						</c:if> 
	                   	 작성자 : ${boardVO.registerId } ｜ 등록일 : ${boardVO.simpleRegisterDt } ｜ 조회수 : ${boardVO.viewCount}
	                    </p>
	                    <p>
	                        ${boardVO.dataContent}
	                    </p>                
	                </div>
	                <div class="form-group-ri" style="text-align: center;">
	                	<c:if test="${ '9' eq userVO.userLevel }">
							<a href="/notice/${boardVO.boardUid}/update.do?${pageVO.pagingParam}" class="genric-btn danger-border radius">수정</a>
						</c:if>	
	                	
			        	<a href="/notice/list.do?${pageVO.pagingParam}" class="genric-btn danger radius">목록</a>
					</div>  
             	</div>    	          
            </div>                         
        </div>       	 
    </div>
</section>
</main>		
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>
