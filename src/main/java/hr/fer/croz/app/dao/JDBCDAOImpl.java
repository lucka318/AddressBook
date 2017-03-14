package hr.fer.croz.app.dao;

import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;
import hr.fer.croz.app.model.Sex;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class JDBCDAOImpl implements DAO {

	private JdbcTemplate jdbcTemplate;

	public JDBCDAOImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		setContactID();
		setAddressID();
		setCityID();
		setCountryID();
	}

	public Contact getContact(Long id) {
		String sql = "SELECT * FROM contact WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			public Contact extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("id"));
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setPhone(rs.getString("phone"));
					contact.setEmail(rs.getString("email"));
					long sex_id = rs.getLong("sex_id");
					contact.setSex_id(sex_id);
					contact.setSex(Sex.getInstance().getGenderName(sex_id));
					long address_id = rs.getLong("address_id");
					contact.setAddress_id(address_id);
					contact.setSex(getSex(sex_id));
					contact.setAddress(getAddress(address_id));
					return contact;
				}

				return null;
			}

		});
	}

	public Address getAddress(Long id) {
		String sql = "SELECT * FROM address WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Address>() {

			public Address extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Address address = new Address();
					address.setId(rs.getLong("id"));
					address.setStreetName(rs.getString("street"));
					address.setStreetNo(rs.getString("street_no"));
					long city_id = rs.getLong("city_id");
					address.setCity_id(city_id);
					address.setCity(getCity(city_id));
					return address;
				}

				return null;
			}

		});
	}

	public City getCity(long id) {
		String sql = "SELECT * FROM city WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<City>() {

			public City extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					City city = new City();
					city.setId(rs.getLong("id"));
					city.setName(rs.getString("name"));
					city.setZipcode(rs.getString("zip_code"));
					long country_id = rs.getLong("country_id");
					city.setCountry_id(country_id);
					city.setCountry(getCountry(country_id));
					return city;
				}

				return null;
			}

		});
	}

	public Country getCountry(long id) {
		String sql = "SELECT * FROM country WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Country>() {

			public Country extractData(ResultSet rs) throws SQLException,
					DataAccessException {
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

	public Address getAddress(String name, String no) {
		String sql = "SELECT * FROM address WHERE street='" + name + "'"
				+ " AND street_no='" + no + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Address>() {

			public Address extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					Address address = new Address();
					address.setId(rs.getLong("id"));
					address.setStreetName(rs.getString("street"));
					address.setStreetNo(rs.getString("street_no"));
					long city_id = rs.getLong("city_id");
					address.setCity_id(city_id);
					address.setCity(getCity(city_id));
					return address;
				}

				return null;
			}

		});
	}

	public City getCity(String name, String zipcode) {
		String sql = "SELECT * FROM city WHERE name='" + name + "'"
				+ " AND zip_code='" + zipcode + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<City>() {

			public City extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					City city = new City();
					city.setId(rs.getLong("id"));
					city.setName(rs.getString("name"));
					city.setZipcode(rs.getString("zip_code"));
					city.setCountry_id(rs.getLong("country_id"));
					return city;
				}

				return null;
			}

		});
	}

	public Country getCountry(String name) {
		String sql = "SELECT * FROM country WHERE name='" + name + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Country>() {

			public Country extractData(ResultSet rs) throws SQLException,
					DataAccessException {
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

	public String getSex(Long id) {
		String sql = "SELECT * FROM sex WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<String>() {

			public String extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					return rs.getString("name");
				}

				return "";
			}

		});
	}

	public List<Contact> getContacts() {
		String sql = "SELECT * FROM contact";
		List<Contact> contacts = jdbcTemplate.query(sql,
				new RowMapper<Contact>() {

					public Contact mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Contact contact = new Contact();
						contact.setId(rs.getInt("id"));
						contact.setFirstName(rs.getString("first_name"));
						contact.setLastName(rs.getString("last_name"));
						contact.setPhone(rs.getString("phone"));
						contact.setEmail(rs.getString("email"));
						long sex_id = rs.getLong("sex_id");
						contact.setSex_id(sex_id);
						contact.setSex(Sex.getInstance().getGenderName(sex_id));
						long address_id = rs.getLong("address_id");
						contact.setAddress_id(address_id);
						contact.setSex(getSex(sex_id));
						contact.setAddress(getAddress(address_id));
						return contact;
					}

				});

		return contacts;
	}

	public List<City> getCities() {
		String sql = "SELECT * FROM city";
		List<City> cities = jdbcTemplate.query(sql, new RowMapper<City>() {

			public City mapRow(ResultSet rs, int rowNum) throws SQLException {
				City city = new City();
				city.setId(rs.getLong("id"));
				city.setName(rs.getString("name"));
				city.setZipcode(rs.getString("zip_code"));
				long country_id = rs.getLong("country_id");
				city.setCountry_id(country_id);
				city.setCountry(getCountry(country_id));
				return city;
			}

		});

		return cities;
	}

	public List<Country> getCountries() {
		String sql = "SELECT * FROM country";
		List<Country> countries = jdbcTemplate.query(sql,
				new RowMapper<Country>() {

					public Country mapRow(ResultSet rs, int rowNum)
							throws SQLException {
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

	public void createTuple(Address address) {
		if (address.getId() > 0) {
			// update
			String sql = "UPDATE address SET street=?, street_no=?, city_id=?"
					+ " WHERE id=?";
			jdbcTemplate.update(sql, address.getStreetName(),
					address.getStreetNo(), address.getCity_id(),
					address.getId());
		} else {
			// insert
			Address checkAddress = getAddress(address.getStreetName(),
					address.getStreetNo());
			if (checkAddress == null) {
				address.setId();
				String sql = "INSERT INTO address (id, street, street_no, city_id)"
						+ " VALUES (?, ?, ?, ?)";
				jdbcTemplate.update(sql, address.getId(),
						address.getStreetName(), address.getStreetNo(),
						address.getCity_id());
			} else {
				address.setId(checkAddress.getId());
			}
		}
	}

	public void createTuple(Contact contact) {
		if (contact.getId() > 0) {
			// update
			String sql = "UPDATE contact SET first_name=?, last_name=?, phone=?, email=?, sex_id=?, address_id=?"
					+ " WHERE id=?";
			jdbcTemplate.update(sql, contact.getFirstName(),
					contact.getLastName(), contact.getPhone(),
					contact.getEmail(), contact.getSex_id(),
					contact.getAddress_id(), contact.getId());
		} else {
			// insert
			contact.setId();
			String sql = "INSERT INTO contact (id, first_name, last_name, phone, email, sex_id, address_id)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(),
					contact.getLastName(), contact.getPhone(),
					contact.getEmail(), contact.getSex_id(),
					contact.getAddress_id());
		}
	}

	public void createTuple(City city) {
		if (city.getId() > 0) {
			// update
			String sql = "UPDATE city SET name=?, zip_code=?, country_id=?"
					+ " WHERE id=?";
			jdbcTemplate.update(sql, city.getName(), city.getZipcode(),
					city.getCountry_id(), city.getId());
		} else {
			// insert
			City checkCity = getCity(city.getName(), city.getZipcode());
			if (checkCity == null) {
				city.setId();
				String sql = "INSERT INTO city (id, name, zip_code, country_id)"
						+ " VALUES (?, ?, ?, ?)";
				jdbcTemplate.update(sql, city.getId(), city.getName(),
						city.getZipcode(), city.getCountry_id());
			} else {
				city.setId(checkCity.getId());
			}
		}
	}

	public void createTuple(Country country) {
		if (country.getId() > 0) {
			// update
			String sql = "UPDATE country SET name=?, alpha_2=?, alpha_3=?"
					+ " WHERE id=?";
			jdbcTemplate.update(sql, country.getName(), country.getAlpha_2(),
					country.getAlpha_3(), country.getId());
		} else {
			// insert
			Country checkCountry = getCountry(country.getName());
			if (checkCountry == null) {
				country.setId();
				String sql = "INSERT INTO country (id, name, alpha_2, alpha_3)"
						+ " VALUES (?, ?, ?, ?)";
				jdbcTemplate.update(sql, country.getId(), country.getName(),
						country.getAlpha_2(), country.getAlpha_3());
			} else {
				country.setId(checkCountry.getId());
			}
		}
	}

	public void deleteContact(Long id) {
		String sql = "DELETE FROM contact WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	public int deleteCity(Long id) {
		String sql = "SELECT * FROM address WHERE city_id=" + id.longValue();
		Address address = jdbcTemplate.query(sql,
				new ResultSetExtractor<Address>() {

					public Address extractData(ResultSet rs)
							throws SQLException, DataAccessException {
						if (rs.next()) {
							Address address = new Address();
							address.setId(rs.getLong("id"));
							address.setStreetName(rs.getString("street"));
							address.setStreetNo(rs.getString("street_no"));
							long city_id = rs.getLong("city_id");
							address.setCity_id(city_id);
							address.setCity(getCity(city_id));
							return address;
						}

						return null;
					}

				});
		if (address == null) {
			sql = "DELETE FROM city WHERE id=?";
			jdbcTemplate.update(sql, id);
			return 0;
		}
		return 1;
	}

	public int deleteCountry(Long id) {
		String sql = "SELECT * FROM city WHERE id=" + id;
		City city = jdbcTemplate.query(sql, new ResultSetExtractor<City>() {

			public City extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				if (rs.next()) {
					City city = new City();
					city.setId(rs.getLong("id"));
					city.setName(rs.getString("name"));
					city.setZipcode(rs.getString("zip_code"));
					long country_id = rs.getLong("country_id");
					city.setCountry_id(country_id);
					city.setCountry(getCountry(country_id));
					return city;
				}

				return null;
			}

		});

		if (city == null) {
			sql = "DELETE FROM country WHERE id=?";
			jdbcTemplate.update(sql, id);
			return 0;
		}
		return 1;
	}

	private void setContactID() {
		String sql = "SELECT COALESCE(max(id),0) FROM contact";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		Contact.setID_CNT(id);
	}

	private void setAddressID() {
		String sql = "SELECT COALESCE(max(id),0) FROM address";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		Address.setID_CNT(id);
	}

	private void setCityID() {
		String sql = "SELECT COALESCE(max(id),0) FROM city";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		City.setID_CNT(id);
	}

	private void setCountryID() {
		String sql = "SELECT COALESCE(max(id),0) FROM country";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		Country.setID_CNT(id);
	}

}
