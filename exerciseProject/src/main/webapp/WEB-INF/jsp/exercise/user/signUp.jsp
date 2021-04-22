<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<script type="text/javascript" src="https://ssl.daumcdn.net/dmaps/map_js_init/postcode.v2.js"></script>
<script>
//주소검색
function searchZipcode() 
{
	new daum.Postcode({
		oncomplete: function(data) 
		{
			$("#zipCode").val(data.zonecode);
			//$("#zipCode").attr("disabled",true); //활성화 
			$("#userAddr").val(data.address);
			//$("#address1").attr("disabled",true); //활성화 
			$("#userAddr2").focus();
		}
	}).open();
}

function idCheck(){
	if(!postRequest2()) return false;
	var allData = { "userID": $("#userID").val() };
	$.ajax({
		url		: "/signUP/${mode}/idCheck.do",
		type	: "POST",
		async	: false,
		cache	: false,
		data	: $("#userVO").serialize(),
	    dataType: "json",
		error : function(request){
	        alert("처리중에 문제가 발생하였습니다.") ;
	        console.log("문제가 발생했습니다.\n잠시후 다시 시도해 주세요.\n상태코드 : " + request.status);
	    },
	    success : postResponse2
	});
}

//폼처리후
function postResponse2(jsonResult, statusText, xhr, $form)  {
	console.log(jsonResult.code);
	if(jsonResult.code === 999){	
		alert(jsonResult.message);
		$("#check").val("false");
	}else if(jsonResult.code === 555){	
		alert(jsonResult.message);
		if(typeof jsonResult.name != "undefined" && jsonResult.name != ""){
			$("#userVO [name=\""+jsonResult.name+"\"]").focus();
		}
	}else if(jsonResult.code === 200){
		alert(jsonResult.message);
		$("#check").val("true");
		//$("#userID").attr("disabled",true);
	}else{
		alert("요청 처리에 실패하였습니다.");
	}	
}

//폼처리전
function postRequest2() {
	if(confirm("해당정보로 조회 하시겠습니까?")){		
		return true;
	}else{
		return false;
	}
	
}
//폼ajax 설정
function postForm() {
	if(!postRequest()) return false;
	$.ajax({
		url		: $("#userVO").attr("action"),
		type	: "POST",
		async	: false,
		cache	: false,
		data	: $("#userVO").serialize(),
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

			$("#userVO [name=\""+jsonResult.name+"\"]").focus();
		}
	}else if(jsonResult.code === 200){
		alert(jsonResult.message);
		window.location = jsonResult.url;
	}else{
		alert("요청 처리에 실패하였습니다.");
	}	
}

function emailChange(val){
	$("#email2").val(val);
}
</script>
<main> <!-- Hero Area Start-->
<div class="slider-area ">
	<div class="single-slider slider-height2 d-flex align-items-center">
		<div class="container">
			<div class="row">
				<div class="col-xl-12">
					<div class="hero-cap text-center">
						<h2>회원가입</h2>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!--================Checkout Area =================-->
