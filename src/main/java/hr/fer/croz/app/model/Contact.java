package hr.fer.croz.app.model;

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
	private String firstName;
	@NotEmpty(message = "Please provide a last name.")
	private String lastName;
	@NotEmpty(message = "Please provide a phone number.")
	private String phone;
	@NotEmpty(message = "Please enter email addresss.")
	@Email
	private String email;
	@NotEmpty(message = "Please enter addresss.")
	private String address;
	@NotEmpty(message = "Please provide gender.")
	private String sex;

	// private long sexId;
	// private Sex sex;
	// private long addressId;
	// private Address address;

	public Contact() {
	}

	public Contact(String firstName, String lastName, String phone,
			String email, String address, String sex) {
		super();
		ID_CNT++;
		this.id = ID_CNT;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.sex = sex;
		// this.sexId = sexId;
		// this.sex = sex;
	}

	/*
	 * public Contact(String firstName, String lastName, String phone, String
	 * email, long sexId, Sex sex, long addressId, Address address) { super();
	 * ID_CNT++; this.id = ID_CNT; this.firstName = firstName; this.lastName =
	 * lastName; this.phone = phone; this.email = email; this.sexId = sexId;
	 * this.sex = sex; this.addressId = addressId; this.address = address; }
	 */

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public static long getID_CNT() {
		return ID_CNT;
	}

	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

	/*
	 * public long getSexId() { return sexId; }
	 * 
	 * public void setSexId(long sexId) { this.sexId = sexId; }
	 * 
	 * public Sex getSex() { return sex; }
	 * 
	 * public void setSex(Sex sex) { this.sex = sex; }
	 * 
	 * public long getAddressId() { return addressId; }
	 * 
	 * public void setAddressId(long addressId) { this.addressId = addressId; }
	 * 
	 * public Address getAddress() { return address; }
	 * 
	 * public void setAddress(Address address) { this.address = address; }
	 */

}
