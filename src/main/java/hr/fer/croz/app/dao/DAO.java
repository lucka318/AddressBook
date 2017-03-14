package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;

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
public interface DAO {

	/**
	 * Get contact from database with given id
	 * 
	 * @param id
	 * @return Contact
	 */
	public Contact getContact(Long id);

	/**
	 * Get City from database.
	 * 
	 * @param cityId
	 * @return City
	 */
	public City getCity(long cityId);

	/**
	 * Get Country from database.
	 * 
	 * @param countryId
	 * @return Country
	 */
	public Country getCountry(long countryId);

	/**
	 * Get all contact in a database
	 * 
	 * @return List<Contact>
	 */
	public List<Contact> getContacts();

	/**
	 * Get all cities in a database
	 * 
	 * @return List<City>
	 */
	public List<City> getCities();

	/**
	 * Get all countries in a database.
	 * 
	 * @return List<Country>
	 */
	public List<Country> getCountries();

	/**
	 * Create new contact in a database.
	 * 
	 * @param contact
	 */
	public void createTuple(Contact contact);

	/**
	 * Create new Address in database.
	 * 
	 * @param address
	 */
	public void createTuple(Address address);

	/**
	 * Create new city in a database
	 * 
	 * @param city
	 */
	public void createTuple(City city);

	/**
	 * Create new country in a database.
	 * 
	 * @param country
	 */
	public void createTuple(Country country);

	/**
	 * Delete Contact with given id.
	 * 
	 * @param id
	 */
	public void deleteContact(Long id);

	/**
	 * Delete City with given id. It won't delete the City if there is an
	 * Address that depends on it.
	 * 
	 * @param id
	 * @return int, 0 if City is deleted, 1 if it's not.
	 */
	public int deleteCity(Long id);

	/**
	 * Delete Country with given id. It won't delete the Country if there is a
	 * City that depends on it.
	 * 
	 * @param id
	 * @return int, 0 if Country is deleted, 1 if it's not.
	 */
	public int deleteCountry(Long id);

}
