package upjs.sk.pizza_int.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import upjs.sk.pizza_int.EditPizzaPageController;
import upjs.sk.pizza_int.Interfaces.PizzaDao;
import upjs.sk.pizza_int.Models.Pizza;

public class MysqlPizzaDao implements PizzaDao {

	// napojenie sa na tabulku pizzaList v databaze ...

	private JdbcTemplate jdbcTemplate;

	public MysqlPizzaDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// metoda na ziskanie vsetkych pizz z databazy ...
	@Override
	public List<Pizza> getAll() {
		String sql = "SELECT idPizzaList, Name, Description, Weight, WeightType, Price, Currency FROM pizzalist ORDER BY idPizzaList;";
		// postupne prechadzame databazou a ukladame pizze do listu, ktory na konci
		// vratime
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

	// za databazy vyberieme pizzu ktorej prislucha dane meno
	@Override
	public Pizza getByName(String pizzaName) {
		String sql = "SELECT * FROM pizzalist WHERE Name = ?;";

		try {
			// ak sme nasli taku pizzu, ktora ma prislusne meno, tak ju
			// nasetujeme a vratime ju (otaznik v selecte nizssie nahradi meno)
			Pizza pizza = jdbcTemplate.queryForObject(sql, new Object[] { pizzaName }, new RowMapper<Pizza>() {

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
	
	@Override
	public Pizza savePizza(Pizza pizza) {
		if (pizza.getName().length() == 0 || pizza.getDescription().length() == 0 || pizza.getWeight() == 0
				|| pizza.getPrice() == 0) {
			return null;
		}

		if (pizza.getId() == null) {
			SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
			simpleJdbcInsert.withTableName("pizzalist");
			simpleJdbcInsert.usingColumns("Name", "Description", "Weight", "Price");
			simpleJdbcInsert.usingGeneratedKeyColumns("idPizzaList");

			Map<String, Object> values = new HashMap<String, Object>();
			values.put("Name", pizza.getName());
			values.put("Description", pizza.getDescription());
			values.put("Weight", pizza.getWeight());
			values.put("Price", pizza.getPrice());

			long id = simpleJdbcInsert.executeAndReturnKey(values).longValue();
			pizza.setId(id);
		}

		return pizza;
	}
	
	@Override
	public Pizza editPizza (Pizza pizza) {
		
		String sql = "UPDATE `pizza_int`.`pizzalist` SET "
				+ "`Name` = '" + pizza.getName() + "', "
				+ "`Description` = '" + pizza.getDescription() + "', "
				+ "`Weight` = '" + pizza.getWeight() + "', `Price` = '" + pizza.getPrice() + "' "
				+ "WHERE (`Name` = '" + EditPizzaPageController.selectedPizza.getName() + "');";
		jdbcTemplate.update(sql);
		
		return pizza;
	}

}
