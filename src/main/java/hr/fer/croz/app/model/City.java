package hr.fer.croz.app.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class City {

	private static long ID_CNT = 0;
	private final static int name_constraint_min = 2;
	private final static int name_constraint_max = 50;
	private final static int zipcode_constraint_min = 4;
	private final static int zipcode_constraint_max = 10;

	private long id;
	@NotEmpty(message = "Please provide city name")
	@Size(min = name_constraint_min, max = name_constraint_max, message = "Name has to be between "
			+ name_constraint_min
			+ " and "
			+ name_constraint_max
			+ " characters long")
	private String name;
	@NotEmpty(message = "Please provide zipcode")
	@Size(min = zipcode_constraint_min, max = zipcode_constraint_max, message = "Zipcode size has to be between "
			+ zipcode_constraint_min
			+ " and "
			+ zipcode_constraint_max
			+ " characters long")
	private String zipcode;

	private long country_id;
	private Country country;

	public City(String name, String zipcode) {
		super();
		ID_CNT++; // check overflow
		this.id = ID_CNT;
		this.name = name;
		this.zipcode = zipcode;
	}

	public City(String name, String zipcode, long country_id, Country country) {
		super();
		ID_CNT++; // check overflow
		this.id = ID_CNT;
		this.name = name; // check not null, unique
		this.zipcode = zipcode; // check not null, unique
		// this.country_id = country_id; // check not null, unique
		// this.country = country; // check not null, unique
	}

	public City() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public void setId() {
		ID_CNT++;
		this.id = ID_CNT;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public long getCountry_id() {
		return country_id;
	}

	public void setCountry_id(long country_id) {
		this.country_id = country_id;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

}
