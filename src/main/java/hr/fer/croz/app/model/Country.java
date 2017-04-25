package hr.fer.croz.app.model;

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

	private long id;
	private String name;
	private String alpha_2;
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
		this.name = name;
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
		this.alpha_2 = alpha_2;
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
	public void setAlpha_3(String alpha_3) {
		this.alpha_3 = alpha_3;
	}

}
