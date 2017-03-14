package hr.fer.croz.app.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Class Country represents entity Country in database. City has attributes:
 * <code>id</code>, <code>name</code>, <code>alpha_2</code>,
 * <code>alpha_3</code>. Constraints of the fields are made by using Hibernate
 * validators.
 * 
 * @author Lucija Megla
 *
 */
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

	/**
	 * Empty constructor.
	 */
	public Country() {
	}

	/**
	 * Getter for field id.
	 * 
	 * @return
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter for field id. Used when fetching Country form database.
	 * 
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Setter for field id. Used when saving Country to database.
	 */
	public void setId() {
		ID_CNT++;
		this.id = ID_CNT;
	}

	/**
	 * Getter for country's name
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for country's name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name.toLowerCase();
	}

	/**
	 * Getter for country's alpha_2 code.
	 * 
	 * @return
	 */
	public String getAlpha_2() {
		return alpha_2;
	}

	/**
	 * Setter for country's alpha_2 code.
	 * 
	 * @param alpha_2
	 */
	public void setAlpha_2(String alpha_2) {
		this.alpha_2 = alpha_2.toLowerCase();
	}

	/**
	 * Getter for country's alpha_3 code-
	 * 
	 * @return String
	 */
	public String getAlpha_3() {
		return alpha_3;
	}

	/**
	 * Setter for alpha_3 code.
	 * 
	 * @param aplha_3
	 */
	public void setAlpha_3(String aplha_3) {
		this.alpha_3 = aplha_3.toLowerCase();
	}

	/**
	 * Setter for ID_CNT that keeps track of last id in database.
	 * 
	 * @param iD_CNT
	 */
	public static void setID_CNT(long iD_CNT) {
		ID_CNT = iD_CNT;
	}

}
