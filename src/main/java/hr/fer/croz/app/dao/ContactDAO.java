package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.Contact;

/**
 * Interface DAO represents data access object between database with entities
 * <code>Contact</code>, <code>Address</code>, <code>Sex</code>,
 * <code>City</code> and <code>Country</code> and controller
 * <code>HomeController</code> Interface DAO consists of a list of methods for
 * saving and fetching rows from a database. Those methods are implemented in
 * <code>JDBCDAOImpl</code>.
 * 
 * @author Lucija Megla
 *
 */
public interface ContactDAO {

	public Contact getContact(Long id);

	public Contact getContact(String firstName, String lastName, String phone, String email, long sex_id);

	public List<Contact> getContacts();

	public Contact createTuple(Contact contact);

	public void deleteContact(Long id);

	public Contact updateTuple(Contact contact);

	public boolean contactExists(Contact contact);

	public List<Contact> getContacts(long addressId);

}
