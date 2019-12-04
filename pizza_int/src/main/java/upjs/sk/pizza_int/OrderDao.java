package upjs.sk.pizza_int;

import java.util.List;

public interface OrderDao {

	List<Order> getAll();

	Order save(Order order);

	Order getById(long orderId);

}