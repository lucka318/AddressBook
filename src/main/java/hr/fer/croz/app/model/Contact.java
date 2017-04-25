package hr.fer.croz.app.model;

/**
 * Class Contact represents entity Contact in database. Contact has 9
 * attributes: <code>id</code>, <code>firstName</code>, <code>lastName</code>,
 * <code>phone</code>, <code>email</code>, <code>address_id</code>,
 * <code>address</code>, <code>sex_id</code>, <code>sex</code>.
 * <code>address_id</code> and <code>sex_id</code> are ID's from entities
 * Address and Sex in database. Constraints of the fields are made by using
 * Hibernate validators.
 * 
 * @author Lucija Megla
 *
 */
public class Contact {

	private long id;

	private String firstName;
	private String lastName;
	private String phone;
	private String email;

	private long addressID;
	private Address address;
	private long sexID;
	private Sex sex;

	/**
	 * Empty constructor
	 */
	public Contact() {
	}

	/**
	 * Getter for field id
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for field id. Used when fetching Contact from database.
	 * 
	 * @param id
	 *            - long
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for field first name.
	 * 
	 * @return String
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for first name
	 * 
	 * @param firstName
	 *            String
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for last name.
	 * 
	 * @return String
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for last name.
	 * 
	 * @param lastName
	 *            String
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for phone number.
	 * 
	 * @return String
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Setter for phone number.
	 * 
	 * @param phone
	 *            String
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Getter for field email.
	 * 
	 * @return String
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for field email.
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for field address
	 * 
	 * @return Address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * Setter for field address
	 * 
	 * @param address
	 *            Address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * Getter for field sex.
	 * 
	 * @return String
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * Setter for field sex.
	 * 
	 * @param sex
	 */
	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public long getAddressID() {
		return addressID;
	}

	public void setAddressID(long address_id) {
		this.addressID = address_id;
	}

	public long getSexID() {
		return sexID;
	}

	public void setSexID(long sex_id) {
		this.sexID = sex_id;
	}

}
