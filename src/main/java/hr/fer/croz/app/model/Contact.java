package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class Contact {

	private static long ID_CNT = 0;
	private final static int firstName_constraint = 20;
	private final static int lastName_constraint = 50;
	private final static int phone_constraint = 13;
	private final static int email_constraint = 50;

	private long id;

	@NotEmpty(message = "Please provide a first name.")
	@Size(max = firstName_constraint, message = "Name cannot be longer than "
			+ firstName_constraint + " characters.")
	private String firstName;

	@NotEmpty(message = "Please provide a last name.")
	@Size(max = lastName_constraint, message = "Name cannot be longer than "
			+ lastName_constraint + " characters.")
	private String lastName;

	@NotEmpty(message = "Please provide a phone number.")
	@Size(max = phone_constraint, message = "Phone cannot be longer than "
			+ phone_constraint + " characters.")
	@Pattern(regexp = " ^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message = "Not a valid phone number.")
	private String phone;

	@NotEmpty(message = "Please enter email addresss.")
	@Size(max = email_constraint, message = "Phone cannot be longer than "
			+ email_constraint + " characters.")
	@Email
	private String email;

	private long address_id;
	private Address address;
	private long sex_id;
	private String sex;

	public Contact() {
	}

	public Contact(String firstName, String lastName, String phone,
			String email, Address address, long sex_id) {
		super();
		ID_CNT++;
		this.id = ID_CNT;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.sex_id = sex_id;
		this.address_id = address.getId();
	}

	public long getId() {
		return id;
	}

	public void setId() {
		ID_CNT++;
		this.id = ID_CNT;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName.toLowerCase();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName.toLowerCase();
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
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static long getID_CNT() {
		return ID_CNT;
	}

	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

	public long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	public long getSex_id() {
		return sex_id;
	}

	public void setSex_id(long sex_id) {
		this.sex_id = sex_id;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
