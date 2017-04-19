package hr.fer.croz.app.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import hr.fer.croz.app.dao.AddressDAO;
import hr.fer.croz.app.dao.CityDAO;
import hr.fer.croz.app.dao.ContactDAO;
import hr.fer.croz.app.dao.CountryDAO;
import hr.fer.croz.app.dao.GenderDAO;
import hr.fer.croz.app.model.Address;
import hr.fer.croz.app.model.AddressBookEntity;
import hr.fer.croz.app.model.City;
import hr.fer.croz.app.model.Contact;
import hr.fer.croz.app.model.Country;

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
		return contactDAO.getContacts();
	}

	public List<City> fetchCities() {
		return cityDAO.getCities();
	}

	public List<Country> fetchCountries() {
		return countryDAO.getCountries();
	}

	public void saveNewToDatabase(AddressBookEntity addressBookEntity) {
		Country country = addressBookEntity.getCountry();
		saveToDatabaseNewCountry(country);
		City city = addressBookEntity.getCity();
		saveToDatabaseNewCity(city);
		Address address = addressBookEntity.getAddress();
		saveToDatabaseNewAddress(address);
		Contact contact = addressBookEntity.getContact();
		saveToDatabaseNewContact(contact);

	}

	public AddressBookEntity prepareAddressBookEntity(long contactId) {
		Contact contact = contactDAO.getContact(contactId);
		Address address = addressDAO.getAddress(contact.getAddress());
		City city = cityDAO.getCity(address.getId());
		Country country = countryDAO.getCountry(city.getId());
		String sex = genderDAO.getSex(contact.getSex());
		AddressBookEntity abe = new AddressBookEntity();
		abe.setContact(contact);
		abe.setAddress(address);
		abe.setCity(city);
		abe.setCountry(country);
		abe.setSex(sex);
		return abe;
	}

	public void saveUpdateToDatabase(AddressBookEntity addressBookEntity) {

		Country country = addressBookEntity.getCountry();
		saveToDatabaseUpdateCountry(country);

		City city = addressBookEntity.getCity();
		saveToDatabaseUpdateCity(city);

		Address address = addressBookEntity.getAddress();
		saveToDatabaseUpdateAddress(address);

		Contact contact = addressBookEntity.getContact();
		saveToDatabaseUpdateContact(contact);
	}

	private void saveToDatabaseNewContact(Contact contact) {
		if (contact != null) {
			boolean check = contactDAO.contactExists(contact);
			if (!check) {
				contact = contactDAO.createTuple(contact);
			}
		}
	}

	private void saveToDatabaseNewCountry(Country country) {
		if (country != null) {
			boolean check = countryDAO.countryExists(country);
			if (!check) {
				country = countryDAO.createTuple(country);
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

	private void saveToDatabaseNewCity(City city) {
		if (city != null) {
			boolean check = cityDAO.cityExists(city);
			if (!check) {
				city = cityDAO.createTuple(city);
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

	private void saveToDatabaseUpdateCity(City city) {
		if (city != null) {
			boolean check = cityDAO.cityExists(city);
			if (!check) {
				city = cityDAO.updateTuple(city);
			}
		}
	}

	private void saveToDatabaseUpdateCountry(Country country) {
		if (country != null) {
			boolean check = countryDAO.countryExists(country);
			if (!check) {
				country = countryDAO.updateTuple(country);
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

	public List<Address> fetchAddresses() {
		return addressDAO.getAddresses();
	}

	public void saveNewAddressToDatabase(AddressBookEntity addressBookEntity) {

		Address address = addressBookEntity.getAddress();
		saveToDatabaseNewAddress(address);

	}

}
