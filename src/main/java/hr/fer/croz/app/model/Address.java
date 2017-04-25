package hr.fer.croz.app.model;

/**
 * Class Address represents entity Address in database. City has 5 attributes:
 * <code>id</code>, <code>street</code>, <code>street_no</code>,
 * <code>city_id</code>, <code>city</code>. <code>city_id</code> is ID from
 * entity City in database. Constraints of the fields are made by using
 * Hibernate validators.
 * 
 * @author Lucija Megla
 *
 */
public class Address {

	private long id;
	private String streetName;
	private String streetNo;

	private long cityID;
	private City city;

	/**
	 * Empty constructor.
	 */
	public Address() {
	}

	/**
	 * Getter for field id.
	 * 
	 * @return long
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for field id.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter for street name.
	 * 
	 * @return String
	 */
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Setter for field street name.
	 * 
	 * @param streetName
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName.toLowerCase();
	}

	/**
	 * Getter for field street number.
	 * 
	 * @return String
	 */
	public String getStreetNo() {
		return streetNo;
	}

	/**
	 * Setter for field string number.
	 * 
	 * @param streetNo
	 */
	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	/**
	 * Getter for City
	 * 
	 * @return City.
	 */
	public City getCity() {
		return city;
	}

	/**
	 * Setter for field city.
	 * 
	 * @param city
	 */
	public void setCity(City city) {
		this.city = city;
	}

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long city_id) {
		this.cityID = city_id;
	}
}
