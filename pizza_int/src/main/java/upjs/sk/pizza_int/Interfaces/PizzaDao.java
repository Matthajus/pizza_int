package upjs.sk.pizza_int.Interfaces;

import java.util.List;

import upjs.sk.pizza_int.Models.Pizza;

public interface PizzaDao {

	List<Pizza> getAll();

	Pizza savePizza(Pizza pizza);
	
	Pizza getByName(String pizzaName);

	Pizza editPizza(Pizza pizza);

}
