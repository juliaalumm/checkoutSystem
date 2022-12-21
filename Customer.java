package coursework2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

	ArrayList<Product> basket;

	public void addToBasket(Product prod) {
		basket.add(prod);
	}

	public void clearBasket() {
		basket.clear();
	}

	public List<Product> getBasket() {
		return basket;
	}

	public Customer(String userID, String username, String name, int houseNumber, String postcode, String city) {
		super(userID, username, name, houseNumber, postcode, city, "customer");
		this.basket = new ArrayList<Product>();
		// TODO Auto-generated constructor stub
	}

	// get the products from the textfile
	public List<Product> getProducts() {
		List<Product> products = Database.ProductReadFromFile();

		for (Product prod : products) {
			if (prod.getQuantity() == 0) {
				products.remove(prod);
			}
		}
		return products;
	}

	// gets the products after its been added to the shopping basket
	public List<Product> getStock() throws FileNotFoundException {

		List<Product> productList = Database.ProductReadFromFile();

		for (Product prod : productList) {
			for (Product basketProd : basket) {
				if (prod.getBarcode().equals(basketProd.getBarcode())) {
					prod.setQuantity(prod.getQuantity() - basketProd.getQuantity());
				}
			}
		}

		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getQuantity() == 0) {
				productList.remove(i);
				i = 0;
			}
		}
		return productList;

	}

	// gets barcode in the textfile
	public Product getProductBarcode(String barcode) {
		List<Product> productList = getProducts();
		for (int i = 0; i < productList.size(); i++) {
			if (productList.get(i).getBarcode().equals(barcode)) {
				return productList.get(i);
			}
		}

		return null;

	}

}
