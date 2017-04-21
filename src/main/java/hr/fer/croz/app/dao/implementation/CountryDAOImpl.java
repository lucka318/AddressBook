package hr.fer.croz.app.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import hr.fer.croz.app.dao.CountryDAO;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Country;

public class CountryDAOImpl implements CountryDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public CountryDAOImpl() {
	}

	public Country createTuple(Country country) {
		String sql = "INSERT INTO country (name, alpha_2, alpha_3)" + " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, country.getName(), country.getAlpha_2(), country.getAlpha_3());

		sql = "SELECT last_value FROM country_id_seq";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		country.setId(id);

		return country;
	}

	public Country updateTuple(Country country) {
		String sql = "UPDATE country SET name=?, alpha_2=?, alpha_3=?" + " WHERE id=?";
		jdbcTemplate.update(sql, country.getName(), country.getAlpha_2(), country.getAlpha_3(), country.getId());
		return country;
	}

	public Country getCountry(long id) {
		String sql = "SELECT * FROM country WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Country>() {

			public Country extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Country country = new Country();
					country.setId(rs.getLong("id"));
					country.setName(rs.getString("name"));
					country.setAlpha_2(rs.getString("alpha_2"));
					country.setAlpha_3(rs.getString("alpha_3"));
					return country;
				}

				return null;
			}

		});
	}

	public Country getCountry(String name) {
		String sql = "SELECT * FROM country WHERE name='" + name + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Country>() {

			public Country extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Country country = new Country();
					country.setId(rs.getLong("id"));
					country.setName(rs.getString("name"));
					country.setAlpha_2(rs.getString("alpha_2"));
					country.setAlpha_3(rs.getString("alpha_3"));
					return country;
				}

				return null;
			}

		});
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

	public List<Country> getCountries() {
		String sql = "SELECT * FROM country";
		List<Country> countries = jdbcTemplate.query(sql, new RowMapper<Country>() {

			public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
				Country country = new Country();
				country.setId(rs.getLong("id"));
				country.setName(rs.getString("name"));
				country.setAlpha_2(rs.getString("alpha_2"));
				country.setAlpha_3(rs.getString("alpha_3"));
				return country;
			}

		});

		return countries;
	}

	public void deleteCountry(Long id) {
		String sql = "DELETE FROM country WHERE id=?";
		jdbcTemplate.update(sql, id);
		return;
	}

	/**
	 * Method checks if given country exists in database.
	 * 
	 * @param ccountry
	 * @return true if given Country exists in database, false otherwise
	 */
	public boolean countryExists(Country country) {
		Country countryToCheck = getCountry(country.getName());
		if (countryToCheck == null) {
			return false;
		} else {
			return true;
		}
	}

}
