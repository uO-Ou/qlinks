package xin.redips.urls.dao;

import java.util.List;

import xin.redips.urls.pojo.Link;

public interface LinkDao {

	List<Link> selectUrlsByUserid(int userId);
	
	public int insertUrl(Link link);
	
	public int deleteUrlById(int id);
	
	List<String > selectTagsByUserid(int userId);
	
	public void incHitCntById(int urlId);
	
	public void modifyUrlById(Link link);
}
