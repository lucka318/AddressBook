package hr.fer.croz.app.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hr.fer.croz.app.dao.AddressDAO;
import hr.fer.croz.app.dao.CityDAO;
import hr.fer.croz.app.dao.ContactDAO;
import hr.fer.croz.app.dao.CountryDAO;
import hr.fer.croz.app.dao.GenderDAO;
import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.AddressEntity;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.ContactEntity;
import hr.fer.croz.app.model.Country;
import hr.fer.croz.app.model.Sex;

public class AddressBookManager {

	@Autowired
	private ContactDAO contactDAO;
	@Autowired
	private GenderDAO genderDAO;
	@Autowired
	private AddressDAO addressDAO;
	@Autowired
	private CityDAO cityDAO;
	@Autowired
	private CountryDAO countryDAO;

	public AddressBookManager() {
	}

	public List<Contact> fetchContacts() {
		List<Contact> contacts = contactDAO.getContacts();
		for (Contact c : contacts) {
			c.setAddress(getAddressObjectTree(c.getAddressID()));
			c.setSex(getSexObjectTree(c.getSexID()));
		}
		return contacts;
	}

	private Sex getSexObjectTree(long sexID) {
		Sex sex = genderDAO.getSex(sexID);
		return sex;
	}

	private Address getAddressObjectTree(long addressID) {
		Address address = addressDAO.getAddress(addressID);
		long cityID = address.getCityID();
		City city = cityDAO.getCity(cityID);
		long countryID = city.getCountryID();
		Country country = countryDAO.getCountry(countryID);
		city.setCountry(country);
		address.setCity(city);
		return address;
	}

	public List<Address> fetchAddresses() {
		List<Address> addresses = addressDAO.getAddresses();
		for (Address a : addresses) {
			a.setCity(getCityObjectTree(a.getCityID()));
		}
		return addresses;
	}

	private City getCityObjectTree(long cityID) {
		City city = cityDAO.getCity(cityID);
		long countryID = city.getCountryID();
		Country country = countryDAO.getCountry(countryID);
		city.setCountry(country);
		return city;
	}

	public List<City> fetchCities() {
		List<City> cities = cityDAO.getCities();
		for (City c : cities) {
			c.setCountry(getCountryObjectTree(c.getCountryID()));
		}
		return cities;
	}

	private Country getCountryObjectTree(long countryID) {
		Country country = countryDAO.getCountry(countryID);
		return country;
	}

	private String saveToDatabaseNewContact(Contact contact) {
		String error = "";
		if (contact != null) {
			boolean check = contactDAO.contactExists(contact);
			if (!check) {
				contact = contactDAO.createTuple(contact);
			} else {
				error = "Contact already exists in database";
			}
		}
		return error;
	}

	private String saveToDatabaseNewAddress(Address address) {
		String error = "";
		if (address != null) {
			boolean check = addressDAO.addressExists(address);
			if (!check) {
				address = addressDAO.createTuple(address);
			} else {
				error = "Address is already in database.";
			}
		}
		return error;
	}

	private String saveToDatabaseUpdateContact(Contact contact) {
		String error = "";
		if (contact != null) {
			boolean check = contactDAO.contactExists(contact);
			if (!check) {
				contact = contactDAO.updateTuple(contact);
			} else {
				error = "Contact already exists in database";
			}
		}
		return error;
	}

	private String saveToDatabaseUpdateAddress(Address address) {
		String error = "";
		if (address != null) {
			boolean check = addressDAO.addressExists(address);
			if (!check) {
				address = addressDAO.updateTuple(address);
			} else {
				error = "Address is already in database.";
			}
		}
		return error;
	}

	public void deleteContactFromDatabase(long contactId) {
		contactDAO.deleteContact(contactId);
	}

	public String deleteAddressFromDatabase(long addressId) {
		List<Contact> contacts = contactDAO.getContacts(addressId);
		if (contacts.isEmpty()) {
			addressDAO.deleteAddress(addressId);
			return "";
		} else {
			Address address = addressDAO.getAddress(addressId);
			return "There exists at least one contact that has " + address.getStreetName() + " " + address.getStreetNo()
					+ "as an address. Address cannot be deleted.";
		}
	}

	public ContactEntity prepareContactEntity(long contactId) {
		Contact contact = contactDAO.getContact(contactId);
		contact.setAddress(getAddressObjectTree(contact.getAddressID()));

		ContactEntity contactEntity = new ContactEntity();
		contactEntity.setFirstName(contact.getFirstName());
		contactEntity.setLastName(contact.getLastName());
		contactEntity.setEmail(contact.getEmail());
		contactEntity.setPhone(contact.getPhone());
		contactEntity.setGender(contact.getSexID());
		contactEntity.setAddressID(contact.getAddressID());
		return contactEntity;
	}

	public String saveNewToDatabase(AddressEntity addressEntity) {
		Address address = extractAddress(addressEntity);
		String error = saveToDatabaseNewAddress(address);
		return error;

	}

	private Address extractAddress(AddressEntity addressEntity) {
		Address address = new Address();
		address.setId(addressEntity.getId());
		address.setStreetName(addressEntity.getStreetName());
		address.setStreetNo(addressEntity.getStreetNo());
		address.setCityID(addressEntity.getCityID());
		return address;
	}

	public String saveNewToDatabase(ContactEntity contactEntity) {
		Contact contact = extractContact(contactEntity);
		String error = saveToDatabaseNewContact(contact);
		return error;

	}

	private Contact extractContact(ContactEntity contactEntity) {
		Contact contact = new Contact();
		contact.setId(contactEntity.getId());
		contact.setFirstName(contactEntity.getFirstName());
		contact.setLastName(contactEntity.getLastName());
		contact.setPhone(contactEntity.getPhone());
		contact.setAddressID(contactEntity.getAddressID());
		contact.setEmail(contactEntity.getEmail());
		contact.setSexID(contactEntity.getGender());
		return contact;
	}

	public String saveUpdateToDatabase(AddressEntity addressEntity) {
		Address address = extractAddress(addressEntity);
		String error = saveToDatabaseUpdateAddress(address);
		return error;

	}

	public String saveUpdateToDatabase(ContactEntity contactEntity) {
		Contact contact = extractContact(contactEntity);
		String error = saveToDatabaseUpdateContact(contact);
		return error;
	}

	public List<Sex> fetchGenders() {
		List<Sex> genders = genderDAO.getSexes();
		return genders;
	}

	public AddressEntity prepareAddressEntity(long addressId) {
		Address address = addressDAO.getAddress(addressId);
		address.setCity(getCityObjectTree(address.getCityID()));

		AddressEntity addressEntity = new AddressEntity();
		addressEntity.setId(address.getId());
		addressEntity.setStreetName(address.getStreetName());
		addressEntity.setStreetNo(address.getStreetNo());
		addressEntity.setCityID(address.getCityID());
		return addressEntity;
	}

}
