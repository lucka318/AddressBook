package hr.fer.croz.app.dao;

import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;

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
	}

	private void setContactID() {
		String sql = "SELECT COALESCE(max(id),0) FROM contact";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		Contact.setID_CNT(id);
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
					contact.setAddress(rs.getString("address"));
					contact.setPhone(rs.getString("phone"));
					contact.setSex(rs.getString("sex"));
					contact.setEmail(rs.getString("email"));
					return contact;
				}

				return null;
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
						contact.setAddress(rs.getString("address"));
						contact.setPhone(rs.getString("phone"));
						contact.setSex(rs.getString("sex"));
						contact.setEmail(rs.getString("email"));

						return contact;
					}

				});

		return contacts;
	}

	public List<City> getCities() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Country> getCountries() {
		// TODO Auto-generated method stub
		return null;
	}

	public void createTuple(Contact contact) {
		if (contact.getId() > 0) {
			// update
			String sql = "UPDATE contact SET first_name=?, last_name=?, phone=?, email=?, sex=?, address=?"
					+ " WHERE id=?";
			jdbcTemplate.update(sql, contact.getFirstName(),
					contact.getLastName(), contact.getPhone(),
					contact.getEmail(), contact.getSex(), contact.getAddress(),
					contact.getId());
		} else {
			// insert
			contact.setId();
			String sql = "INSERT INTO contact (id, first_name, last_name, phone, email, sex, address)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
			jdbcTemplate.update(sql, contact.getId(), contact.getFirstName(),
					contact.getLastName(), contact.getPhone(),
					contact.getEmail(), contact.getSex(), contact.getAddress());
		}
	}

	public void createTuple(City city) {
		// TODO Auto-generated method stub
		return;
	}

	public void createTuple(Country country) {
		// TODO Auto-generated method stub
		return;
	}

	public void deleteContact(Long id) {
		String sql = "DELETE FROM contact WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	public int deleteCity(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int deleteCountry(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateContact(Long id, Contact contact) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateCity(Long id, City city) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int updateCountry(Long id, Country country) {
		// TODO Auto-generated method stub
		return 0;
	}

}
