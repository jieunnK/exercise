package egovframework.service.user;

import egovframework.model.user.UserVO;

public interface UserService {
	
	//회원등록
	String insert(UserVO userVO) throws Exception;
	
	//중복체크
	int idCheck(UserVO userVO) throws Exception;
	
	//로그인
	String login(UserVO userVO) throws Exception;
	
	//로그인 정보
	UserVO oneData(UserVO userVO) throws Exception;
}
