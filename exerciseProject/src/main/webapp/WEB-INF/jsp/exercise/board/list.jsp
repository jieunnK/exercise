<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<form id="view" name = "view" method="post">
	<input type="hidden" name="userPassword" id= "userPassword"/>
</form>
<div class="section-top-border" >
	<h3 class="mb-30">${title}</h3>
	<c:if test="${boardType != 'question'}">
		<form acrion="/${boardType }/list.do" method="post">
			<div class="form-group">
		    	<div class="input-group mb-3">
		        	<div class="default-select" id="default-select">
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
    </c:if>
	<div class="progress-table-wrap" style="overflow:hidden;">
		<div class="progress-table">
			<div class="table-head">
				<div class="serial">번호</div>
				<div class="serial">구분</div>
				<div class="percentage">제목</div>
				<div class="visit">작성자</div>
				<div class="visit">작성일</div>
				<div class="visit">조회수</div>
				<c:if test="${boardType eq 'question'}">
					<div class="visit">상태</div>
				</c:if>
			</div>
			<c:set var="rowNum" value="${pageVO.totalCount-(pageVO.page-1)*pageVO.rowCount }" />
			<c:if test="${empty list}">
				<div style="text-align: center;">
				등록(검색)된 정보가 없습니다.
				</div>
			</c:if>
			<c:forEach var="row" items="${list}">
				<c:set var="viewUrl" value="./${row.boardUid}/view.do?${pageVO.pagingParam}" />
				<div class="table-row">
					<div class="serial">${rowNum}</div>
					<div class="serial">${'1' eq row.boardDivision ? '공지' : '일반'}</div>
					<div class="percentage">
						<c:choose> 
							<c:when test="${row.dataSecret eq 'Y'}">
								<a href="#" style="color: #000000;" onclick="passCheck('${row.boardUid}'); return false;">${row.dataTitle }</a>
							</c:when> 
							<c:otherwise>
								<a href="${viewUrl }" style="color: #000000;">${row.dataTitle }</a>
							</c:otherwise> 
						</c:choose> 		
					</div>
					<div class="visit">${row.registerId}</div>
					<div class="visit">${row.simpleRegisterDt}</div>
					<div class="visit">${row.viewCount }</div>
					<c:if test="${boardType eq 'question'}">
						<div class="visit">${row.tmpField1 }</div>
					</c:if>
				</div>
				<c:if test="${boardType eq 'qna' }">
					<div style="text-align: center;">
					${row.dataContent }
					</div>
				</c:if>
				<c:set var="rowNum" value="${rowNum-1}" />	
			</c:forEach>
		</div>
	</div>
</div>
<hr/>

<c:if test="${ '9' eq userVO.userLevel || ('1' != pageVO.boardType && '4' != pageVO.boardType ) }">
	<div class="form-group-ri" style="text-align: right;">
		<a href="/${boardType}/write.do" class="genric-btn danger radius">글쓰기</a>
	</div>
</c:if>			
<nav class="blog-pagination justify-content-center d-flex">
	<ul class="pagination">
		${paging}         
	</ul>
</nav>
<script>
	function passCheck(boardUid){
		var inputString = prompt('비밀글 입니다. \r비밀번호를 입력해주세요.'); 
		
		$("#userPassword").val(inputString);
			
		var theForm = document.view;
		
		theForm.action = "/${boardType}/"+boardUid+"/view.do?${pageVO.pagingParam}";
		
		if(inputString != null && inputString != ""){
			theForm.submit();
		}
	}
</script>
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>