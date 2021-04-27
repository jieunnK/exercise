package egovframework.model.common;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

public class PageVO {
	private String searchType;
	private String keyword;
	private String searchType1;
	private String keyword1;
	private String searchType2;
	private String keyword2;
	private String dateType;
	private int page= 1;
	private int topPage= 1;
	private int subPage= 1;
	private int rowCount=10;
	private long totalCount=0;
	private int totalPage=0;
	private int startRow = 0;
	private String order;
	private String asc;
	private String schBeginDate;
	private String schEndDate;
	private String schRegisterDt;  //생성일 기준 검색용1
	private String schRegisterDt2; //생성일 기준 검색용2
	private String etcParamStr="";
	private String etcParam1="";
	private String paramStr;
	
	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
	public String getSearchType1() {
		return searchType1;
	}
	public void setSearchType1(String searchType1) {
		this.searchType1 = searchType1;
	}
	public String getKeyword1() {
		return keyword1;
	}
	public void setKeyword1(String keyword1) {
		this.keyword1 = keyword1;
	}	
	public String getSearchType2() {
		return searchType2;
	}
	public void setSearchType2(String searchType2) {
		this.searchType2 = searchType2;
	}
	public String getKeyword2() {
		return keyword2;
	}
	public void setKeyword2(String keyword2) {
		this.keyword2 = keyword2;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public String getEtcParamStr() {
		return etcParamStr;
	}
	public void setEtcParamStr(String etcParamStr) {
		this.etcParamStr = etcParamStr;
	}
	
	public String getEtcParam1() {
		return etcParam1;
	}
	public void setEtcParam1(String etcParam1) {
		this.etcParam1 = etcParam1;
	}
	
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public String getAsc() {
		return asc;
	}
	public void setAsc(String asc) {
		this.asc = asc;
	}	
	public String getSchBeginDate() {
		return schBeginDate;
	}
	public void setSchBeginDate(String schBeginDate) {
		this.schBeginDate = schBeginDate;
	}
	public String getSchEndDate() {
		return schEndDate;
	}
	public void setSchEndDate(String schEndDate) {
		this.schEndDate = schEndDate;
	}
	public String getSchRegisterDt() {
		return schRegisterDt;
	}
	public void setSchRegisterDt(String schRegisterDt) {
		this.schRegisterDt = schRegisterDt;
	}
	public String getSchRegisterDt2() {
		return schRegisterDt2;
	}
	public void setSchRegisterDt2(String schRegisterDt2) {
		this.schRegisterDt2 = schRegisterDt2;
	}
	public int getTopPage() {
		return topPage;
	}
	public void setTopPage(int topPage) {
		this.topPage = topPage;
	}	
	public int getSubPage() {
		return subPage;
	}
	public void setSubPage(int subPage) {
		this.subPage = subPage;
	}
	//기본적인 검색어,키값 형식의 파라미터 반환
	public String getSearchParam() throws UnsupportedEncodingException{
		String paramStr = "";
		if(keyword!=null && !"".equals(keyword) && searchType!=null && !"".equals(searchType)){
			paramStr += "&searchType="+searchType+"&keyword="+URLEncoder.encode(keyword,"UTF-8");
		}
		if(keyword1!=null && !"".equals(keyword1) && searchType1!=null && !"".equals(searchType1)){
			paramStr += "&searchType1="+searchType1+"&keyword1="+URLEncoder.encode(keyword1,"UTF-8");
		}
		if(keyword2!=null && !"".equals(keyword2) && searchType2!=null && !"".equals(searchType2)){
			paramStr += "&searchType2="+searchType2+"&keyword2="+URLEncoder.encode(keyword2,"UTF-8");
		}
		if(order!=null && !"".equals(order)){
			paramStr += "&order="+order;
		}
		if(asc!=null && !"".equals(asc)){
			paramStr += "&asc="+asc;
		}
		if(etcParam1!=null && !"".equals(etcParam1)){
			paramStr += "&etcParam1="+etcParam1;
		}
		if(schBeginDate!=null && !"".equals(schBeginDate)){
			paramStr += "&schBeginDate="+schBeginDate;
		}
		if(schEndDate!=null && !"".equals(schEndDate)){
			paramStr += "&schEndDate="+schEndDate;
		}
		if(schRegisterDt!=null && !"".equals(schRegisterDt)){
			paramStr += "&schRegisterDt="+schRegisterDt;
		}
		if(schRegisterDt2!=null && !"".equals(schRegisterDt2)){
			paramStr += "&schRegisterDt2="+schRegisterDt2;
		}
		if(rowCount>10){
			paramStr += "&rowCount="+rowCount;
		}
		if(dateType!=null && !"".equals(dateType)){
			paramStr += "&dateType="+dateType;
		}
		return paramStr+etcParamStr;
	}
	//페이지번호 포함된 파라미터 반환 앞은 공백처리 ?,& 상황에 따라 임의로 적용
	public String getPagingParam() throws UnsupportedEncodingException{
		String paramStr = "";
		paramStr += "page="+String.valueOf(page);
		if(keyword!=null && !"".equals(keyword) && searchType!=null && !"".equals(searchType)){
			paramStr += "&searchType="+searchType+"&keyword="+URLEncoder.encode(keyword,"UTF-8");
		}
		if(keyword1!=null && !"".equals(keyword1) && searchType1!=null && !"".equals(searchType1)){
			paramStr += "&searchType1="+searchType1+"&keyword1="+URLEncoder.encode(keyword1,"UTF-8");
		}
		if(keyword2!=null && !"".equals(keyword2) && searchType2!=null && !"".equals(searchType2)){
			paramStr += "&searchType2="+searchType2+"&keyword2="+URLEncoder.encode(keyword2,"UTF-8");
		}
		if(order!=null && !"".equals(order)){
			paramStr += "&order="+order;
		}
		if(asc!=null && !"".equals(asc)){
			paramStr += "&asc="+asc;
		}
		if(etcParam1!=null && !"".equals(etcParam1)){
			paramStr += "&etcParam1="+etcParam1;
		}
		if(schBeginDate!=null && !"".equals(schBeginDate)){
			paramStr += "&schBeginDate="+schBeginDate;
		}
		if(schEndDate!=null && !"".equals(schEndDate)){
			paramStr += "&schEndDate="+schEndDate;
		}
		if(schRegisterDt!=null && !"".equals(schRegisterDt)){
			paramStr += "&schRegisterDt="+schRegisterDt;
		}
		if(schRegisterDt2!=null && !"".equals(schRegisterDt2)){
			paramStr += "&schRegisterDt2="+schRegisterDt2;
		}
		if(rowCount>10){
			paramStr += "&rowCount="+rowCount;
		}
		if(dateType!=null && !"".equals(dateType)){
			paramStr += "&dateType="+dateType;
		}
		return paramStr+etcParamStr;
	}
	//기본적인 검색어,키값 형식에 정렬파람 뺀 데이터 반환
	public String getNotOrderSearchParam() throws UnsupportedEncodingException{
		String paramStr = "";
		if(keyword!=null && !"".equals(keyword) && searchType!=null && !"".equals(searchType)){
			paramStr += "&searchType="+searchType+"&keyword="+URLEncoder.encode(keyword,"UTF-8");
		}
		if(keyword1!=null && !"".equals(keyword1) && searchType1!=null && !"".equals(searchType1)){
			paramStr += "&searchType1="+searchType1+"&keyword1="+URLEncoder.encode(keyword1,"UTF-8");
		}
		if(keyword2!=null && !"".equals(keyword2) && searchType2!=null && !"".equals(searchType2)){
			paramStr += "&searchType2="+searchType2+"&keyword2="+URLEncoder.encode(keyword2,"UTF-8");
		}
		if(etcParam1!=null && !"".equals(etcParam1)){
			paramStr += "&etcParam1="+etcParam1;
		}
		if(schBeginDate!=null && !"".equals(schBeginDate)){
			paramStr += "&schBeginDate="+schBeginDate;
		}
		if(schEndDate!=null && !"".equals(schEndDate)){
			paramStr += "&schEndDate="+schEndDate;
		}
		if(schRegisterDt!=null && !"".equals(schRegisterDt)){
			paramStr += "&schRegisterDt="+schRegisterDt;
		}
		if(schRegisterDt2!=null && !"".equals(schRegisterDt2)){
			paramStr += "&schRegisterDt2="+schRegisterDt2;
		}
		if(dateType!=null && !"".equals(dateType)){
			paramStr += "&dateType="+dateType;
		}
		return paramStr+etcParamStr;
	}

	//페이지번호 포함된 파라미터 반환 앞은 공백처리 ?,& 상황에 따라 임의로 적용 정렬값 뺀 데이터
	public String getNotOrderPagingParam() throws UnsupportedEncodingException{
		String paramStr = "";
		paramStr += "page="+String.valueOf(page);
		if(keyword!=null && !"".equals(keyword) && searchType!=null && !"".equals(searchType)){
			paramStr += "&searchType="+searchType+"&keyword="+URLEncoder.encode(keyword,"UTF-8");
		}
		if(keyword1!=null && !"".equals(keyword1) && searchType1!=null && !"".equals(searchType1)){
			paramStr += "&searchType1="+searchType1+"&keyword1="+URLEncoder.encode(keyword1,"UTF-8");
		}
		if(keyword2!=null && !"".equals(keyword2) && searchType2!=null && !"".equals(searchType2)){
			paramStr += "&searchType2="+searchType2+"&keyword2="+URLEncoder.encode(keyword2,"UTF-8");
		}
		if(etcParam1!=null && !"".equals(etcParam1)){
			paramStr += "&etcParam1="+etcParam1;
		}
		if(schBeginDate!=null && !"".equals(schBeginDate)){
			paramStr += "&schBeginDate="+schBeginDate;
		}
		if(schEndDate!=null && !"".equals(schEndDate)){
			paramStr += "&schEndDate="+schEndDate;
		}
		if(schRegisterDt!=null && !"".equals(schRegisterDt)){
			paramStr += "&schRegisterDt="+schRegisterDt;
		}
		if(schRegisterDt2!=null && !"".equals(schRegisterDt2)){
			paramStr += "&schRegisterDt2="+schRegisterDt2;
		}
		if(dateType!=null && !"".equals(dateType)){
			paramStr += "&dateType="+dateType;
		}
		return paramStr+etcParamStr;
	}
	//정렬파라미터 반환
	public String getOrderParam(){
		String paramStr = "";
		if(order!=null && !"".equals(order)){
			paramStr += "&order="+order;
		}
		if(asc!=null && !"".equals(asc)){
			paramStr += "&asc="+asc;
		}
		return paramStr;
	}
	
	public int getStartRow() {
		return ((page-1)*rowCount);
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public String getParamStr() {
		return paramStr;
	}
	public void setParamStr(String paramStr) {
		this.paramStr = paramStr;
	}

	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	
	
}
