package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class Address {

	private static long ID_CNT = 0;
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
	@NumberFormat
	@Size(min = streetNo_constraint_min, max = streetNo_constraint_max, message = "Number has to be between "
			+ streetNo_constraint_min
			+ " and "
			+ streetNo_constraint_max
			+ " characters long")
	private String streetNo;

	private long city_id;
	private City city;

	public Address() {
	}

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
		// this.city_id = city_id;
		// this.city = city;
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
	
	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

}
