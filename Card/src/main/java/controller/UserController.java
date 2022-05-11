package controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import model.MyUser;
import service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toLogin")
	public String toLogin(@ModelAttribute MyUser myUser) {
		return "login";
	}
	
	@RequestMapping("/toRegister")
	public String toRegister(@ModelAttribute MyUser myUser) {
		return "register";
	}
	
	@RequestMapping("/checkUname")
	public String checkUname(@ModelAttribute MyUser myUser) {
		return userService.checkUname(myUser);
	}
	
	@RequestMapping("/register")
	public String register(@ModelAttribute MyUser myUser, Model model) {
		return userService.register(myUser);
	}
	
	@RequestMapping("/login")
	public String login(@ModelAttribute MyUser myUser, Model model, HttpSession session) {
		return userService.login(myUser, model, session);
	}
}
