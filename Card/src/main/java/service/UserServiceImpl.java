package service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.UserMapper;
import model.MyUser;
import po.MyUserTable;
import util.MD5Util;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * ����û����Ƿ����
	 */
	@Override
	public String checkUname(MyUser myUser) {
		// TODO Auto-generated method stub
		List<MyUserTable> userList = userMapper.selectByUname(myUser);
		if(userList.size() > 0) {
			return "no";
		}
		return "ok";
	}

	/**
	 * ʵ��ע�Ṧ��
	 */
	@Override
	public String register(MyUser myUser) {
		// TODO Auto-generated method stub
		// �����ı������
		myUser.setUpwd(MD5Util.MD5(myUser.getUpwd()));
		if(userMapper.register(myUser) > 0) {
			return "login";
		}
		return "register";
	}

	/**
	 * ʵ�ֵ�¼����
	 */
	@Override
	public String login(MyUser myUser, Model model, HttpSession session) {
		// TODO Auto-generated method stub
		// ValidateCodeController�е�rand
		String code = (String) session.getAttribute("rand");
		if(!code.equalsIgnoreCase(myUser.getCode())) {
			model.addAttribute("errorMessage", "��֤�����");
			return "login";
		}else {
			// ���ı������
			myUser.setUpwd(MD5Util.MD5(myUser.getUpwd()));
			List<MyUserTable> list = userMapper.login(myUser);
			if(list.size() > 0) {
				session.setAttribute("userLogin", list.get(0));
				return "redirect:/card/selectAllCardsByPage?currentPage=1";
			}else {
				model.addAttribute("errorMessage", "�û������������");
				return "login";
			}
		}
	}
}
