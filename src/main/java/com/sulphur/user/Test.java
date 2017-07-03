package com.sulphur.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("/user")
public class Test {

	@RequestMapping("/test.do")
	public @ResponseBody String test(){
		return "win";
	}
}
