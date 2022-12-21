package coursework2;

import java.io.FileNotFoundException;
import java.util.List;

public abstract class User {
	private final String userID;
	private final String username;
	private final String name;
	private final int houseNumber;
	private final String postcode;
	private final String city;
	private final String type;

	public User(String userID, String username, String name, int houseNumber, String postcode, String city,
			String type) {
		super();
		this.userID = userID;
		this.username = username;
		this.name = name;
		this.houseNumber = houseNumber;
		this.postcode = postcode;
		this.city = city;
		this.type = type;
	}

	public String getUserID() {
		return userID;
	}

	public String getUsername() {
		return username;
	}

	public String getName() {
		return name;
	}

	public int getHouseNumber() {
		return houseNumber;
	}

	public String getPostcode() {
		return postcode;
	}

	public String getCity() {
		return city;
	}

	public String getType() {
		return type;
	}

	public String getAddress() {
		return houseNumber + ", " + postcode + ", " + city;
	}

	public abstract List<Product> getProducts() throws FileNotFoundException;

}
