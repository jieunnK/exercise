package egovframework.service.user.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.impl.EgovSampleServiceImpl;
import egovframework.model.user.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.service.user.UserService;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);

	@Resource(name = "userDAO")
	private UserDAO userDao;
	
	//회원가입
	@Override
	public String insert(UserVO userVO) throws Exception {	
		LOGGER.debug(userVO.toString());
		return userDao.insert(userVO);
	}

	//중복체크
	@Override
	public int idCheck(UserVO userVO) throws Exception {
		LOGGER.debug(userVO.toString());
		return userDao.idCheck(userVO);
	}

	//로그인
	@Override
	public String login(UserVO userVO) throws Exception {
		LOGGER.debug(userVO.toString());	
		return userDao.login(userVO);
	}

	//로그인 정보
	@Override
	public UserVO oneData(UserVO userVO) throws Exception {
		LOGGER.debug(userVO.toString());
		UserVO user = userDao.oneData(userVO);
		LOGGER.debug("oneData>>>>>>>>>>>>>>>>>>>>"+user.toString());
		return user;
	}



}
