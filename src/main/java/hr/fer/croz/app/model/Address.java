package hr.fer.croz.app.model;

public class Address {

	private static long ID_CNT = 0;
	private final static int streetName_constraint = 50;
	private final static int streetNo_constraint = 5;

	private long id;
	private String streetName;
	private String streetNo;
	private long city_id;
	private City city;

	public Address(String streetName, String streetNo) {
		super();
		ID_CNT++;
		this.id = ID_CNT;
		this.streetName = streetName;
		this.streetNo = streetNo;
	}

	public Address(String streetName, String streetNo, long city_id, City city) {
		super();
		ID_CNT++;
		this.id = ID_CNT;
		this.streetName = streetName;
		this.streetNo = streetNo;
		this.city_id = city_id;
		this.city = city;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getStreetNo() {
		return streetNo;
	}

	public void setStreetNo(String streetNo) {
		this.streetNo = streetNo;
	}

	public long getCity_id() {
		return city_id;
	}

	public void setCity_id(long city_id) {
		this.city_id = city_id;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

}
