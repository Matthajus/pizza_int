package upjs.sk.pizza_int;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.User;

class MysqlUserDaoTest {
	static Long userPred;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		userPred = (long) DaoFactory.INSTANCE.getUserDao().getAll().size();

		User user = new User();
		user.setName("Antoan");
		user.setSurname("DJ");
		user.setLogin("Ant");
		user.setPassword("789");
		user.setEmail("antoan.dj@gmail.com");
		user.setTel_number("905748753");
		user.setIsicCardNumber("1111155555");

		DaoFactory.INSTANCE.getUserDao().saveUser(user);

	}

	@Test
	void saveUserTest() {
		assertEquals(userPred + 1, DaoFactory.INSTANCE.getUserDao().getAll().size());
	}

	void getAllTest() {
		assertFalse(DaoFactory.INSTANCE.getUserDao().getAll().isEmpty());
		assertNotNull(DaoFactory.INSTANCE.getUserDao().getAll());

	}

	@Test
	void getUserByLoginTest() {
		if (DaoFactory.INSTANCE.getUserDao().getAll().isEmpty() == false
				&& DaoFactory.INSTANCE.getUserDao().getAll() != null) {

			User user = DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant", "789");
			assertEquals(user.getName(), "Antoan");
			assertEquals(user.getSurname(), "DJ");
		}
	}

	@Test
	void proAndDemoteTest() {

		User user = new User();
		user.setName("Antoan1");
		user.setSurname("DJ1");
		user.setLogin("Ant1");
		user.setPassword("7891");
		user.setEmail("antoan1.dj@gmail.com");
		user.setTel_number("905748751");
		user.setIsicCardNumber("1111155551");

		DaoFactory.INSTANCE.getUserDao().saveUser(user);

		int role = DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant1", "7891").getRole();

		DaoFactory.INSTANCE.getUserDao().promoteUser(DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant1", "7891"));
		assertEquals(role - 1, DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant1", "7891").getRole());
		DaoFactory.INSTANCE.getUserDao().demoteUser(DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant1", "7891"));
		assertEquals(role, DaoFactory.INSTANCE.getUserDao().getUserByLogin("Ant1", "7891").getRole());

	}

}