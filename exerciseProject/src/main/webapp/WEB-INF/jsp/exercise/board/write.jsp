<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<div class="col-lg-15 col-md-15">
	<h3 class="mb-30">${title }</h3>
	<form:form commandName="dataVO" name="dataVO" action="/${boardType}/${mode}/action.do" method="post" novalidate="novalidate">
		<c:if test="${mode eq 'update' }">
			<form:hidden path="boardUid" id="boardUid" />
		</c:if>
		<c:if test="${mode eq 'reply' || division == 2 }">
			<form:hidden path="dataDep" id="dataDep" />
			<input type="hidden" name="viewUid" id="viewUid" value="${viewUid}"/>
			<input type="hidden" name="division" id="division" value="${division}"/>
		</c:if>
		<div class="col-md-2 form-group p_star">
			<label for="dataTtle">제목</label>
		</div>
		<c:choose>
			<c:when test="${boardType eq 'question' && mode != 'reply' && division != 2}">
				<div class="col-md-6 form-group p_star">
					<form:input path="dataTitle" class="form-control" id="dataTtle" placeholder="제목을 입력해주세요." />
				</div>
				<div class="col-md-3 form-group p_star">
					<div class="creat_account">
		                <label for="dataSecret">비밀글설정</label>
		                <form:checkbox path="dataSecret" id="dataSecret" value="Y"/>
		            </div>
	            </div>	
			</c:when>
			<c:otherwise>
				<div class="col-md-9 form-group p_star">
					<form:input path="dataTitle" class="form-control" id="dataTtle" placeholder="제목을 입력해주세요." />
				</div>
			</c:otherwise>
		</c:choose>
		
		<div class="col-md-2 form-group p_star">
			<label for="dataTtle">작성자</label>
		</div>
		<div class="col-md-9 form-group p_star">
			<form:input path="userNicName" class="form-control" id="userNicName" placeholder="이름을 입력해주세요." />
		</div>
		
		<c:if test="${empty userVO && mode != 'reply' && division != 2}">
			<div class="col-md-2 form-group p_star">
			<label for="dataTtle">글 비밀번호</label>
			</div>
			<div class="col-md-9 form-group p_star">
				<input type="text" class="form-control" name = "userPassword" id="userPassword" placeholder="비밀번호를 입력해주세요."/>
			</div>
		</c:if>
		<c:if test="${'9' eq userVO.userLevel}">
			<div class="col-md-2 form-group p_star">
				<label for="dataTtle">공지글 설정</label>
			</div>
			<div class="col-md-2 form-group p_star">
				<div class="radion_btn">
					<form:radiobutton path="boardDivision" id="boardDivision" value="1" onchange="objChg(this.value);"/>
					<label for="boardDivision">사용</label>
					<div class="check"></div>
				</div>
			</div>
			<div class="col-md-2 form-group p_star">
				<div class="radion_btn">
					<form:radiobutton path="boardDivision" id="boardDivision2" value="2" onchange="objChg(this.value);"/>
					<label for="boardDivision2">미사용</label>
					<div class="check"></div>
				</div>
			</div>
			<div class="col-md-5 form-group p_star"></div>
			<div id="noticeDivision" ${mode eq 'update' && !empty dataVO.beginDate && !empty dataVO.endDate ? "" : "style='display: none;'"} >
				<div class="col-md-2 form-group p_star">
					<label for="dataTtle">기간설정</label>
				</div>
				<div class="col-md-2 form-group p_star">
					<form:input path="beginDateStr" class="form-control" id="beginDateStr" placeholder="시작날짜." onClick="showCalendar('beginDateStr');" />
				</div>
				<div class="col-md-1 form-group p_star" style="text-align: center;">~</div>
				<div class="col-md-2 form-group p_star">
					<form:input path="endDateStr" class="form-control" id="endDateStr" placeholder="종료날짜." onClick="showCalendar('endDateStr');"/>
				</div>
				<div class="col-md-4 form-group p_star"></div>
			</div>
		</c:if>
		<div class="col-md-1 form-group p_star" style="margin-bottom: -15px;">
			<label for="message">내용</label>
		</div>
		<div class="col-md-12 form-group p_star">
			<form:textarea path="dataContent" class="form-control" id="dataContent" rows="1" placeholder="내용을 입력해주세요." style="margin-top: 15px; margin-bottom: 0px; height: 359px;" />				
		</div>
	</form:form>

	<div style="padding: 50px 30px 10px 60px; text-align: center;">
		<c:if test="${mode eq 'write' || mode eq 'reply'}">
			<a class="genric-btn danger-border radius" href="./list.do?${pageVO.pagingParam}" style="margin-right: 10px;">목록</a>
			<a class="genric-btn danger radius" href="#" onclick="postForm(); return false;">등록</a>
		</c:if>
		<c:if test="${mode eq 'update' }">
			<a class="genric-btn danger-border radius" href="/${boardType}/list.do?${pageVO.pagingParam}" style="margin-right: 10px;">목록</a>
			<a class="genric-btn danger radiuse" href="#" onclick="postForm(); return false;">수정</a>
		</c:if>
	</div>
</div>

<script>
	$(document).ready(function(){	
		// 달력 선택
	    $( "#beginDateStr,#endDateStr").datepicker({
			dateFormat: 'yy-mm-dd',
		    prevText: '이전 달',
		    nextText: '다음 달',
		    monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		    dayNames: ['일','월','화','수','목','금','토'],
		    dayNamesShort: ['일','월','화','수','목','금','토'],
		    dayNamesMin: ['일','월','화','수','목','금','토'],
		    showMonthAfterYear: true,
		    yearSuffix: '년',
		    yearRange: 'c-99:c+0',
		    /* maxDate : 0, */
			changeYear			: true,			// 년별로 선택
			changeMonth			: true,			// 달별로 선택		
		});
	});
	
	//달려보이기
	function showCalendar(field) {
		$("#" + field).focus();

	} 
	
	function objChg(value){
		if(value == 1){
			$("#noticeDivision").show();
		}else{
			$("#noticeDivision").hide();
		}
	}
	
	//폼ajax 설정
	function postForm() {
		if(!postRequest()) return false;
		$.ajax({
			url		: $("#dataVO").attr("action"),
			type	: "POST",
			async	: false,
			cache	: false,
			data	: $("#dataVO").serialize(),
		    dataType: "json",
			error : function(request){
		        alert("처리중에 문제가 발생하였습니다.") ;
		        console.log("문제가 발생했습니다.\n잠시후 다시 시도해 주세요.\n상태코드 : " + request.status);
		    },
		    success : postResponse
		});
	}


	//폼처리전
	function postRequest() {
		var modeStr = "등록";	
		<c:if test="${mode eq 'update'}">
		modeStr = "수정";
		</c:if>
		if(confirm("해당정보로 "+modeStr+" 하시겠습니까?")){		
			return true;
		}else{
			return false;
		}
		
	}
	//폼처리후
	function postResponse(jsonResult, statusText, xhr, $form)  {
		
		if(jsonResult.code === 999){	
			alert(jsonResult.message);
		}else if(jsonResult.code === 555){	
			alert(jsonResult.message);
			if(typeof jsonResult.name != "undefined" && jsonResult.name != ""){

				$("#dataVO [name=\""+jsonResult.name+"\"]").focus();
			}
		}else if(jsonResult.code === 200){
			alert(jsonResult.message);
			window.location = jsonResult.url;
		}else if(jsonResult.code === 998){
			alert(jsonResult.message);
			window.location = jsonResult.url;
		}else{
			alert("요청 처리에 실패하였습니다.");
		}	
	}
</script>
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>