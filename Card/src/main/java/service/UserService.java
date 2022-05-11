package service;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import model.MyUser;

public interface UserService {
	public String checkUname(MyUser myUser);
	public String register(MyUser myUser);
	public String login(MyUser myUser, Model model, HttpSession session);
}
