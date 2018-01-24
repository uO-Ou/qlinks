package xin.redips.urls.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xin.redips.urls.pojo.Account;
import xin.redips.urls.pojo.Authorization;
import xin.redips.urls.pojo.Link;
import xin.redips.urls.service.AccountService;
import xin.redips.urls.service.AuthorizationService;
import xin.redips.urls.service.LinkService;
import xin.redips.urls.service.LoggerService;
import xin.redips.urls.service.MailService;

@Controller
public class AccountController {
	private static final String HOST_ADDRESS = "http://www.redips.xin/";
	//private static final String HOST_ADDRESS = "http://localhost:8080/";

	@Resource
	private AccountService accountService;
	@Resource
	private MailService mailService;
	@Resource
	private LinkService linkService;
	@Resource
	private AuthorizationService authorizationService;
	@Resource
	private LoggerService loggerService;
	
	@ResponseBody
	@RequestMapping(value="/login/{username}")
	public String verify(@PathVariable String username,HttpServletRequest request,HttpSession session){
		String password = request.getParameter("pass");
		if(password==null) return "failed";

		Account account = accountService.verify(username, password);
	    if(account!=null)  {
	    	session.setAttribute("account", account);
	    	session.setMaxInactiveInterval(9000);
	    	loggerService.logger(username, LoggerService.LOG_OPTYPE_LOGIN_SUCCESS);
	    	return "success";	
	    }
	    else {
	    	loggerService.logger(username, LoggerService.LOG_OPTYPE_LOGIN_FAIL);
	    	return "failed";
	    }
	}
	
	@ResponseBody
	@RequestMapping(value="/regist")
	public String regist(HttpServletRequest request,HttpSession session){
		String password = request.getParameter("pass");
		String mail = request.getParameter("mail");
		String name = request.getParameter("name");
		if(password==null||mail==null||name==null) return "empty";
		
		Account account = new Account(name,password,mail);
		String result = accountService.addUser(account);
		
		if(result=="success"){
			session.setAttribute("account", account);
	    	session.setMaxInactiveInterval(9000);
	    	loggerService.logger(name, LoggerService.LOG_OPTYPE_REGIST);
	    	linkService.insertUrl(new Link(account.getId(),"https://www.baidu.com/","百度","常用链接"));
	    	linkService.insertUrl(new Link(account.getId(),"http://www.mtime.com/","时光网","常用链接"));
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/updatepass")
	public String updatePass(HttpSession session,HttpServletRequest request){
		String newpass = request.getParameter("newPass");
		Account account = (Account)(session.getAttribute("account"));
		if(newpass!=null&&account!=null){
			account.setPassword(newpass);
			accountService.updatePassById(account);
			loggerService.logger(account.getName(), LoggerService.LOG_OPTYPE_RESET_PASSWORD_STEP2);
			return "success";
		}
		return "fail";
	}
	
	@ResponseBody
	@RequestMapping(value="/forgetPassword/{username}")
	public String forgetPassword(@PathVariable String username) throws UnsupportedEncodingException, MessagingException{
		Account account = accountService.selectUserByName(username);
		if(account==null) return "null";
		else{
			loggerService.logger(username, LoggerService.LOG_OPTYPE_FORGET_PASSWORD);
			//generate a random string
			String authcode = UUID.randomUUID().toString().replaceAll("-", "");
			//insert into authorizations
			authorizationService.insertAuthorization(username, authcode,Authorization.AUTHCODE_TYPE_RESETPASSWORD, Authorization.AUTHCODE_PERIOD_RESETPASSWORD);
			//generate html content
			String url = HOST_ADDRESS+"urls/resetPassword/"+username+"/"+authcode;
			String html = "<p>请点击链接<a href='"+url+"'>"+url+"</a>重置www.redips.xin/urls的密码</p>";
			//send a email
			mailService.sendHtmlMail(account.getMail(), "密码找回",html);
			return "ok";
		}
	}
	
	@RequestMapping(value="/resetPassword/{username}/{authcode}")
	public String resetPassword(@PathVariable String username,@PathVariable String authcode,HttpSession session,HttpServletRequest request){
		//check if authcode exsits
		Authorization authorization = authorizationService.selectAuthorizationByNameAndCode(username, authcode);
		if(authorization!=null){
			//check if authcode effective
			Date current = new Date();
			long different = current.getTime() - authorization.getEffectiveDate().getTime();
			loggerService.logger(username, LoggerService.LOG_OPTYPE_RESET_PASSWORD_STEP1);
			if(different/1000<authorization.getPeriod()){
				//add account to session
				session.setAttribute("account", accountService.selectUserByName(username));
		    	session.setMaxInactiveInterval(9000);
		    	//add a label to request
		    	request.setAttribute("label", "resetpassword");
		    	//delete
				authorizationService.deleteAuthorizationById(authorization.getId());
				return "links";
			}
			else{
				//authcode expired
				authorizationService.deleteAuthorizationById(authorization.getId());
				return "redirect:/index.jsp?name="+username+"&authcode="+Authorization.AUTHCODE_STATUS_EXPIRED;
			}
		}
		return "redirect:/index.jsp?name="+username+"&authcode="+Authorization.AUTHCODE_STATUS_INVALID;
	}
}
