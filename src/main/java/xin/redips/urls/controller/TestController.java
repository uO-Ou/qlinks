package xin.redips.urls.controller;


import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("/{username}")
	public String showAttributes(@PathVariable String username,Model model){
		model.addAttribute("username",username);
		
		ArrayList names = new ArrayList<String>();
		names.add("张三");
		names.add("李四");
		names.add("王五");
		names.add("赵六");
		model.addAttribute("names",names);
		return "test";
	}
}
