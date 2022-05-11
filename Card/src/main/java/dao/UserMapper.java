package dao;

import java.util.List;

import model.MyUser;
import po.MyUserTable;

public interface UserMapper {
	public List<MyUserTable> selectByUname(MyUser myUser);
	public int register(MyUser myUser);
	public List<MyUserTable> login(MyUser myUser);
}
