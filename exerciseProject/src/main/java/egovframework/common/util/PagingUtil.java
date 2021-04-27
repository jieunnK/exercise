package egovframework.common.util;

import java.io.UnsupportedEncodingException;

import egovframework.model.common.PageVO;

public class PagingUtil {
	
	private long totalCount=0;
	
	private int totalPage=0;
	
	private int rowCount=10;
	
	private int pageSize=10;
	
	private int pageIndex=1;
	
	private String paging;
	
	private String firstButtonClass="btn_fir";
	private String prevButtonClass="btn_pre";
	
	private String firstButtonImg;
	private String prevButtonImg;
	
	private String firstButtonTxt = "firstPage";
	private String prevButtonTxt="preview";
	
	private String lastButtonClass="btn_last";
	private String nextButtonClass="btn_next";
	
	private String lastButtonImg;
	private String nextButtonImg;
	
	private String lastButtonTxt="nextPage";
	private String nextButtonTxt="lastPage";
	
	private String pageDefaultClass;
	
	private String pageDefaultTag;
	
	private String pageOnClass="active";
	
	private String pageOnTag;
	
	private String url;
	
	private String param;
	
	public PagingUtil (long totalCount,int rowCount,int pageIndex,int pageSize,String url, PageVO pageVO)
	{
		this.totalCount = totalCount;
		this.rowCount = rowCount;
		this.pageIndex = pageIndex;
		this.pageSize = pageSize;
		this.url = url;
		try {
			this.param = pageVO.getSearchParam();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		totalPage = (int)Math.ceil((double)totalCount / (double)rowCount);
		setTotalPage(totalPage);
		
		pageVO.setTotalCount(totalCount);
		pageVO.setTotalPage(totalPage);
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

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	
	public String getFirstButtonClass() {
		return firstButtonClass;
	}

	public void setFirstButtonClass(String firstButtonClass) {
		this.firstButtonClass = firstButtonClass;
	}

	public String getPrevButtonClass() {
		return prevButtonClass;
	}

	public void setPrevButtonClass(String prevButtonClass) {
		this.prevButtonClass = prevButtonClass;
	}

	public String getFirstButtonImg() {
		return firstButtonImg;
	}

	public void setFirstButtonImg(String firstButtonImg) {
		this.firstButtonImg = firstButtonImg;
	}

	public String getPrevButtonImg() {
		return prevButtonImg;
	}

	public void setPrevButtonImg(String prevButtonImg) {
		this.prevButtonImg = prevButtonImg;
	}

	public String getFirstButtonTxt() {
		return firstButtonTxt;
	}

	public void setFirstButtonTxt(String firstButtonTxt) {
		this.firstButtonTxt = firstButtonTxt;
	}

	public String getPrevButtonTxt() {
		return prevButtonTxt;
	}

	public void setPrevButtonTxt(String prevButtonTxt) {
		this.prevButtonTxt = prevButtonTxt;
	}

	public String getLastButtonClass() {
		return lastButtonClass;
	}

	public void setLastButtonClass(String lastButtonClass) {
		this.lastButtonClass = lastButtonClass;
	}

	public String getNextButtonClass() {
		return nextButtonClass;
	}

	public void setNextButtonClass(String nextButtonClass) {
		this.nextButtonClass = nextButtonClass;
	}

	public String getLastButtonImg() {
		return lastButtonImg;
	}

	public void setLastButtonImg(String lastButtonImg) {
		this.lastButtonImg = lastButtonImg;
	}

	public String getNextButtonImg() {
		return nextButtonImg;
	}

	public void setNextButtonImg(String nextButtonImg) {
		this.nextButtonImg = nextButtonImg;
	}

	public String getLastButtonTxt() {
		return lastButtonTxt;
	}

	public void setLastButtonTxt(String lastButtonTxt) {
		this.lastButtonTxt = lastButtonTxt;
	}

	public String getNextButtonTxt() {
		return nextButtonTxt;
	}

	public void setNextButtonTxt(String nextButtonTxt) {
		this.nextButtonTxt = nextButtonTxt;
	}

	public String getPageDefaultClass() {
		return pageDefaultClass;
	}

	public void setPageDefaultClass(String pageDefaultClass) {
		this.pageDefaultClass = pageDefaultClass;
	}

	public String getPageDefaultTag() {
		return pageDefaultTag;
	}

	public void setPageDefaultTag(String pageDefaultTag) {
		this.pageDefaultTag = pageDefaultTag;
	}

	public String getPageOnClass() {
		return pageOnClass;
	}

	public void setPageOnClass(String pageOnClass) {
		this.pageOnClass = pageOnClass;
	}

	public String getPageOnTag() {
		return pageOnTag;
	}

	public void setPageOnTag(String pageOnTag) {
		this.pageOnTag = pageOnTag;
	}
	
	public String getPaging() {
		
		//int totalPage = 1;
		int currPageGroup = 1;
		int prevGroupNum = 0;
		int nextGroupNum = 0;
		int startPageNum = 0;
		int lastPageNum = 0;
		
		//totalPage = (int)Math.ceil((double)totalCount / (double)rowCount); 
		currPageGroup = pageIndex / pageSize;
		
		//이전그룹번호설정
		prevGroupNum = currPageGroup;
		if(pageIndex%pageSize == 0)
			prevGroupNum--;
		if(prevGroupNum > 1){
			prevGroupNum = prevGroupNum*pageSize;
		}

		//다음그룹번호설정
		nextGroupNum = totalPage;
		if(totalCount%rowCount > 0)
			nextGroupNum++;
		if(nextGroupNum > (currPageGroup*pageSize)+1 ){
			nextGroupNum = (currPageGroup*pageSize)+1;
		}
		
		//현재 그룹의 시작,마지막 페이지번호설정
		startPageNum = currPageGroup;
		if(pageIndex % pageSize == 0){
			currPageGroup--;
		}
		startPageNum = (currPageGroup*pageSize)+1;
		lastPageNum = ((currPageGroup+1)*pageSize);
		
		paging = "";
		
		if(firstButtonImg != null && !"".equals(firstButtonImg))
		{
			paging += "<li {CLASS}><a href=\"{LINK}?page=1{PARAM}\"><img src=\""+firstButtonImg+"\" /></a></li>";
		}else{
			paging += "<li {CLASS}><a href=\"{LINK}?page=1{PARAM}\">"+firstButtonTxt+"</a></li>";
		}
		
		if(firstButtonClass != null && !"".equals(firstButtonClass))
		{
			paging = paging.replace("{CLASS}", "class=\""+firstButtonClass+"\"");
		}else{
			paging = paging.replace("{CLASS}", "");
		}
		
		int prevIndex = 1;
		
		if(pageIndex > 1)
		{
			prevIndex = pageIndex-1;
		}
		
		if(prevButtonImg != null && !"".equals(prevButtonImg))
		{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+prevIndex+"{PARAM}\"><img src=\""+prevButtonImg+"\" /></a></li>";
		}else{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+prevIndex+"{PARAM}\">"+prevButtonTxt+"</a></li>";
		}
		
		if(prevButtonClass != null && !"".equals(prevButtonClass))
		{
			paging = paging.replace("{CLASS}", "class=\""+prevButtonClass+"\"");
		}else{
			paging = paging.replace("{CLASS}", "");
		}
 
		
		for(int i=startPageNum;i <= lastPageNum;i++)
		{
			if(i > totalPage) break;
			if(i==pageIndex)
			{
				paging += "<li {ONCLASS}><a href=\"{LINK}?page="+i+"{PARAM}\">"+i+"</a></li>";
			}else{
				paging += "<li><a href=\"{LINK}?page="+i+"{PARAM}\">"+i+"</a></li>";
			}			
		}
		if(totalCount == 0)
		{
			paging += "<li><a href=\"{LINK}?page=1{PARAM}\">1</a></li>";
		}
		
		if(pageDefaultClass != null && !"".equals(pageDefaultClass))
		{
			paging = paging.replace("{CLASS}", "class=\""+pageDefaultClass+"\"");
		}else{
			paging = paging.replace("{CLASS}", "");
		}
		
		if(pageOnClass != null && !"".equals(pageOnClass))
		{
			paging = paging.replace("{ONCLASS}", "class=\""+pageOnClass+"\"");
		}else{
			paging = paging.replace("{ONCLASS}", "");
		}
		
		
		int nextIndex = pageIndex;
		
		if(pageIndex < totalPage)
		{
			nextIndex = pageIndex+1;
		}else{
			nextIndex = totalPage;
		}
		
		if(nextButtonImg != null && !"".equals(nextButtonImg))
		{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+nextIndex+"{PARAM}\"><img src=\""+nextButtonImg+"\" /></a></li>";
		}else{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+nextIndex+"{PARAM}\">"+nextButtonTxt+"</a></li>";
		}
		
		if(nextButtonClass != null && !"".equals(nextButtonClass))
		{
			paging = paging.replace("{CLASS}", "class=\""+nextButtonClass+"\"");
		}else{
			paging = paging.replace("{CLASS}", "");
		}
		
		if(lastButtonImg != null && !"".equals(lastButtonImg))
		{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+totalPage+"{PARAM}\"><img src=\""+lastButtonImg+"\" /></a></li>";
		}else{
			paging += "<li {CLASS}><a href=\"{LINK}?page="+totalPage+"{PARAM}\">"+lastButtonTxt+"</a></li>";
		}
		
		if(lastButtonClass != null && !"".equals(lastButtonClass))
		{
			paging = paging.replace("{CLASS}", "class=\""+lastButtonClass+"\"");
		}else{
			paging = paging.replace("{CLASS}", "");
		}
		
		
		paging = paging.replace("{LINK}", url).replace("{PARAM}", param);
		
		return paging;
	}

	public void setPaging(String paging) {
		this.paging = paging;
	}
	
	public int getRownum()
	{
		int page = (int)totalCount / rowCount;
		if(totalCount%rowCount > 0)
		{
			page++;
		}
				
		int mod = (int)totalCount%rowCount;
		if(mod > 0)
		{
			mod = rowCount - mod;
		}
		
		page = (page-(pageIndex-1))*rowCount-mod;
		return page;
		
	}
}
