package xin.redips.urls.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import xin.redips.urls.dao.AccountDao;
import xin.redips.urls.pojo.Account;

@Service("accountService")
public class AccountService {

	@Resource
	private AccountDao accountDao;
	
	public Account selectUserByName(String username){
		return accountDao.selectUserByName(username);
	}
	
	public Account verify(String username,String password){
		return accountDao.selectUserByNameAndPassword(new Account(username,password));
	}
	
	public String addUser(Account account){
		if(accountDao.isUsernameExists(account.getName())!=null){
			return "user_exists";
		}
		else if(accountDao.isMailUsed(account.getMail())!=null){
			return "mail_exists";
		}
		else{
			accountDao.adduser(account);
			return "success";
		}
	}
	
	public void updatePassById(Account account){
		accountDao.updatePassById(account);
	}
	
}
