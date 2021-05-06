package egovframework.service.board.impl;

import java.util.List;

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
	
	//듣록
	@Override
	public String insert(BoardVO boardVO) throws Exception {
		LOGGER.debug(boardVO.toString());
		return boardDAO.insert(boardVO);
	}

	//목록
	@Override
	public List<BoardVO> list(BoardVO boardVO) throws Exception {
		LOGGER.debug(boardVO.toString());
		
		return boardDAO.list(boardVO);
	}

	//개수
	@Override
	public long totalCount(BoardVO boardVO) throws Exception {
		LOGGER.debug(boardVO.toString());
		return boardDAO.totalCount(boardVO);
	}

}
