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
	 * 查询、修改查询、删除查询、分页查询
	 */
	@Override
	public String selectAllCardsByPage(Model model, int currentPage, HttpSession session) {
		// TODO Auto-generated method stub
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		List<Map<String, Object>> allUser = cardMapper.selectAllCards(mut.getId());
		// 共有多少个用户
		int totalCount = allUser.size();
		// 计算共有多少页
		int pageSize = 5;
		int totalPage = (int)Math.ceil(totalCount * 1.0 / pageSize);
		List<Map<String, Object>> cardByPage = cardMapper.selectAllCardsByPage((currentPage-1) * pageSize, pageSize, mut.getId());
		model.addAttribute("allCards", cardByPage);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("currentPage", currentPage);
		return "main";
	}

	/**
	 * 添加与修改名片
	 */
	@Override
	public String addCard(Card card, HttpServletRequest request, String act, HttpSession session)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		MultipartFile myfile = card.getLogo();
		// 如果选择了上传文件，将文件上传到指定的目录static/images
		if(!myfile.isEmpty()) {
			// 上传文件路径（生产环境）
			String path = request.getServletContext().getRealPath("/static/images/");
			// 获得上传文件原名
			String fileName = myfile.getOriginalFilename();
			// 对文件重命名
			String fileNewName = MyUtil.getNewFileName(fileName);
			File filePath = new File(path + File.separator + fileNewName);
			// 如果文件目录不存在，创建目录
			if(!filePath.getParentFile().exists()) {
				filePath.getParentFile().mkdirs();
			}
			// 将上传文件保存在一个目标文件中
			myfile.transferTo(filePath);
			// 将重命名后的图片存到card对象中，添加时使用
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
				// 成功
				return "redirect:/card/selectAllCardsByPage?currentPage=1";
			}
			// 失败
			return "updateCard";
		}
	}

	/**
	 * 打开详情与修改页面
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
	 * 删除
	 */
	@Override
	public String delete(int id) {
		// TODO Auto-generated method stub
		cardMapper.deleteACard(id);
		return "/card/selectAllCardsByPage?currentPage=1";
	}

	/**
	 * 安全退出
	 */
	@Override
	public String loginOut(Model model, HttpSession session) {
		// TODO Auto-generated method stub
		session.invalidate();
		model.addAttribute("myUser", new MyUser());
		return "login";
	}

	/**
	 * 打开修改密码页面
	 */
	@Override
	public String toUpdatePwd(Model model, HttpSession session) {
		// TODO Auto-generated method stub
		MyUserTable mut = (MyUserTable)session.getAttribute("userLogin");
		model.addAttribute("myuser", mut);
		return "updatePwd";
	}
 
	/**
	 * 修改密码
	 */
	@Override
	public String updatePwd(MyUser myuser) {
		// TODO Auto-generated method stub
		// 将明文变成密文
		myuser.setUpwd(MD5Util.MD5(myuser.getUpwd()));
		cardMapper.updatePwd(myuser);
		return "login";
	}
	
}
