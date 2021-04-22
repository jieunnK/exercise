package egovframework.service.user;

import egovframework.model.user.UserVO;

public interface UserService {
	
	//회원등록
	String insert(UserVO userVO) throws Exception;
	
	int idCheck(UserVO userVO) throws Exception;
	
}
