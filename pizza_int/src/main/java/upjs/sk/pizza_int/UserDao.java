package upjs.sk.pizza_int;

import java.util.List;

public interface UserDao {
	
	List<User> getAll();
	
	User getUserByLogin(String login, String password);
	
	User promoteUser(String login);
	
	User demoteUser(String login);

}
