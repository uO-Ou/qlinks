package xin.redips.urls.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.redips.urls.dao.AuthorizationDao;
import xin.redips.urls.pojo.Authorization;

@Service("authorizationService")
public class AuthorizationService {
	@Resource
	AuthorizationDao authorizationDao;
	
	public void insertAuthorization(String username,String authcode,int type,int period){
		authorizationDao.insertAuthorization(new Authorization(username,authcode,type,period));
	}
	
	public void deleteAuthorizationById(int id){
		authorizationDao.deleteAuthorizationById(id);
	}
	
	public Authorization selectAuthorizationByNameAndCode(String username,String authcode){
		Authorization auth = authorizationDao.selectAuthorizationByNameAndCode(new Authorization(username,authcode));
		return auth;
	}
}
