package egovframework.model.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class UserVO {
	String userUid;
	String userID;
	String userPassword;
	String userPassword2; //비밀번호 확인
	String userName;
	String userBirth;
	String userBirthD; // 1 : 양력 , 2 : 음력
	String userPhone;
	String userEmail;
	String email;
	String email2;
	String userGender;  // 1 : 남자 , 2 : 여자
	String zipCode;
	String userAddr;
	String userAddr2;
	String userCheck; // 1 : 인증 , 2 : 미인증
	String userLevel; // 1 : 기본 , 3 : 정회원, 9 : 매니져
	Date register_dt; 
	String register_ID;
	Date update_dt;
	String update_ID;
	String tmpField1;
	String tmpField2;
	String tmpField3;
	String check;
	
	public String getUserUid() {
		return userUid;
	}

	public void setUserUid(String userUid) {
		this.userUid = userUid;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	public String getUserPassword2() {
		return userPassword2;
	}

	public void setUserPassword2(String userPassword2) {
		this.userPassword2 = userPassword2;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserBirth() {
		return userBirth;
	}

	public void setUserBirth(String userBirth) {
		this.userBirth = userBirth;
	}

	public String getUserBirthD() {
		return userBirthD;
	}

	public void setUserBirthD(String userBirthD) {
		this.userBirthD = userBirthD;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserGender() {
		return userGender;
	}

	public void setUserGender(String userGender) {
		this.userGender = userGender;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getUserAddr2() {
		return userAddr2;
	}

	public void setUserAddr2(String userAddr2) {
		this.userAddr2 = userAddr2;
	}

	public String getUserCheck() {
		return userCheck;
	}

	public void setUserCheck(String userCheck) {
		this.userCheck = userCheck;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public Date getRegister_dt() {
		return register_dt;
	}

	public void setRegister_dt(Date register_dt) {
		this.register_dt = register_dt;
	}

	public String getRegister_ID() {
		return register_ID;
	}

	public void setRegister_ID(String register_ID) {
		this.register_ID = register_ID;
	}

	public Date getUpdate_dt() {
		return update_dt;
	}

	public void setUpdate_dt(Date update_dt) {
		this.update_dt = update_dt;
	}

	public String getUpdate_ID() {
		return update_ID;
	}

	public void setUpdate_ID(String update_ID) {
		this.update_ID = update_ID;
	}

	public String getTmpField1() {
		return tmpField1;
	}

	public void setTmpField1(String tmpField1) {
		this.tmpField1 = tmpField1;
	}

	public String getTmpField2() {
		return tmpField2;
	}

	public void setTmpField2(String tmpField2) {
		this.tmpField2 = tmpField2;
	}

	public String getTmpField3() {
		return tmpField3;
	}

	public void setTmpField3(String tmpField3) {
		this.tmpField3 = tmpField3;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	
	public String getCheck() {
		return check;
	}

	public void setCheck(String check) {
		this.check = check;
	}

	public String getSimplerDt(Date today) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(today);
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
