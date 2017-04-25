package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ContactEntity {

	private final static int firstName_constraint = 20;
	private final static int lastName_constraint = 50;
	private final static int phone_constraint = 13;
	private final static int email_constraint = 50;

	private long id;

	@NotEmpty(message = "Please provide a first name.")
	@Size(max = firstName_constraint, message = "Name cannot be longer than " + firstName_constraint + " characters.")
	private String firstName;

	@NotEmpty(message = "Please provide a last name.")
	@Size(max = lastName_constraint, message = "Name cannot be longer than " + lastName_constraint + " characters.")
	private String lastName;

	@NotEmpty(message = "Please provide a phone number.")
	@Size(max = phone_constraint, message = "Phone cannot be longer than " + phone_constraint + " characters.")
	@Pattern(regexp = "^[0-9, ]+$", message = "Not a valid phone number.")
	private String phone;

	@NotEmpty(message = "Please enter email addresss.")
	@Size(max = email_constraint, message = "Phone cannot be longer than " + email_constraint + " characters.")
	@Email
	private String email;
	private long gender;
	private long addressID;

	public ContactEntity() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public long getGender() {
		return gender;
	}

	public void setGender(long gender) {
		this.gender = gender;
	}

	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long addressID) {
		this.addressID = addressID;
	}
}
