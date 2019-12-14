package upjs.sk.pizza_int;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class MysqlOrderDao implements OrderDao {

	private JdbcTemplate jdbcTemplate;
	
	public MysqlOrderDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// metoda na ziskanie vsetkych objednavok z databazy ...
	public List<Order> getAll() {
		String sql = "SELECT idOrder,Date,Address,PizzaList_idPizzaList,Users_idUsers FROM pizza_int.order ORDER BY idOrder;";
		// postupne prechadzame databazou a ukladame objednavku do listu, ktory na konci vratime
		List<Order> order = jdbcTemplate.query(sql, new ResultSetExtractor<List<Order>>() {

			public List<Order> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Order> result = new ArrayList<Order>();

				while (rs.next()) {
					Order order= new Order();
					order.setId(rs.getLong("idOrder"));
					order.setDate(rs.getDate("Date"));
					order.setAdress(rs.getString("Address"));
					order.setIdPizza(rs.getLong("PizzaList_idPizzaList"));
					order.setIdUser(rs.getLong("Users_idUsers"));
					result.add(order);
				}
				return result;
			}

		});
		return order;
	}
		
		public Order save(Order order) {
			return null;
		}

		public Order getById(long orderId) {
			String sql = "SELECT * FROM order WHERE idOrder = ?;";

			try {
				// ak sme nasli taku objednavku, ktora ma prislusne id-cko, tak ju
				// nasetujeme a vratime ju (otaznik v selecte sa nizssie nahradi id)
				Order order = jdbcTemplate.queryForObject(sql, new Object[] { orderId }, new RowMapper<Order>() {

					public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
						Order order= new Order();
						order.setId(rs.getLong("idOrder"));
						order.setDate(rs.getDate("Date"));
						order.setAdress(rs.getString("Address"));
						order.setIdPizza(rs.getLong("PizzaList_idPizzaList"));
						order.setIdUser(rs.getLong("Users_idUsers"));
						return order;
					}
				});
				return order;
			} catch (EmptyResultDataAccessException e) {
				// ak sa taka objednavka nenachadza vratime null
				return null;
			}
		
	}
		
		// metoda na ziskanie vsetkych objednavok z databazy ...
		public ObservableList<String> getAllForHistory() {
			String sql = "SELECT " + 
						 "o.idOrder, o.Date, o.Address, p.Name, u.Name, u.Surname " + 
						 "FROM " + 
						 "pizza_int.order o " + 
						 "JOIN " + 
						 "pizza_int.pizzalist p ON o.PizzaList_idPizzaList = p.idPizzaList " + 
						 "JOIN " + 
						 "pizza_int.users u ON o.Users_idUsers = u.idUsers " + 
						 "ORDER BY idOrder;";
			// postupne prechadzame databazou a ukladame objednavku do listu, ktory na konci vratime
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
						oneOrder += rs.getString("u.Surname") + " " ;
						result.add(oneOrder);
					}
					return result;
				}

			});
			return order;
		}
}
