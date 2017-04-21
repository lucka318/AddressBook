package hr.fer.croz.app.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class City represents entity City in database. City has g attributes:
 * <code>id</code>, <code>name</code>, <code>zipcode</code>,
 * <code>country_id</code>, <code>country</code>. <code>country_id</code> is ID
 * from entity Country in database. Constraints of the fields are made by using
 * Hibernate validators.
 * 
 * @author Lucija Megla
 *
 */
public class City {

	private final static int name_constraint_min = 2;
	private final static int name_constraint_max = 50;
	private final static int zipcode_constraint_min = 4;
	private final static int zipcode_constraint_max = 10;

	private long id;

	@NotEmpty(message = "Please provide city name")
	@Size(min = name_constraint_min, max = name_constraint_max, message = "Name has to be between "
			+ name_constraint_min + " and " + name_constraint_max + " characters long")
	private String name;

	@NotEmpty(message = "Please provide zipcode")
	@Size(min = zipcode_constraint_min, max = zipcode_constraint_max, message = "Zipcode size has to be between "
			+ zipcode_constraint_min + " and " + zipcode_constraint_max + " characters long")
	private String zipcode;

	private long countryID;
	private Country country;

	public City(String name, String zipcode) {
		super();
		this.name = name;
		this.zipcode = zipcode;
	}

	/**
	 * Empty constructor
	 */
	public City() {
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
	 * Setter for field id. Used when fetching City from database.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for field name.
	 * 
	 * @return String.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for field name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name.toLowerCase();
	}

	/**
	 * Getter for field zip code.
	 * 
	 * @return
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Setter for field zip code.
	 * 
	 * @param zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode.toLowerCase();
	}

	/**
	 * Getter for field country
	 * 
	 * @return Country
	 */
	public Country getCountry() {
		return country;
	}

	/**
	 * Setter for field country.
	 * 
	 * @param country
	 */
	public void setCountry(Country country) {
		this.country = country;
	}

	public long getCountryID() {
		return countryID;
	}

	public void setCountryID(long country_id) {
		this.countryID = country_id;
	}

}
