package xin.redips.urls.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.redips.urls.dao.LoggerDao;
import xin.redips.urls.pojo.Logger;

@Service
public class LoggerService {
	public static final int LOG_OPTYPE_LOGIN_SUCCESS = 0;
	public static final int LOG_OPTYPE_LOGIN_FAIL = 1;
	public static final int LOG_OPTYPE_REGIST = 2;
	public static final int LOG_OPTYPE_FORGET_PASSWORD = 3;
	public static final int LOG_OPTYPE_RESET_PASSWORD_STEP1 = 4;
	public static final int LOG_OPTYPE_RESET_PASSWORD_STEP2 = 5;
	@Resource
	private LoggerDao loggerDao;
	public void logger(String username,int op){
		loggerDao.insertLog(new Logger(username,op));
	}
}
