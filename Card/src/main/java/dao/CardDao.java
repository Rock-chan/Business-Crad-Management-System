package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import model.Card;
import model.MyUser;
import po.CardTable;

public interface CardDao {
	// 查询所有名片
	
	public List<Map<String, Object>> selectAllCards(int uid);
	public List<Map<String, Object>> selectAllCardsByPage(@Param("startIndex") int startIndex, @Param("perPageSize") int perPageSize, @Param("uid") int uid);
	public int addCard(Card card);
	public int updateCard(Card card);
	public CardTable selectACard(int id);
	public int deleteACard(int id);
	public int updatePwd(MyUser myuser);
}
