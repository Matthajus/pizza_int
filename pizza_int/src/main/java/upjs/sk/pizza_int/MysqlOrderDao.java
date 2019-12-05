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
}