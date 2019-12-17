package upjs.sk.pizza_int.Interfaces;

import java.util.List;

import upjs.sk.pizza_int.Models.User;

public interface UserDao {
	
	List<User> getAll();
	
	User getUserByLogin(String login, String password);
	
	User saveUser(User user);
	
	User promoteUser(String login);
	
	User demoteUser(String login);

}
