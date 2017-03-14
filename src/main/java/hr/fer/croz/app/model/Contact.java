package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

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
	@Pattern(regexp = "^[0-9, ]+$", message = "Not a valid phone number.")
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
	 * Setter for field id Used for setting database id when putting Contact
	 * into a database.
	 */
	public void setId() {
		ID_CNT++;
		this.id = ID_CNT;
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
		this.firstName = firstName.toLowerCase();
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
		this.lastName = lastName.toLowerCase();
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
	 * Get current database ID.
	 * 
	 * @return long
	 */
	public static long getID_CNT() {
		return ID_CNT;
	}

	/**
	 * Set current database ID. Used when starting the application
	 * 
	 * @param iD_CNT
	 */
	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

	/**
	 * Getter for field address
	 * 
	 * @return long
	 */
	public long getAddress_id() {
		return address_id;
	}

	/**
	 * Setter for field address_id.
	 * 
	 * @param address_id
	 */
	public void setAddress_id(long address_id) {
		this.address_id = address_id;
	}

	/**
	 * Getter for field sex_id
	 * 
	 * @return long
	 */
	public long getSex_id() {
		return sex_id;
	}

	/**
	 * Setter for field sex_id
	 * 
	 * @param sex_id
	 */
	public void setSex_id(long sex_id) {
		this.sex_id = sex_id;
	}

	/**
	 * Getter for field sex.
	 * 
	 * @return String
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * Setter for field sex.
	 * 
	 * @param sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

}
