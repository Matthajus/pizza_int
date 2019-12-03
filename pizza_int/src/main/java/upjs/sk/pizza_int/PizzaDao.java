package upjs.sk.pizza_int;

import java.util.List;

public interface PizzaDao {

	List<Pizza> getAll();

	Pizza save(Pizza subject);

	Pizza getById(long subjectID);

}
