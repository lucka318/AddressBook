package hr.fer.croz.app.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import hr.fer.croz.app.dao.GenderDAO;

public class GenderDAOImpl implements GenderDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public GenderDAOImpl() {
	}
	
	public String getSex(Long id) {
		String sql = "SELECT * FROM sex WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			public String extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					return rs.getString("name");
				}

				return "";
			}

		});
	}

}
