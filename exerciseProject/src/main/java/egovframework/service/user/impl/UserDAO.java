package egovframework.service.user.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.example.sample.service.SampleVO;
import egovframework.model.user.UserVO;
import egovframework.rte.psl.dataaccess.EgovAbstractDAO;

@Repository("userDAO")
public class UserDAO extends EgovAbstractDAO{
	
	//회원등록
	public String insert(UserVO vo) throws Exception {
		return (String) insert("userDAO.insert", vo);
	}
	
	public int idCheck(UserVO userVO) throws Exception {
		return (int) select("userDAO.idCheck", userVO);
	}
	
	public String login(UserVO userVO) throws Exception {
		return (String) select("userDAO.login" , userVO);
	}
	
	public UserVO oneData(UserVO userVO) throws Exception {
		return (UserVO) select("userDAO.oneData",userVO);
	}
}
