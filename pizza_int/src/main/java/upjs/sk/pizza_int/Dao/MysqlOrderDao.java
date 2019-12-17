package upjs.sk.pizza_int.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

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
		if (order.getDate() == null || order.getAdress().length() == 0 || order.getIdPizza() == 0
				|| order.getIdUser() == 0) {
			return null;
		}

		if (order.getId() == null) {
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
			simpleJdbcInsert.withTableName("order");
			simpleJdbcInsert.usingColumns("Date", "Address", "PizzaList_idPizzaList", "Users_idUsers");
			simpleJdbcInsert.usingGeneratedKeyColumns("idOrder");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("Date", order.getDate());
			values.put("Address", order.getAdress());
			values.put("PizzaList_idPizzaList", order.getIdPizza());
			values.put("Users_idUsers", order.getIdUser());

			long id = simpleJdbcInsert.executeAndReturnKey(values).longValue();
			order.setId(id);
		}

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
					oneOrder += rs.getLong("o.idOrder") + "		";
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
}
