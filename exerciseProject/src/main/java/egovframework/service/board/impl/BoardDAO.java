package egovframework.service.board.impl;

import org.springframework.stereotype.Repository;

import egovframework.model.board.BoardVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("boardDAO")
public class BoardDAO extends EgovAbstractDAO{
	public String insert(BoardVO boardVO) throws Exception{
		return (String) insert("boardDAO.insert", boardVO);
	}
}
