package xin.redips.urls.controller;

import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xin.redips.urls.pojo.Account;
import xin.redips.urls.pojo.Link;
import xin.redips.urls.service.LinkService;

//@RequestMapping("/")
@Controller
public class LinkController {

	@Resource
	private LinkService linkService;

	@RequestMapping(value="/user/{username}")
	public String showUrls(@PathVariable String username,HttpSession session){
		Account account = (Account)(session.getAttribute("account"));
		if(account==null||!account.getName().equals(username)) return "redirect:/index.jsp?name="+username;
		return "links";	
	}
	
	@ResponseBody
	@RequestMapping("/getTags")
	public ArrayList<String> getTags(HttpSession session){
		
		Account account = (Account)(session.getAttribute("account"));
		if(account==null){
			return null;
		}
		
		return (ArrayList<String>)linkService.selectTagsByUserid(account.getId());
	}
	
	@ResponseBody
	@RequestMapping(value="/getLinks")
	public ArrayList<Link> getLinks(HttpSession session){
		Account account = (Account)(session.getAttribute("account"));
		if(account==null){
			return null;
		}

		return (ArrayList<Link>)linkService.selectUrlsByUserid(account.getId());
	}
	
	@ResponseBody
	@RequestMapping(value="/addLink")
	public Link addLink(HttpServletRequest request,HttpSession session){
		Account account = (Account)(session.getAttribute("account"));
		if(account==null){
			return null;
		}
		
		String url = request.getParameter("url");
		String hint = request.getParameter("hint");
		String tag = request.getParameter("tag");
		Link link = new Link(account.getId(),url,hint,tag);
		
		if(linkService.insertUrl(link)==1) return link;
		return null;
	}
	
	@ResponseBody
	@RequestMapping("/incHit")
	public String incHit(HttpSession session,HttpServletRequest request){
		if(session.getAttribute("account")==null) return "failed";
		
		linkService.incHitCntById(Integer.parseInt(request.getParameter("id")));
		
		return "success";
	}
	
	@ResponseBody
	@RequestMapping("/deleteLink")
	public String deleteLinkById(HttpServletRequest request,HttpSession session){
		Account account = (Account)(session.getAttribute("account"));
		if(account==null) return "failed";
		
		if(linkService.deleteUrlById(Integer.parseInt(request.getParameter("id")))>0){
			return "success";
		}
		//return "redirect:/user/"+account.getName();
        return "failed";		
	}
	
	@ResponseBody
	@RequestMapping("/modifyLink")
	public String modifyLink(HttpSession session,HttpServletRequest request){
		if(session.getAttribute("account")==null) return "failed";
		
		String id = request.getParameter("id");
		String url = request.getParameter("url");
		String hint = request.getParameter("hint");
		String helpInfo = request.getParameter("helpInfo");
		
		if(id!=null&&id.length()>0){
			Link link = new Link();
			link.setId(Integer.parseInt(id));
			link.setUrl(url);
			link.setHint(hint);
			link.setHelpInfo(helpInfo);
			
			linkService.modifyUrlById(link);
			return "success";
		}
		return "failed";
	}
}
