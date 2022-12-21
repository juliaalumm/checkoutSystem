package coursework2;

import java.util.List;

public class Admin extends User {

	public Admin(String userID, String username, String name, int houseNumber, String postcode, String city) {
		super(userID, username, name, houseNumber, postcode, city, "admin");
		// TODO Auto-generated constructor stub
	}

	// get all products in my text file
	@Override
	public List<Product> getProducts() {
		List<Product> productList = null;
		productList = Database.ProductReadFromFile();
		String[] productType = new String[productList.size()];
		for (int i = 0; i < productList.size(); i++) {
			productType[i] = productList.get(i).getType();
		}

		return productList;
	}
}