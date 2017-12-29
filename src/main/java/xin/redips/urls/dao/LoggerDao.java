package xin.redips.urls.dao;

import javax.annotation.Resource;

import xin.redips.urls.pojo.Logger;

@Resource
public interface LoggerDao {
	public void insertLog(Logger record);
}
