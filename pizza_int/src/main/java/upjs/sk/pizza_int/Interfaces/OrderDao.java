package upjs.sk.pizza_int.Interfaces;

import javafx.collections.ObservableList;
import upjs.sk.pizza_int.Models.Order;

public interface OrderDao {

	Order saveOrder(Order order);
	
	ObservableList<String> getAllForHistory();

}