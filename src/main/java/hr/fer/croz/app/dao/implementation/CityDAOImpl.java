package hr.fer.croz.app.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import hr.fer.croz.app.dao.CityDAO;
import hr.fer.croz.app.model.City;

public class CityDAOImpl implements CityDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CityDAOImpl() {
	}

	public City createTuple(City city) {
		String sql = "INSERT INTO city (name, zip_code, country_id)" + " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, city.getName(), city.getZipcode(), city.getCountry());

		sql = "SELECT last_value FROM city_id_seq";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		city.setId(id);

		return city;

	}

	public City updateTuple(City city) {
		String sql = "UPDATE city SET name=?, zip_code=?, country_id=?" + " WHERE id=?";
		jdbcTemplate.update(sql, city.getName(), city.getZipcode(), city.getCountry(), city.getId());
		return city;
	}

	public City getCity(long id) {
		String sql = "SELECT * FROM city WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<City>() {

			public City extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					City city = new City();
					city.setId(rs.getLong("id"));
					city.setName(rs.getString("name"));
					city.setZipcode(rs.getString("zip_code"));
					city.setCountryID(rs.getLong("country_id"));
					return city;
				}

				return null;
			}

		});
	}

	public City getCity(String name, String zipcode, Long country_id) {
		String sql = "SELECT * FROM city WHERE name='" + name + "'" + " AND zip_code='" + zipcode + " AND country_id="
				+ "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<City>() {

			public City extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					City city = new City();
					city.setId(rs.getLong("id"));
					city.setName(rs.getString("name"));
					city.setZipcode(rs.getString("zip_code"));
					return city;
				}

				return null;
			}

		});
	}

	// provjeriti postoji li adresa koja ovisi o gradu - u menageru
	public void deleteCity(Long id) {
		String sql = "DELETE FROM city WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;
	}

	/**
	 * Method checks if given city exists in database.
	 * 
	 * @param city
	 * @return true if given City exists in database, false otherwise
	 */
	public boolean cityExists(City city) {
		City cityToCheck = getCity(city.getName(), city.getZipcode(), city.getCountryID());
		if (cityToCheck == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<City> getCities() {
		String sql = "SELECT * FROM city";
		List<City> cities = jdbcTemplate.query(sql, new RowMapper<City>() {

			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City city = new City();
				city.setId(rs.getLong("id"));
				city.setName(rs.getString("name"));
				city.setZipcode(rs.getString("zip_code"));
				city.setCountryID(rs.getLong("country_id"));
				return city;
			}

		});

		return cities;
	}

}
