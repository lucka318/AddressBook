package hr.fer.croz.app.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class Country {

	private static long ID_CNT = 0;
	private final static int name_constraint = 50;
	private final static int alpha_2_constraint = 2;
	private final static int alpha_3_constraint = 3;

	private long id;
	@NotEmpty(message = "Please provide a country name.")
	@Size(max = name_constraint, message = "Size should be max 50 characters long.")
	private String name;
	@NotEmpty(message = "Please provide alpha_2 country code")
	@Size(min = alpha_2_constraint, max = alpha_2_constraint, message = "Size should be 2 characters.")
	private String alpha_2;
	@NotEmpty(message = "Please provide alpha_3 country code")
	@Size(min = alpha_3_constraint, max = alpha_3_constraint, message = "Size should be 3 characters.")
	private String alpha_3;

	public Country(String name, String alpha_2, String aplha_3) {
		super();
		ID_CNT++;
		this.id = ID_CNT; // check overflow
		this.name = name; // check not null, unique
		this.alpha_2 = alpha_2; // check not null, unique
		this.alpha_3 = aplha_3; // check not null, unique
	}

	public Country() {
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
		return name.toLowerCase();
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlpha_2() {
		return alpha_2.toLowerCase();
	}

	public void setAlpha_2(String alpha_2) {
		this.alpha_2 = alpha_2;
	}

	public String getAlpha_3() {
		return alpha_3.toLowerCase();
	}

	public void setAlpha_3(String aplha_3) {
		this.alpha_3 = aplha_3;
	}

	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

}
