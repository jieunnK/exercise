<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<script>
//삭제폼
function postForm(division) {
	
	if(!postRequest(division)) return false;
	var tmp = $("#boardVO").attr("action");
	var data = $("#boardVO").serialize();
	if(division == 'reply'){tmp = $("#replyData").attr("action"); data = $("#replyData").serialize()}
	$.ajax({
		url		: tmp,
		type	: "POST",
		async	: false,
		cache	: false,
		data	: data,
	    error : function(request){
	        alert("처리중에 문제가 발생하였습니다.") ;
	        console.log("문제가 발생했습니다.\n잠시후 다시 시도해 주세요.\n상태코드 : " + request.status);
	    },
	    success : postResponse
	});
}
//삭제폼처리전
function postRequest(division) {
	if(division == 'reply'){
		if(confirm("해당답변를 삭제 하시겠습니까?")){
			
			return true;
		}else{
			return false;
		}
	}else{
		if(confirm("해당정보를 삭제 하시겠습니까?")){
			var inputString = prompt('비밀번호를 입력해주세요.'); 
			
			$("#seacretPassword").val(inputString);
			
			if(inputString != null && inputString != ''){
				return true;
			}else{
				return false;
			}
			
		}else{
			return false;
		}
	}
	
	
}
//폼처리후
function postResponse(jsonResult, statusText, xhr, $form)  {
	console.log(jsonResult);
	if(jsonResult.code === 999){	
		alert(jsonResult.message);
	}else if(jsonResult.code === 555){	
		alert(jsonResult.message);
		if(typeof jsonResult.name != "undefined" && jsonResult.name != ""){
			$("#boardVO [name=\""+jsonResult.name+"\"]").focus();
		}
	}else if(jsonResult.code === 200){
		alert(jsonResult.message);
		window.location = jsonResult.url;
	}else{
		alert("요청 처리에 실패하였습니다.");
	}	
}

function passCheck(){
	var inputString = prompt('비밀번호를 입력해주세요.'); 
	
	$("#userPassword").val(inputString);
	
	if(inputString != null && inputString != ""){
		$("#update").submit();
	}
}



</script>
<form:form commandName="boardVO" method="post" action="/${boardType}/delete/action.do">
 	<form:hidden path="boardUid" id="boardUid" />
	<form:hidden path="seacretPassword" id="seacretPassword" />
</form:form>

<form id="update" method="post" action="/${boardType }/${boardVO.boardUid}/update.do?${pageVO.pagingParam}">
	<input type="hidden" name ="userPassword" id="userPassword" />
</form>

<c:if test="${not empty replyData }">
	<form:form commandName="replyData" name="replyData" method="post" action="/${boardType }/delete/action.do?division=2&viewUid=${boardVO.boardUid}">
		<form:hidden path="boardUid" id="boardUid" />
	</form:form>
</c:if>


<div class="col-lg-15 col-md-15">
	<div class="row justify-content-center">
    	<div class="col-lg-8">
	    	<div class="single_product_text text-center" style="margin-bottom: 100px;">
	        	<h3 style="margin-bottom: 20px;">${boardVO.dataTitle }</h3>
	            <p style="margin-bottom: 80px;">
	            	<c:if test="${!empty boardVO.beginDate && !empty boardVO.endDate}">
	                    	기간 : ${boardVO.simpleBeginDt} ~ ${boardVO.simpleEndDt} ｜  
					</c:if> 
	                   	작성자 : ${boardVO.userNicName } ｜ 등록일 : ${boardVO.simpleRegisterDt } ｜ 조회수 : ${boardVO.viewCount}
	            </p>
	            <p>
	                 ${boardVO.dataContent}
	            </p>                
	        </div>
	        <div ${not empty replyData ? "style='display:block;'" : "style='display:none;'"}>
		        <hr>
		        <h4>답변</h4>
		        <div class="single_product_text text-center" style="margin-bottom: 80px;">
		        	<h5 style="margin-bottom: 10px;">${replyData.dataTitle }</h5>
		            <p style="margin-bottom: 50px;">
		                    작성자 : ${replyData.userNicName } ｜ 등록일 : ${replyData.simpleRegisterDt }
		            </p>
		            <p>
		                 ${replyData.dataContent}
		            </p>
		            <div style="text-align: right;">
		           	<c:if test="${userVO.userLevel eq '9'}">
						<a href="/${boardType}/${replyData.boardUid}/update.do?${pageVO.pagingParam}&division=2&viewUid=${boardVO.boardUid}" class="genric-btn primary">수정</a>
						<a href="#" class="genric-btn primary-border" onclick="postForm('reply'); return false;">삭제</a>
				 	</c:if> 
					</div>	            
		        </div>
		        <hr>
	        </div>
	        <div class="form-group-ri" style="text-align: center;">
	        	<c:if test="${ '9' eq userVO.userLevel || ('1' != boardVO.boardType && '4' != boardVO.boardType) }">
	        		<c:choose>
					    <c:when test="${empty boardVO.userPassword || userVO.userLevel eq '9'}">
					      	<a href="/${boardType }/${boardVO.boardUid}/update.do?${pageVO.pagingParam}" class="genric-btn danger-border radius">수정</a>
					    	<a href="#" class="genric-btn danger-border radius" onclick="$('#boardVO').submit(); return false;">삭제</a>
					    </c:when>
					    <c:when test="${!empty boardVO.userPassword && userVO.userLevel != '9'}">
					      	<a href="#" class="genric-btn danger-border radius" onclick="passCheck(); return false;">수정</a>
					    	<a href="#" class="genric-btn danger-border radius" onclick="postForm(2); return false;">삭제</a>
					    </c:when>			   
					</c:choose>
				</c:if>	
	          	<c:if test="${boardType eq 'question' && empty replyData}">
	            	<a href="/${boardType}/${boardVO.boardUid}/reply.do?${pageVO.pagingParam}&division=2&viewUid=${boardVO.boardUid}" class="genric-btn danger-border radius">답변</a>
	            </c:if>
			    <a href="/${boardType }/list.do?${pageVO.pagingParam}" class="genric-btn danger radius">목록</a>
			</div>  
         </div>    	          
     </div>                         
</div>       	 
	
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>
