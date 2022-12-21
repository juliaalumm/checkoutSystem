package coursework2;

import coursework2.Product;

public class Keyboard extends Product {
	private KeyboardLayout keyboardLayout;
	private KeyboardType keyboardType;

	public Keyboard(String barcode, String type, KeyboardType keyboardType, String brand, String colour,
			Connectivity connectivity, int quantity, float originalCost, float retailCost,
			KeyboardLayout keyboardLayout) {
		super(barcode, "keyboard", brand, colour, connectivity, quantity, originalCost, retailCost);
		this.keyboardLayout = keyboardLayout;
		this.keyboardType = keyboardType;
	}

	public KeyboardLayout getKeyboardLayout() {
		return keyboardLayout;
	}

	public KeyboardType getKeyboardType() {
		return keyboardType;
	}

}
