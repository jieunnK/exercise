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
				<form acrion="/notice/list.do" method="post">
					<div class="form-group">
	                	<div class="input-group mb-3">
	                    	<div class="default-select" id="default-select" "="">
								<select name="searchType" style="display: none;" >
									<option value="" ${pageVO.searchType eq '' ? "selected='selected'" : ""}>선택</option>
									<option value="dataTitle" ${pageVO.searchType eq 'dataTitle' ? "selected='selected'" : ""}>제목</option>
									<option value="dataContent" ${pageVO.searchType eq 'dataContent' ? "selected='selected'" : ""}>내용</option>
									<option value="registerId" ${pageVO.searchType eq 'registerId' ? "selected='selected'" : ""}>작성자</option>
								</select>
								<div class="nice-select" tabindex="0">
									<span class="current">
										${pageVO.searchType eq 'dataTitle' ? "제목" : pageVO.searchType eq 'dataContent' ? "내용" : pageVO.searchType eq 'registerId' ? "작성자" : "선택"}
									</span>
									<ul class="list">
										<li data-value="" ${pageVO.searchType eq '' ? "class='option focus selected'" : "class='option'"}>선택</li>
										<li data-value="dataTitle" ${pageVO.searchType eq 'dataTitle' ? "class='option focus selected'" : "class='option'"}>제목</li>
										<li data-value="dataContent" ${pageVO.searchType eq 'dataContent' ? "class='option focus selected'" : "class='option'"}>내용</li>
										<li data-value="registerId" ${pageVO.searchType eq 'registerId' ? "class='option focus selected'" : "class='option'"}>작성자</li>
									</ul>
								</div>
							</div>
	                        <input type="text" class="form-control" name ="keyword" placeholder="Search Keyword" onfocus="this.placeholder = ''" onblur="this.placeholder = 'Search Keyword'" style="margin-left: 10px;" value= "${pageVO.keyword}">
	                        <div class="input-group-append">
	                           	<button class="btns" type="submit" style="border-color: transparent;">
	                           		<i class="ti-search"></i>
	                           	</button>
	                       </div>
	                   </div>
	                </div>
                </form>
				<div class="progress-table-wrap" style="overflow:hidden;">
					<div class="progress-table">
						<div class="table-head">
							<div class="serial">번호</div>
							<div class="percentage">제목</div>
							<div class="visit">작성자</div>
							<div class="visit">작성일</div>
							<div class="visit">조회수</div>
						</div>
						<c:set var="rowNum" value="${pageVO.totalCount-(pageVO.page-1)*pageVO.rowCount }" />
						<c:if test="${empty list}">
							<div class="table-row">
							등록(검색)된 정보가 없습니다.
							</div>
						</c:if>
						<c:forEach var="row" items="${list}">
							<c:set var="viewUrl" value="./${row.boardUid}/view.do?${pageVO.pagingParam}" />
							<div class="table-row">
								<div class="serial">${rowNum}</div>
								<div class="percentage"><a href="${viewUrl }" style="color: #000000;">${row.dataTitle }</a></div>
								<div class="visit">${row.registerId}</div>
								<div class="visit">${row.simpleRegisterDt}</div>
								<div class="visit">${row.viewCount }</div>
							</div>
							<c:set var="rowNum" value="${rowNum-1}" />	
						</c:forEach>
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
	        		${paging}         
	            </ul>
	        </nav>
		</div>
	</section>
</main>
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>