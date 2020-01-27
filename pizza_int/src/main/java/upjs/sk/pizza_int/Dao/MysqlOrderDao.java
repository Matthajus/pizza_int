package upjs.sk.pizza_int.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import upjs.sk.pizza_int.Interfaces.OrderDao;
import upjs.sk.pizza_int.Models.Order;

public class MysqlOrderDao implements OrderDao {

	private JdbcTemplate jdbcTemplate;

	public MysqlOrderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Order saveOrder(Order order) {

		String sql = "INSERT INTO `pizza_int`.`order` (`Date`, `Address`, `PizzaList_idPizzaList`, `Users_idUsers`) VALUES (current_timestamp(), '"
				+ order.getAdress() + "', '" + order.getIdPizza() + "', '" + order.getIdUser() + "');";
		jdbcTemplate.update(sql);

		return order;
	}

	// metoda na ziskanie vsetkych objednavok z databazy ...
	@Override
	public ObservableList<String> getAllForHistory() {
		String sql = "SELECT " + "o.idOrder, o.Date, o.Address, p.Name, u.Name, u.Surname " + "FROM "
				+ "pizza_int.order o " + "JOIN " + "pizza_int.pizzalist p ON o.PizzaList_idPizzaList = p.idPizzaList "
				+ "JOIN " + "pizza_int.users u ON o.Users_idUsers = u.idUsers " + "ORDER BY idOrder;";
		// postupne prechadzame databazou a ukladame objednavku do listu, ktory na konci
		// vratime
		ObservableList<String> order = jdbcTemplate.query(sql, new ResultSetExtractor<ObservableList<String>>() {

			public ObservableList<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
				ObservableList<String> result = FXCollections.observableArrayList();

				while (rs.next()) {
					String oneOrder = "";
					oneOrder += rs.getLong("o.idOrder") + "	";
					oneOrder += rs.getDate("o.Date") + " ";
					oneOrder += rs.getString("o.Address") + " ";
					oneOrder += rs.getString("p.Name") + " ";
					oneOrder += rs.getString("u.Name") + " ";
					oneOrder += rs.getString("u.Surname") + " ";
					result.add(oneOrder);
				}
				return result;
			}

		});
		return order;
	}
	
	@Override
	public void deletOrder(int orderID) {
		
		String sql = "DELETE FROM `pizza_int`.`order` WHERE (`idOrder` = " + orderID +");";
		jdbcTemplate.update(sql);
		
	}

}
