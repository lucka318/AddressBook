package hr.fer.croz.app.dao.implementation;

import hr.fer.croz.app.dao.ContactDAO;
import hr.fer.croz.app.model.Contact;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

/**
 * Class JDBCDAOImpl implements interface <code>DAO</code>.
 * 
 * @author Lucija Megla
 *
 */
public class ContactDAOImpl implements ContactDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	/**
	 * Constructor of the class. It initializes an object for database
	 * communication. Also, it sets Contact, Address, City and Country ID
	 * database counters.
	 * 
	 * @param dataSource
	 */
	public ContactDAOImpl() {
	}

	public Contact getContact(Long id) {
		String sql = "SELECT * FROM contact WHERE id=" + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("id"));
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setPhone(rs.getString("phone"));
					contact.setEmail(rs.getString("email"));
					contact.setSexID(rs.getLong("sex_id"));
					contact.setAddressID(rs.getLong("address_id"));
					return contact;
				}

				return null;
			}

		});
	}

	public Contact getContact(String firstName, String lastName, String phone, String email, long sex_id,
			long address_id) {
		String sql = "SELECT * FROM contact WHERE first_name='" + firstName + "' AND last_name='" + lastName
				+ "' AND phone='" + phone + "' AND email='" + email + "' AND sex_id=" + sex_id + " AND address_id="
				+ address_id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Contact>() {

			public Contact extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Contact contact = new Contact();
					contact.setId(rs.getInt("id"));
					contact.setFirstName(rs.getString("first_name"));
					contact.setLastName(rs.getString("last_name"));
					contact.setPhone(rs.getString("phone"));
					contact.setEmail(rs.getString("email"));
					contact.setSexID(rs.getLong("sex_id"));
					contact.setAddressID(rs.getLong("address_id"));
					return contact;
				}

				return null;
			}

		});
	}

	public List<Contact> getContacts() {
		String sql = "SELECT * FROM contact";
		List<Contact> contacts = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setFirstName(rs.getString("first_name"));
				contact.setLastName(rs.getString("last_name"));
				contact.setPhone(rs.getString("phone"));
				contact.setEmail(rs.getString("email"));
				contact.setSexID(rs.getLong("sex_id"));
				contact.setAddressID(rs.getLong("address_id"));
				return contact;
			}

		});

		return contacts;
	}

	public Contact createTuple(Contact contact) {

		String sql = "INSERT INTO contact (first_name, last_name, phone, email, sex_id, address_id)"
				+ " VALUES (?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail(),
				contact.getSexID(), contact.getAddressID());

		sql = "SELECT last_value FROM contact_id_seq";
		long id = jdbcTemplate.queryForObject(sql, Long.class);
		contact.setId(id);

		return contact;
	}

	public Contact updateTuple(Contact contact) {
		String sql = "UPDATE contact SET first_name=?, last_name=?, phone=?, email=?, sex_id=?, address_id=?"
				+ " WHERE id=?";
		jdbcTemplate.update(sql, contact.getFirstName(), contact.getLastName(), contact.getPhone(), contact.getEmail(),
				contact.getSexID(), contact.getAddressID(), contact.getId());
		return contact;
	}

	public void deleteContact(Long id) {
		String sql = "DELETE FROM contact WHERE id=?";
		jdbcTemplate.update(sql, id);
	}

	public boolean contactExists(Contact contact) {
		Contact contactToCheck = getContact(contact.getFirstName(), contact.getLastName(), contact.getPhone(),
				contact.getEmail(), contact.getSexID(), contact.getAddressID()); // napraviti metodu
															// koja
		// dohvaca cijeli kontakt
		if (contactToCheck == null) {
			return false;
		} else {
			return true;
		}
	}

	public List<Contact> getContacts(long addressId) {
		String sql = "SELECT * FROM contact WHERE address_id=" + addressId;
		List<Contact> contacts = jdbcTemplate.query(sql, new RowMapper<Contact>() {

			public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
				Contact contact = new Contact();
				contact.setId(rs.getInt("id"));
				contact.setFirstName(rs.getString("first_name"));
				contact.setLastName(rs.getString("last_name"));
				contact.setPhone(rs.getString("phone"));
				contact.setEmail(rs.getString("email"));
				contact.setSexID(rs.getLong("sex_id"));
				contact.setAddressID(rs.getLong("address_id"));
				return contact;
			}

		});

		return contacts;
	}
}
