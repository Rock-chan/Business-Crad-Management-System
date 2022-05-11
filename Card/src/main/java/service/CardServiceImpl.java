package service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import dao.CardMapper;
import model.Card;
import model.MyUser;
import po.CardTable;
import po.MyUserTable;
import util.MD5Util;
import util.MyUtil;

@Service
public class CardServiceImpl implements CardService{

	@Autowired
	private CardMapper cardMapper;
	
	/**
	 * ��ѯ���޸Ĳ�ѯ��ɾ����ѯ����ҳ��ѯ
	 */
	@Override
	public String selectAllCardsByPage(Model model, int currentPage, HttpSession session) {
		// TODO Auto-generated method stub
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		List<Map<String, Object>> allUser = cardMapper.selectAllCards(mut.getId());
		// ���ж��ٸ��û�
		int totalCount = allUser.size();
		// ���㹲�ж���ҳ
		int pageSize = 5;
		int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
		List<Map<String, Object>> cardByPage = cardMapper.selectAllCardsByPage((currentPage-1) * pageSize, pageSize, mut.getId());
		model.addAttribute("allCards", cardByPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		return "main";
	}

	/**
	 * ������޸���Ƭ
	 */
	@Override
	public String addCard(Card card, HttpServletRequest request, String act, HttpSession session)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		MultipartFile myfile = card.getLogo();
		// ���ѡ�����ϴ��ļ������ļ��ϴ���ָ����Ŀ¼static/images
		if(!myfile.isEmpty()) {
			// �ϴ��ļ�·��������������
			String path = request.getServletContext().getRealPath("/static/images/");
			// ����ϴ��ļ�ԭ��
			String fileName = myfile.getOriginalFilename();
			// ���ļ�������
			String fileNewName = MyUtil.getNewFileName(fileName);
			File filePath = new File(path + File.separator + fileNewName);
			// ����ļ�Ŀ¼�����ڣ�����Ŀ¼
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			// ���ϴ��ļ�������һ��Ŀ���ļ���
			myfile.transferTo(filePath);
			// �����������ͼƬ�浽card�����У����ʱʹ��
			card.setLogoName(fileNewName);
		}
		
		if("add".equals(act)) {
			MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
			card.setUser_id(mut.getId());
			int n = cardMapper.addCard(card);
			if(n > 0) {
				return "redirect:/card/selectAllCardsByPage?currentPage=1";
			}
			else {
				return "addCard";
			}
		}else {
			int n = cardMapper.updateCard(card);
			if(n > 0) {
				// �ɹ�
				return "redirect:/card/selectAllCardsByPage?currentPage=1";
			}
			// ʧ��
			return "updateCard";
		}
	}

	/**
	 * ���������޸�ҳ��
	 */
	@Override
	public String detail(Model model, int id, String act) {
		// TODO Auto-generated method stub
		CardTable ct = cardMapper.selectACard(id);
		model.addAttribute("card", ct);
		if("detail".equals(act)) {
			return "cardDetail";
		}else {
			return "updateCard";
		}
	}

	/**
	 * ɾ��
	 */
	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		cardMapper.deleteACard(id);
		return "/card/selectAllCardsByPage?currentPage=1";
	}

	/**
	 * ��ȫ�˳�
	 */
	@Override
	public String loginOut(Model model, HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
		model.addAttribute("myUser", new MyUser());
		return "login";
	}

	/**
	 * ���޸�����ҳ��
	 */
	@Override
	public String toUpdatePwd(Model model, HttpSession session) {
		// TODO Auto-generated method stub
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		model.addAttribute("myuser", mut);
		return "updatePwd";
	}
 
	/**
	 * �޸�����
	 */
	@Override
	public String updatePwd(MyUser myuser) {
		// TODO Auto-generated method stub
		// �����ı������
		myuser.setUpwd(MD5Util.MD5(myuser.getUpwd()));
		cardMapper.updatePwd(myuser);
		return "login";
	}
	
}
