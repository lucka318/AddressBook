package hr.fer.croz.app.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import hr.fer.croz.app.dao.GenderDAO;
import hr.fer.croz.app.model.Sex;

public class GenderDAOImpl implements GenderDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public GenderDAOImpl() {
	}

	public Sex getSex(Long id) {
		String sql = "SELECT * FROM sex WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Sex>() {

			public Sex extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Sex sex = new Sex();
					sex.setId(rs.getLong("id"));
					sex.setName(rs.getString("name"));
					return sex;
				}

				return null;
			}

		});
	}

	public List<Sex> getSexes() {
		String sql = "SELECT * FROM sex";
		List<Sex> sexes = jdbcTemplate.query(sql, new RowMapper<Sex>() {

			public Sex mapRow(ResultSet rs, int rowNum) throws SQLException {
				Sex sex = new Sex();
				sex.setId(rs.getLong("id"));
				sex.setName(rs.getString("name"));
				return sex;
			}

		});

		return sexes;
	}

}
