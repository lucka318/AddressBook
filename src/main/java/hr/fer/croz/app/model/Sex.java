package hr.fer.croz.app.model;

import java.util.HashMap;
import java.util.Map;

public class Sex {

	private String name;
	private Map<String, Long> genders;
	private Map<Long,String> ids;

	private static Sex instance = null;

	protected Sex() {
		this.genders = new HashMap<String, Long>();
		this.ids = new HashMap<Long, String>();
		genders.put("Male", (long) 1);
		genders.put("Female", (long) 2);
		ids.put((long) 1, "Male");
		ids.put((long) 2, "Female");
	}

	public static Sex getInstance() {
		if (instance == null) {
			instance = new Sex();
		}
		return instance;
	}

	public Long getGenderID(String name) {
		return genders.get(name);
	}
	
	public String getGenderName(long id) {
		return ids.get(id);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
