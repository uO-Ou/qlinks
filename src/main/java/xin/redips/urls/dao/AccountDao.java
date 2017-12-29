package xin.redips.urls.dao;

import xin.redips.urls.pojo.Account;

public interface AccountDao {

	public Account selectUserByName(String username);
	
	public Account selectUserByNameAndPassword(Account account);
	
	public void adduser(Account account);
	
	public String isMailUsed(String mail);
	
	public String isUsernameExists(String name);
	
	public void updatePassById(Account account);
}