<section class="checkout_area section_padding">
	<div class="container">
		<div class="billing_details">
			<div class="row">
				<div class="col-lg-8">
					<h3>회원가입 정보</h3>
					<form:form commandName="userVO" name="userVO" class="row contact_form" action="/signUP/${mode}/action.do" method="post" novalidate="novalidate">
						<c:if test="${mode eq 'update' }">
							<form:hidden path="userUid" id="userUid" />
						</c:if>
						<c:if test="${mode eq 'write' }">
							<input type="hidden" id="check" name = "check" value=""/>
						</c:if>			
													
						<div class="col-md-9 form-group p_star">
							<form:input path="userID" class="form-control" id="userID" placeholder="아이디"/>
						</div>
						
						<c:if test="${mode eq 'write' }">
							<div class="col-md-3 form-group p_star">
								<a class="btn_3" href="#" style="padding: 9px 18px;" onclick="idCheck(); return false;">중복체크</a>
							</div>
						</c:if>
						
						<div class="col-md-12 form-group p_star">
							<c:if test="${mode eq 'update' }">
								<form:password path="userPassword" class="form-control" id="userPassword" placeholder="비밀번호"/>
							</c:if>
							<input type="password" class="form-control" id="userPassword" name="userPassword" placeholder="비밀번호"/>
						</div>
						<div class="col-md-12 form-group p_star">
							<input type="password" class="form-control" id="userPassword2" name="userPassword2" placeholder="비밀번호 확인"/>
						</div>
						<div class="col-md-9 form-group p_star">
							<form:input path="zipCode" class="form-control" id="zipCode" placeholder="우편번호" />
						</div>
						<div class="col-md-3 form-group p_star">
							<a class="btn_3" href="#" onclick="searchZipcode(); return false;" style="padding: 9px 18px;">우편번호 검색</a>
						</div>
						<div class="col-md-6 form-group p_star">
							<form:input path="userAddr" class="form-control" id="userAddr" placeholder="주소" />
						</div>
						<div class="col-md-6 form-group p_star">
							<form:input path="userAddr2" class="form-control" id="userAddr2" placeholder="상세주소"/>
						</div>
						<div class="col-md-6 form-group p_star">
							<form:input path="userName" class="form-control" id="userName" placeholder="이름"/> 
						</div>
						<div class="col-md-3 form-group p_star">
							<div class="radion_btn">
								<form:radiobutton path="userGender" id="usergender1" value="1"/> 
								<label for="usergender1">남자</label>
								<div class="check"></div>
							</div>
						</div>
						<div class="col-md-3 form-group p_star">
							<div class="radion_btn">
								<form:radiobutton path="userGender" id="usergender2" value="2"/> 
								<label for="usergender2">여자</label>
								<div class="check"></div>
							</div>
						</div>
						<div class="col-md-12 form-group p_star">
							<form:input path="userPhone" class="form-control" id="userPhone" placeholder="휴대폰번호"/> 
						</div>
						<div class="col-md-6 form-group p_star">
							<form:input path="userBirth" class="form-control" id="userBirth" placeholder="생년월일"/> 
						</div>
						<div class="col-md-3 form-group p_star">
							<div class="radion_btn">
								<form:radiobutton path="userBirthD" id="userBirthD" value="1"/> 
								<label for="userBirthD">양력</label>
								<div class="check"></div>
							</div>	
						</div>
						<div class="col-md-3 form-group p_star">
							<div class="radion_btn">
								<form:radiobutton path="userBirthD" id="userBirthD2" value="2"/> 
								<label for="userBirthD2">음력</label>
								<div class="check"></div>
							</div>
						</div>		
						<div class="col-md-3 form-group p_star">
							<c:if test="${mode eq 'update' }">
								<input type="text" class="form-control" id="userEmail" name= "userEmail" placeholder="이메일" value="${email1}" /> 
							</c:if>
							<input type="text" class="form-control" id="userEmail" name= "userEmail" placeholder="이메일"/> 
						</div>
						@
						<div class="col-md-3 form-group p_star">
							<c:if test="${mode eq 'update' }">
								<input type="text" class="form-control" id="email" name= "email" placeholder="이메일" value="${email2}" /> 
							</c:if>
							<input type="text" class="form-control" id="email2" name= "email2" placeholder="이메일"/> 
						</div>
						<div class="col-md-4 form-group p_star">
	                      <select class="country_select" name="email3" onchange="emailChange(this.value);">
	                        <option value="">직접입력</option>
	                        <option value="naver.com" ${'naver.com' eq email2 ? 'selected="selected"' : ''}>naver.com</option>
	                        <option value="daum.net" ${'daum.net' eq email2 ? 'selected="selected"' : ''}>daum.net</option>
	                      </select>
	                    </div>
					</form:form>
					<div style="padding: 50px 30px 10px 60px;text-align: center;">
						<c:if test="${mode eq 'write' }">
							<a class="btn_3" href="javascript:window.history.back();" style="margin-right: 10px;">뒤로가기</a>					
							<a class="btn_3" href="#" onclick="postForm(); return false;">회원가입</a>
						</c:if>					
						<c:if test="${mode eq 'update' }">
							<a class="btn_3" href="javascript:window.history.back();" style="margin-right: 10px;">뒤로가기</a>					
							<a class="btn_3" href="#" onclick="postForm(); return false;">수정완료</a>
						</c:if>		
						
					</div>
				</div>
			</div>
		</div>
	</div>
</section>
<!--================End Checkout Area =================--> </main>
<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>