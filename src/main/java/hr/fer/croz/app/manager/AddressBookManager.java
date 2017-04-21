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

	private void saveToDatabaseNewContact(Contact contact) {
		if (contact != null) {
			boolean check = contactDAO.contactExists(contact);
			if (!check) {
				contact = contactDAO.createTuple(contact);
			}
		}
	}

	private void saveToDatabaseNewAddress(Address address) {
		if (address != null) {
			boolean check = addressDAO.addressExists(address);
			if (!check) {
				address = addressDAO.createTuple(address);
			}
		}
	}

	private void saveToDatabaseUpdateContact(Contact contact) {
		if (contact != null) {
			boolean check = contactDAO.contactExists(contact);
			if (!check) {
				contact = contactDAO.updateTuple(contact);
			}
		}
	}

	private void saveToDatabaseUpdateAddress(Address address) {
		if (address != null) {
			boolean check = addressDAO.addressExists(address);
			if (!check) {
				address = addressDAO.updateTuple(address);
			}
		}
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
			return "There exists at least one contact that has " + address.getStreetName() + " "
					+ address.getStreetName() + "as an address";
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
		contactEntity.setGenderID(contact.getSexID());
		contactEntity.setAddressID(contact.getAddressID());
		return contactEntity;
	}

	public void saveNewToDatabase(AddressEntity addressEntity) {

		Address address = extractAddress(addressEntity);

		saveToDatabaseNewAddress(address);

	}

	private Address extractAddress(AddressEntity addressEntity) {
		Address address = new Address();
		address.setStreetName(addressEntity.getStreetName());
		address.setStreetNo(addressEntity.getStreetNo());
		address.setCityID(addressEntity.getCityID());
		return address;
	}

	public void saveNewToDatabase(ContactEntity contactEntity) {
		Contact contact = extractContact(contactEntity);
		saveToDatabaseNewContact(contact);

	}

	private Contact extractContact(ContactEntity contactEntity) {
		Contact contact = new Contact();
		contact.setFirstName(contactEntity.getFirstName());
		contact.setLastName(contactEntity.getLastName());
		contact.setEmail(contactEntity.getEmail());
		contact.setAddressID(contactEntity.getAddressID());
		contact.setEmail(contactEntity.getEmail());
		contact.setSexID(contactEntity.getGenderID());
		return contact;
	}

	public void saveUpdateToDatabase(AddressEntity addressEntity) {
		Address address = extractAddress(addressEntity);
		saveToDatabaseUpdateAddress(address);

	}

	public void saveUpdateToDatabase(ContactEntity contactEntity) {
		Contact contact = extractContact(contactEntity);
		saveToDatabaseUpdateContact(contact);
	}

}
