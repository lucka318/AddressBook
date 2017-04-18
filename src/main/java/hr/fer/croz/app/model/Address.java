package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

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

	private final static int streetName_constraint_min = 3;
	private final static int streetName_constraint_max = 50;
	private final static int streetNo_constraint_min = 1;
	private final static int streetNo_constraint_max = 5;

	private long id;

	@NotEmpty
	@Pattern(regexp = "^[A-Za-z]*$")
	@Size(min = streetName_constraint_min, max = streetName_constraint_max, message = "Name has to be between "
			+ streetName_constraint_min
			+ " and "
			+ streetName_constraint_max
			+ " characters long")
	private String streetName;

	@NotEmpty
	@Size(min = streetNo_constraint_min, max = streetNo_constraint_max, message = "Number has to be between "
			+ streetNo_constraint_min
			+ " and "
			+ streetNo_constraint_max
			+ " characters long")
	@NumberFormat
	private String streetNo;

	//private long city_id;
	private long city;

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
	public long getCity() {
		return city;
	}

	/**
	 * Setter for field city.
	 * 
	 * @param city
	 */
	public void setCity(long city) {
		this.city = city;
	}
}
