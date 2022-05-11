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
	 * Ȩ�޿���
	 */
	@ModelAttribute
	public void checkLogin(HttpSession session) throws NoLoginException{
		if(session.getAttribute("userLogin") == null) {
			throw new NoLoginException();
		}
	}
	
	/**
	 * ��ѯ���޸Ĳ�ѯ��ɾ����ѯ
	 */
	@RequestMapping("/selectAllCardsByPage")
	public String selectAllCardByPage(Model model, int currentPage, HttpSession session) {
		return cardService.selectAllCardsByPage(model, currentPage, session);
	}
	
	/**
	 * �����ҳ��
	 */
	@RequestMapping("/toAddCard")
	public String toAddCard(@ModelAttribute Card card) {
		return "addCard";
	}
	
	/**
	 * ʵ����Ӽ��޸Ĺ���
	 */
	@RequestMapping("/addCard")
	public String addCard(@ModelAttribute Card card, HttpServletRequest request, String act,
			HttpSession session) throws IllegalStateException, IOException {
		return cardService.addCard(card, request, act, session);
		
	}
	
	/**
	 * �����鼰�޸�ҳ��
	 */
	@RequestMapping("/detail")
	public String detail(Model model, int id, String act) {
		return cardService.detail(model, id, act);
	}
	
	/**
	 * ɾ��
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(int id) {
		return cardService.delete(id);
	}
	
	/**
	 * ��ȫ�˳�
	 */
	@RequestMapping("/loginOut")
	public String loginOut(Model model, HttpSession session) {
		return cardService.loginOut(model, session);
	}
	
	/**
	 * ���޸�����ҳ��
	 */
	@RequestMapping("/toUpdatePwd")
	public String toUpdatePwd(Model model, HttpSession session) {
		return cardService.toUpdatePwd(model, session);
	}
	
	/**
	 * �޸�����
	 */
	@RequestMapping("/updatePwd")
	public String updatePws(@ModelAttribute MyUser myuser) {
		return cardService.updatePwd(myuser);
	}
}
