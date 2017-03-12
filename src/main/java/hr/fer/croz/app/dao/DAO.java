package hr.fer.croz.app.dao;

import java.util.List;

import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;

public interface DAO {

	public Contact getContact(Long id);

	public List<Contact> getContacts();

	public List<City> getCities();

	public List<Country> getCountries();

	public void createTuple(Contact contact);

	public void createTuple(City city);

	public void createTuple(Country country);

	public void deleteContact(Long id);

	public int deleteCity(Long id);

	public int deleteCountry(Long id);

	public int updateContact(Long id, Contact contact);

	public int updateCity(Long id, City city);

	public int updateCountry(Long id, Country country);

}
