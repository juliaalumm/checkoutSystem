package coursework2;

import java.util.Comparator;

//this is to sort it into ascending order from retail cost
public class PriceComparator implements Comparator<Product> {
	@Override
	public int compare(Product prod1, Product prod2) {
		if (prod1.getRetailCost() < prod2.getRetailCost())
			return -1;
		if (prod1.getRetailCost() > prod2.getRetailCost())
			return 1;
		else
			return 0;
	}

}
