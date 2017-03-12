package hr.fer.croz.app.model;

public class City {

	private static long ID_CNT = 0;
	private final static int name_constraint = 50;
	private final static int zipcode_constraint = 10;

	private long id;
	private String name;
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
		this.country_id = country_id; // check not null, unique
		this.country = country; // check not null, unique
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

}
