package controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import model.Card;
import model.MyUser;
import service.CardService;

@Controller
@RequestMapping("/card")
public class CardController {
	@Autowired
	private CardService cardService;
	
	/**
	 * 权限控制
	 */
	@ModelAttribute
	public void checkLogin(HttpSession session) throws NoLoginException{
		if(session.getAttribute("userLogin") == null) {
			throw new NoLoginException();
		}
	}
	
	/**
	 * 查询、修改查询、删除查询
	 */
	@RequestMapping("/selectAllCardsByPage")
	public String selectAllCardByPage(Model model, int currentPage, HttpSession session) {
		return cardService.selectAllCardsByPage(model, currentPage, session);
	}
	
	/**
	 * 打开添加页面
	 */
	@RequestMapping("/toAddCard")
	public String toAddCard(@ModelAttribute Card card) {
		return "addCard";
	}
	
	/**
	 * 实现添加及修改功能
	 */
	@RequestMapping("/addCard")
	public String addCard(@ModelAttribute Card card, HttpServletRequest request, String act,
			HttpSession session) throws IllegalStateException, IOException {
		return cardService.addCard(card, request, act, session);
		
	}
	
	/**
	 * 打开详情及修改页面
	 */
	@RequestMapping("/detail")
	public String detail(Model model, int id, String act) {
		return cardService.detail(model, id, act);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int id) {
		return cardService.delete(id);
	}
	
	/**
	 * 安全退出
	 */
	@RequestMapping("/loginOut")
	public String loginOut(Model model, HttpSession session) {
		return cardService.loginOut(model, session);
	}
	
	/**
	 * 打开修改密码页面
	 */
	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd(Model model, HttpSession session) {
		return cardService.toUpdatePwd(model, session);
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/updatePwd")
	public String updatePws(@ModelAttribute MyUser myuser) {
		return cardService.updatePwd(myuser);
	}
}
