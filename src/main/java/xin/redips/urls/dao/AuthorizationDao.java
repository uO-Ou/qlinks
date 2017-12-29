package xin.redips.urls.dao;

import xin.redips.urls.pojo.Authorization;

public interface AuthorizationDao {
	    void insertAuthorization(Authorization authorization);
		Authorization selectAuthorizationByNameAndCode(Authorization authorization);
		void deleteAuthorizationById(int id);
}
