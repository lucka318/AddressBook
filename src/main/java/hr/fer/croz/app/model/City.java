package hr.fer.croz.app.model;

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

	private long id;
	private String name;
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
		this.name = name;
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
		this.zipcode = zipcode;
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
