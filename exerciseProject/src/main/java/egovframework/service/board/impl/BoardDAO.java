package egovframework.service.board.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.model.board.BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO{
	//글 등록하기
	public String insert(BoardVO boardVO) throws Exception{
		return (String) insert("boardDAO.insert", boardVO);
	}
	
	//목록 가져오기
	public List<BoardVO> list(BoardVO boardVO) throws Exception{
		return (List<BoardVO>) list("boardDAO.list", boardVO);
	}
	
	//총 개수 구하기
	public long totalCount(BoardVO boardVO) throws Exception{
		return (long) select("boardDAO.totalCount", boardVO);
	}
}
