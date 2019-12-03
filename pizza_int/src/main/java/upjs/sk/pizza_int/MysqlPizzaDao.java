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

public class MysqlPizzaDao implements PizzaDao {
	
	// napojenie sa na tabulku pizzaList v databaze
	
	private JdbcTemplate jdbcTemplate;
	
	public MysqlPizzaDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	// metoda na ziskanie vsetkych pizz z databazy
	public List<Pizza> getAll() {
		String sql = "SELECT idPizzaList, Name, Description, Weight, WeightType, Price, Currency FROM pizzalist ORDER BY idPizzaList;";
		// postupne prechadzame databazou a ukladame pizze do listu, ktory na konci vratime
		List<Pizza> pizzaList = jdbcTemplate.query(sql, new ResultSetExtractor<List<Pizza>>() {

			public List<Pizza> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<Pizza> result = new ArrayList<Pizza>();

				while (rs.next()) {
					Pizza pizza = new Pizza();
					pizza.setId(rs.getLong("idPizzaList"));
					pizza.setName(rs.getString("Name"));
					pizza.setDescription(rs.getString("Description"));
					pizza.setWeight(rs.getInt("Weight"));
					pizza.setWeightType(rs.getString("WeightType"));
					pizza.setPrice(rs.getDouble("Price"));
					pizza.setCurrency(rs.getString("Currency"));
					result.add(pizza);
				}
				return result;
			}

		});
		return pizzaList;
	}

	public Pizza save(Pizza pizza) {
		return null;
	}

	public Pizza getById(long pizzaId) {
		String sql = "SELECT * FROM pizzalist WHERE idPizzaList = ?;";

		try {
			// ak sme nasli taku pizzu, ktora ma prislusne id-cko, tak ju
			// nasetujeme a vratime ju (otaznik v selecte sa nizssie nahradi id)
			Pizza pizza = jdbcTemplate.queryForObject(sql, new Object[] { pizzaId }, new RowMapper<Pizza>() {

				public Pizza mapRow(ResultSet rs, int rowNum) throws SQLException {
					Pizza pizza = new Pizza();
					pizza.setId(rs.getLong("idPizzaList"));
					pizza.setName(rs.getString("Name"));
					pizza.setDescription(rs.getString("Description"));
					pizza.setWeight(rs.getInt("Weight"));
					pizza.setWeightType(rs.getString("WeightType"));
					pizza.setPrice(rs.getDouble("Price"));
					pizza.setCurrency(rs.getString("Currency"));
					return pizza;
				}
			});
			return pizza;
		} catch (EmptyResultDataAccessException e) {
			// ak sa taka pizza nenachadza vratime null
			return null;
		}
	}

}
