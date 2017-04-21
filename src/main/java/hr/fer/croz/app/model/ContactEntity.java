package hr.fer.croz.app.model;

public class ContactEntity {

	private String firstName;
	private String lastName;
	private String phone;
	private String email;
	private long genderID;
	private long addressID;

	public ContactEntity() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getGenderID() {
		return genderID;
	}

	public void setGenderID(long genderID) {
		this.genderID = genderID;
	}

	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}
}
