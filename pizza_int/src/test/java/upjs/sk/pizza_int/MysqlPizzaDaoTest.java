package upjs.sk.pizza_int;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import upjs.sk.pizza_int.Dao.DaoFactory;
import upjs.sk.pizza_int.Models.Pizza;

class MysqlPizzaDaoTest {
	static Long pizzePred;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		pizzePred = (long) DaoFactory.INSTANCE.getPizzaDao().getAll().size();

		Pizza pizza = new Pizza();
		pizza.setName("Veteranska");
		pizza.setDescription("tomato,syr,plesnivy syr");
		pizza.setWeight(720);
		pizza.setPrice(4.50);

		DaoFactory.INSTANCE.getPizzaDao().savePizza(pizza);
	}

	@Test
	void getAllTest() {
		assertFalse(DaoFactory.INSTANCE.getPizzaDao().getAll().isEmpty());
		assertNotNull(DaoFactory.INSTANCE.getPizzaDao().getAll());

	}

	void savePizzaTest() {
		assertEquals(pizzePred + 1, DaoFactory.INSTANCE.getPizzaDao().getAll().size());

	}

	@Test
	void getByNameTest() {
		if (DaoFactory.INSTANCE.getPizzaDao().getAll().isEmpty() == false
				&& DaoFactory.INSTANCE.getPizzaDao().getAll() != null) {

			String name = "Pizza Hawai";
			assertEquals(DaoFactory.INSTANCE.getPizzaDao().getByName(name).getName(), name);

		}
	}
	
}