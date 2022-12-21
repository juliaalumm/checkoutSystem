package coursework2;

import coursework2.Product;

public class Mouse extends Product {
	private MouseType mouseType;
	private float numButtons;

	public Mouse(String barcode, String type, MouseType mouseType, String brand, String colour,
			Connectivity connectivity, int quantity, float originalCost, float retailCost, float numButtons) {
		super(barcode, "mouse", brand, colour, connectivity, quantity, originalCost, retailCost);
		this.mouseType = mouseType;
		this.numButtons = numButtons;
	}

	public MouseType getMouseType() {
		return mouseType;
	}

	public float getNumButtons() {
		return numButtons;
	}

}