package egovframework.service.board.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.model.board.BoardVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.service.board.BoardService;

@Service("boardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService{

	private static final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);
	
	@Resource(name = "boardDAO")
	private BoardDAO boardDAO;
	
	
	@Override
	public String insert(BoardVO boardVO) throws Exception {
		LOGGER.debug(boardVO.toString());
		String check = boardDAO.insert(boardVO);
		LOGGER.debug(boardVO.toString());

		return check;
	}

}
