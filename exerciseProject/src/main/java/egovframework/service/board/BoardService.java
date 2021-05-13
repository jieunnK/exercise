package egovframework.service.board;

import java.util.List;

import egovframework.model.board.BoardVO;

public interface BoardService {
	
	//등록
	String insert(BoardVO boardVO) throws Exception;
	
	//목록
	List<BoardVO> list(BoardVO boardVO) throws Exception;
	
	//총 개수
	long totalCount(BoardVO boardVO) throws Exception;
	
	//게시물 정보
	BoardVO getData(BoardVO boardVO) throws Exception;
	
	//조회수 증가
	int updateViewCount(BoardVO boardVO) throws Exception;
	
	//게시물 수정
	int updateData(BoardVO boardVO) throws Exception;
	
}
