package xin.redips.urls.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.redips.urls.dao.LinkDao;
import xin.redips.urls.pojo.Link;

@Service("linkService")
public class LinkService{
	
	@Resource
	private LinkDao linkDao;
	
	public List<Link> selectUrlsByUserid(int userId){
		return linkDao.selectUrlsByUserid(userId);
	}
	
	public int insertUrl(Link link){
		return linkDao.insertUrl(link);
	}
	
	public int deleteUrlById(int id){
		return linkDao.deleteUrlById(id);
	}
	
	public List<String> selectTagsByUserid(int userId){
		return linkDao.selectTagsByUserid(userId);
	}
	
	public void incHitCntById(int urlId){
		linkDao.incHitCntById(urlId);
	}
	
	public void modifyUrlById(Link link){
		linkDao.modifyUrlById(link);
	}
}
