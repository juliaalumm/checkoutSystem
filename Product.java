package coursework2;

public abstract class Product {
	private final String barcode;
	private final String type;
	private final String brand;
	private final String colour;
	private final Connectivity connectivity;
	private int quantity;
	private final float originalCost;
	private final float retailCost;

	public Product(String barcode, String type, String brand, String colour, Connectivity connectivity, int quantity,
			float originalCost, float retailCost) {
		super();
		this.barcode = barcode;
		this.type = type;
		this.brand = brand;
		this.colour = colour;
		this.connectivity = connectivity;
		this.quantity = quantity;
		this.originalCost = originalCost;
		this.retailCost = retailCost;
	}

	public String getBarcode() {
		return barcode;
	}

	public String getBrand() {
		return brand;
	}

	public String getColour() {
		return colour;
	}

	public Connectivity getConnectivity() {
		return connectivity;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getOriginalCost() {
		return originalCost;
	}

	public float getRetailCost() {
		return retailCost;
	}

	public String getType() {
		return type;
	}

	// sets the new quantity

	void setQuantity(int newQuantity) {
		this.quantity = newQuantity;
	}

}
