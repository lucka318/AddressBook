package hr.fer.croz.app.model;

public class AddressBookEntity {

	private Contact contact;
	private String sex;
	private Address address;
	private City city;
	private Country country;

	public AddressBookEntity() {
		this.contact = new Contact();
		this.address = new Address();
		this.city = new City();
		this.country = new Country();
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
}
