package hr.fer.croz.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.NumberFormat;

public class AddressEntity {

	private final static int streetName_constraint_min = 3;
	private final static int streetName_constraint_max = 50;
	private final static int streetNo_constraint_min = 1;
	private final static int streetNo_constraint_max = 5;

	private long id;

	@NotEmpty
	@Pattern(regexp = "^[A-Za-z]*$")
	@Size(min = streetName_constraint_min, max = streetName_constraint_max, message = "Name has to be between "
			+ streetName_constraint_min + " and " + streetName_constraint_max + " characters long")
	private String streetName;

	@NotEmpty
	@Size(min = streetNo_constraint_min, max = streetNo_constraint_max, message = "Number has to be between "
			+ streetNo_constraint_min + " and " + streetNo_constraint_max + " characters long")
	@NumberFormat
	private String streetNo;
	private long cityID;

	public AddressEntity() {
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

	public long getCityID() {
		return cityID;
	}

	public void setCityID(long cityID) {
		this.cityID = cityID;
	}
}
