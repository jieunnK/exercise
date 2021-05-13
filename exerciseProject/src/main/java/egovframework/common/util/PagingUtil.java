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
	
	private int startBlockPage = 0;
	
	private int prevBlockPage = 0;
	
	private int nextBlockPage = 0;
	
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
	
	
	public String getPaging() {
		totalPage = (int)Math.ceil((double)totalCount / (double)rowCount);
		
		if (totalPage > pageSize) {
			startBlockPage = (int)Math.floor( (double)(pageIndex - 1) / pageSize ) * pageSize + 1;
			
			prevBlockPage = startBlockPage - 1;
			nextBlockPage = startBlockPage + pageSize;
		} else {
			startBlockPage = 1;
		}
		
		paging = "";
		
		if(totalPage > 0) {
			paging += "<li class=\"page-item\">\r\n";
			paging += "<a href=\"{LINK}?page=1{PARAM}\" class=\"page-link\" aria-label=\"Previous\">\r\n";
			paging += "<i class=\"ti-angle-left\"></i>\r\n";
			paging += "<i class=\"ti-angle-left\"></i>\r\n"; 
			paging += "</a>\r\n"; 
			paging += "</li>";
			
			paging += "<li class=\"page-item\">\r\n";
			paging += "<a href=\"{LINK}?page=\""+pageIndex+"{PARAM}\" class=\"page-link\" aria-label=\"Previous\">\r\n";
			paging += "<i class=\"ti-angle-left\"></i>\r\n";
			paging += "</a>\r\n"; 
			paging += "</li>";
		}
		
		int p, q;
		for (p=0, q=0; q < totalPage && p < pageSize; p++) {
			q = startBlockPage + p;				
			String active = pageIndex== q ? "active" : "";
			paging += "<li class=\"page-item "+active+"\">\r\n";
			paging += "<a href=\"{LINK}?page="+q+"{PARAM}\" class=\"page-link\">"+q+"</a>\r\n"; 
			paging += "</li>";
		}
		
		if (totalPage>0) {
			
			int nextPage = pageIndex +1 == totalPage || pageIndex + 1 > totalPage ? totalPage : pageIndex + 1;
			paging += "<li class=\"page-item\">\r\n";
			paging += "<a href=\"{LINK}?page="+nextPage+"{PARAM}\" class=\"page-link\" aria-label=\"Next\">\r\n";
			paging += "<i class=\"ti-angle-right\"></i>\r\n";
			paging += "</a>\r\n"; 
			paging += "</li>";
			
			paging += "<li class=\"page-item\">\r\n";
			paging += "<a href=\"{LINK}?page="+totalPage+"{PARAM}\" class=\"page-link\" aria-label=\"Next\">\r\n";
			paging += "<i class=\"ti-angle-right\"></i>\r\n";
			paging += "<i class=\"ti-angle-right\"></i>\r\n"; 
			paging += "</a>\r\n"; 
			paging += "</li>";
			

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
