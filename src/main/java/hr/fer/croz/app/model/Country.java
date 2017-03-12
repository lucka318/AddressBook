package hr.fer.croz.app.model;

public class Country {

	private static long ID_CNT = 0;
	private final static int name_constraint = 50;
	private final static int alpha_2_constraint = 2;
	private final static int alpha_3_constraint = 3;

	private long id;
	private String name;
	private String alpha_2;
	private String aplha_3;

	public Country(String name, String alpha_2, String aplha_3) {
		super();
		ID_CNT++;
		this.id = ID_CNT; // check overflow
		this.name = name; // check not null, unique
		this.alpha_2 = alpha_2; // check not null, unique
		this.aplha_3 = aplha_3; // check not null, unique
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

	public String getAlpha_2() {
		return alpha_2;
	}

	public void setAlpha_2(String alpha_2) {
		this.alpha_2 = alpha_2;
	}

	public String getAplha_3() {
		return aplha_3;
	}

	public void setAplha_3(String aplha_3) {
		this.aplha_3 = aplha_3;
	}

}
