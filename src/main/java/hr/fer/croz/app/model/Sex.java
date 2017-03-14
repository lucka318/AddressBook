package hr.fer.croz.app.model;

import java.util.HashMap;
import java.util.Map;

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

	private String name;
	private Map<String, Long> genders;
	private Map<Long, String> ids;

	private static Sex instance = null;

	/**
	 * Constructor for object Sex. It initializes two maps and puts values in
	 * them.
	 */
	private Sex() {
		this.genders = new HashMap<String, Long>();
		this.ids = new HashMap<Long, String>();
		genders.put("Male", (long) 1);
		genders.put("Female", (long) 2);
		ids.put((long) 1, "Male");
		ids.put((long) 2, "Female");
	}

	/**
	 * Method to fetch an instance of class Sex.
	 * 
	 * @return Sex
	 */
	public static Sex getInstance() {
		if (instance == null) {
			instance = new Sex();
		}
		return instance;
	}

	/**
	 * Get gender ID given a gender name.
	 * 
	 * @param name
	 * @return Long
	 */
	public Long getGenderID(String name) {
		return genders.get(name);
	}

	/**
	 * Get gender name given a gender id.
	 * 
	 * @param id
	 * @return String
	 */
	public String getGenderName(long id) {
		return ids.get(id);
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
