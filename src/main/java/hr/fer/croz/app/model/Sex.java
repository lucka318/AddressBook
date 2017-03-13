package hr.fer.croz.app.model;

import java.util.HashMap;
import java.util.Map;

public class Sex {

	private String name;
	private Map<Long, String> genders;

	private static Sex instance = null;

	protected Sex() {
		this.genders = new HashMap<Long, String>();
		genders.put((long) 1, "Male");
		genders.put((long) 2, "Female");
	}

	public static Sex getInstance() {
		if (instance == null) {
			instance = new Sex();
		}
		return instance;
	}

	public Map<Long, String> getGenders() {
		return genders;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
