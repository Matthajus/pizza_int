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

public class MysqlUserDao implements UserDao {

	// napojenie sa na tabulku Users v databaze

	private JdbcTemplate jdbcTemplate;

	public MysqlUserDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	// metoda na ziskanie vsetkych userov z databazy
	public List<User> getAll() {
		String sql = "SELECT idUsers, Name, Surname, Login, Password, Email, Tel_number, IsicCardNumber, Role FROM users ORDER BY idUsers;";
		// postupne prechadzame databazou a ukladame userov do listu, ktory na konci vratime
		List<User> users = jdbcTemplate.query(sql, new ResultSetExtractor<List<User>>() {

			public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
				List<User> result = new ArrayList<User>();

				while (rs.next()) {
					User user = new User();
					user.setId(rs.getLong("idUsers"));
					user.setName(rs.getString("Name"));
					user.setSurname(rs.getString("Surname"));
					user.setLogin(rs.getString("Login"));
					user.setPassword(rs.getString("Password"));
					user.setEmail(rs.getString("Email"));
					user.setTel_number(rs.getString("Tel_number"));
					user.setIsicCardNumber(rs.getString("IsicCardNumber"));
					user.setRole(rs.getInt("Role"));
					result.add(user);
				}
				return result;
			}

		});
		return users;
	}

	// metoda na zistenie, ci sa v databaze nachadza user podla loginu a hesla
	public User getUserByLogin(String login, String password) {
		String sql = "SELECT idUsers, Name, Surname, Login, Password, Email, Tel_number, IsicCardNumber, Role FROM users WHERE Login = ? and Password = ?;";

		try {
			// ak sme nasli takeho usera, ktory ma prislusny login a heslo, tak ho
			// nasetujeme a vratime ho (otazniky v selecte sa nizssie nahradia za meno a heslo)
			User user = jdbcTemplate.queryForObject(sql, new Object[] { login, password }, new RowMapper<User>() {

				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
					User user = new User();
					user.setId(rs.getLong("idUsers"));
					user.setName(rs.getString("Name"));
					user.setSurname(rs.getString("Surname"));
					user.setLogin(rs.getString("Login"));
					user.setPassword(rs.getString("Password"));
					user.setEmail(rs.getString("Email"));
					user.setTel_number(rs.getString("Tel_number"));
					user.setIsicCardNumber(rs.getString("IsicCardNumber"));
					user.setRole(rs.getInt("Role"));
					return user;
				}
			});
			return user;
		} catch (EmptyResultDataAccessException e) {
			// ak sa taky user nenachadza vratime null
			return null;
		}
	}

	public User promoteUser(String login) {
		return null;
	}

	public User demoteUser(String login) {
		return null;
	}

}
