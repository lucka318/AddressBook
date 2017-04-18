package hr.fer.croz.app.dao.implementation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import hr.fer.croz.app.dao.AddressDAO;
import hr.fer.croz.app.model.Address;

public class AddressDAOImpl implements AddressDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public AddressDAOImpl() {
	}

	public Address createTuple(Address address) {
		String sql = "INSERT INTO address (street, street_no, city_id)" + " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, address.getStreetName(), address.getStreetNo(), address.getCity());

		sql = "SELECT last_value FROM address_id_seq";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		address.setId(id);

		return address;
	}

	public Address updateTuple(Address address) {
		String sql = "UPDATE address SET street=?, street_no=?, city_id=?" + " WHERE id=?";
		jdbcTemplate.update(sql, address.getStreetName(), address.getStreetNo(), address.getCity(), address.getId());
		return address;
	}

	public Address getAddress(Long id) {
		String sql = "SELECT * FROM address WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Address>() {

			public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Address address = new Address();
					address.setId(rs.getLong("id"));
					address.setStreetName(rs.getString("street"));
					address.setStreetNo(rs.getString("street_no"));
					address.setCity(rs.getLong("city_id"));
					return address;
				}

				return null;
			}

		});
	}

	public Address getAddress(String name, String no, Long city_id) {
		String sql = "SELECT * FROM address WHERE street='" + name + "'" + " AND street_no='" + no + " AND city_id="
				+ city_id + "'";
		return jdbcTemplate.query(sql, new ResultSetExtractor<Address>() {

			public Address extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Address address = new Address();
					address.setId(rs.getLong("id"));
					address.setStreetName(rs.getString("street"));
					address.setStreetNo(rs.getString("street_no"));
					address.setCity(rs.getLong("city_id"));
					return address;
				}

				return null;
			}

		});
	}

	/**
	 * Method checks if given city exists in database.
	 * 
	 * @param city
	 * @return true if given City exists in database, false otherwise
	 */
	// trebaju li exists metode biti u DaoImpl ili u manageru??
	public boolean addressExists(Address address) {
		Address addressToCheck = getAddress(address.getStreetName(), address.getStreetNo(), address.getCity());
		if (addressToCheck == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<Address> getAddresses() {
		String sql = "SELECT * FROM address";
		List<Address> addresses = jdbcTemplate.query(sql, new RowMapper<Address>() {

			public Address mapRow(ResultSet rs, int rowNum) throws SQLException {
				Address address = new Address();
				address.setId(rs.getLong("id"));
				address.setStreetName(rs.getString("street"));
				address.setStreetNo(rs.getString("street_no"));
				address.setCity(rs.getLong("city_id"));
				return address;
			}

		});

		return addresses;
	}

	public void deleteAddress(Long id) {
		String sql = "DELETE FROM address WHERE id=?";
		jdbcTemplate.update(sql, id);
	}
}
