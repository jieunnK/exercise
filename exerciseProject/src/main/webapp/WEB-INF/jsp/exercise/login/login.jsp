
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/exercise/include/header.jsp"%>
<div class="row align-items-center">
	<div class="col-lg-6 col-md-6">
		<div class="login_part_text text-center">
			<div class="login_part_text_iner">
				<h2>회원이 아니십니까?</h2>
				<p>회원가입 하시면 여러가지 서비스를 이용하실 수 있습니다.</p>
				<a href="/signUP/write.do" class="btn_3">회원가입</a>
			</div>
		</div>
	</div>
	<div class="col-lg-6 col-md-6">
		<div class="login_part_form">
			<div class="login_part_form_iner">
				<h3>
					Welcome Back ! <br> Please Sign in now
				</h3>
				<form class="row contact_form" action="/login/${mode}/action.do" method="post" novalidate="novalidate">
					<input type="hidden" name="returnUrl" value="${returnUrl }"/>
					<div class="col-md-12 form-group p_star">
						<input type="text" class="form-control" id="userID" name="userID" value="" placeholder="Username">
					</div>
					<div class="col-md-12 form-group p_star">
						<input type="password" class="form-control" id="userPassword" name="userPassword" value="" placeholder="Password">
					</div>
					<div class="col-md-12 form-group">
						<div class="creat_account d-flex align-items-center">
							<input type="checkbox" id="f-option" name="selector"> 
								<label for="f-option">Remember me</label>
						</div>
						<button type="submit" value="submit" class="btn_3">login</button>
						<a class="lost_pass" href="#">forget password?</a>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/jsp/exercise/include/bottom.jsp"%>