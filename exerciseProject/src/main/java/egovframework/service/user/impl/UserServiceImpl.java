package egovframework.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import egovframework.example.sample.service.impl.EgovSampleServiceImpl;
import egovframework.model.user.UserVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;
import egovframework.service.user.UserService;

@Service("userService")
public class UserServiceImpl extends EgovAbstractServiceImpl implements UserService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EgovSampleServiceImpl.class);

	@Resource(name = "userDAO")
	private UserDAO userDao;
	
	/** ID Generation */
	@Resource(name = "egovIdGnrService")
	private EgovIdGnrService egovIdGnrService;
	
	@Override
	public String insert(UserVO userVO) throws Exception {
		
		LOGGER.debug(userVO.toString());
		String check = userDao.insert(userVO);
		LOGGER.debug(userVO.toString());

		return check;
	}

	@Override
	public int idCheck(UserVO userVO) throws Exception {
		LOGGER.debug(userVO.toString());
		int check = userDao.idCheck(userVO);
		LOGGER.debug(userVO.toString());
		return check;
	}



}
