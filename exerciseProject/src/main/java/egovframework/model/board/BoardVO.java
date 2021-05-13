package egovframework.model.board;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.model.common.PageVO;

public class BoardVO extends PageVO{
	String boardUid;
	String dataTitle;
	String dataContent;
	Date registerDt;
	String registerId;
	Date updateDt;
	String updateId;
	Date beginDate;
	Date endDate;
	String dataSecret;
	String boardType;
	String boardDivision;
	String dataDep;
	String boardNumberfile;
	String category1;
	String category2;
	String category3;
	String tmpField1;
	String tmpField2;
	String tmpField3;
	String tmpField4;
	String tmpField5;
	int viewCount = 0;
	String beginDateStr;
	String endDateStr;
	String simpleRegisterDt;
	String simpleBeginDt;
	String simpleEndDt;
	
	public String getBoardUid() {
		return boardUid;
	}
	public void setBoardUid(String boardUid) {
		this.boardUid = boardUid;
	}
	public String getDataTitle() {
		return dataTitle;
	}
	public void setDataTitle(String dataTitle) {
		this.dataTitle = dataTitle;
	}
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}
	public Date getRegisterDt() {
		return registerDt;
	}
	public void setRegisterDt(Date registerDt) {
		this.registerDt = registerDt;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public Date getUpdateDt() {
		return updateDt;
	}
	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}
	public String getUpdateId() {
		return updateId;
	}
	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getDataSecret() {
		return dataSecret;
	}
	public void setDataSecret(String dataSecret) {
		this.dataSecret = dataSecret;
	}
	public String getBoardType() {
		return boardType;
	}
	public void setBoardType(String boardType) {
		this.boardType = boardType;
	}
	public String getBoardDivision() {
		return boardDivision;
	}
	public void setBoardDivision(String boardDivision) {
		this.boardDivision = boardDivision;
	}
	public String getDataDep() {
		return dataDep;
	}
	public void setDataDep(String dataDep) {
		this.dataDep = dataDep;
	}
	public String getBoardNumberfile() {
		return boardNumberfile;
	}
	public void setBoardNumberfile(String boardNumberfile) {
		this.boardNumberfile = boardNumberfile;
	}
	public String getCategory1() {
		return category1;
	}
	public void setCategory1(String category1) {
		this.category1 = category1;
	}
	public String getCategory2() {
		return category2;
	}
	public void setCategory2(String category2) {
		this.category2 = category2;
	}
	public String getCategory3() {
		return category3;
	}
	public void setCategory3(String category3) {
		this.category3 = category3;
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
	public String getTmpField4() {
		return tmpField4;
	}
	public void setTmpField4(String tmpField4) {
		this.tmpField4 = tmpField4;
	}
	public String getTmpField5() {
		return tmpField5;
	}
	public void setTmpField5(String tmpField5) {
		this.tmpField5 = tmpField5;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	
	public String getBeginDateStr() {
		return beginDateStr;
	}
	public void setBeginDateStr(String beginDateStr) {
		this.beginDateStr = beginDateStr;
	}
	public String getEndDateStr() {
		return endDateStr;
	}
	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}
	
	public String getSimpleRegisterDt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return registerDt != null ? format.format(registerDt) : "";	
	}
	
	public String getSimpleBeginDt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return beginDate != null ? format.format(beginDate) : "";	
	}
	
	public String getSimpleEndDt() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return endDate != null ? format.format(endDate) : "";	
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
