package hr.fer.croz.app.model;

public class Sex {

	private static long ID_CNT = 0;
	private final static int name_constraint = 7;

	private long id;
	private String name;

	public Sex(String name) {
		super();
		ID_CNT++;
		this.id = ID_CNT;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
