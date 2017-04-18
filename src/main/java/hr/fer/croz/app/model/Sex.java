package hr.fer.croz.app.model;

/**
 * Class Sex represents genders of a <code>Contact</code>. It is a database
 * entity. It is a singleton class. It has a field <code>name</code> that just
 * temporary keeps a gender of current Contact we are working on. It has two
 * maps that map from String to Long and vice versa. Those two maps keep two
 * genders: male and female.
 * 
 * @author Lu
 *
 */
public class Sex {

	private long id;
	private String name;

	/**
	 * Constructor for object Sex. It initializes two maps and puts values in
	 * them.
	 */
	public Sex() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Get name of a gender.
	 * 
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name of a gender.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
