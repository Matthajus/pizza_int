package upjs.sk.pizza_int;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;



public enum DaoFactory {
	INSTANCE;

	private MysqlUserDao mysqlUserDao;
	private JdbcTemplate jdbcTemplate;
	
	public MysqlUserDao getUserDao() {
		if (mysqlUserDao == null) {
			mysqlUserDao = new MysqlUserDao(getJdbcTemplate());
		}
		return mysqlUserDao;
	}
	
	private JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			MysqlDataSource dataSource = new MysqlDataSource();
			dataSource.setUser("admin");
			dataSource.setPassword("admin");
			dataSource.setUrl("jdbc:mysql://localhost/pizza_int?serverTimezone=Europe/Bratislava");
			jdbcTemplate =  new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

}
