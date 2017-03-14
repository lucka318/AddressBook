package hr.fer.croz.app.model;

import java.util.HashMap;
import java.util.Map;

public class Sex {

	private String name;
	private Map<String, Long> genders;

	private static Sex instance = null;

	protected Sex() {
		this.genders = new HashMap<String, Long>();
		genders.put("Male", (long) 1);
		genders.put("Female", (long) 2);
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
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
