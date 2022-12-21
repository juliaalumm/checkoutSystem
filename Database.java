package coursework2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

	// reads from the user textfile
	public static List<User> UserReadFromFile() throws FileNotFoundException {
		File inputFile = new File("UserAccounts.txt");
		Scanner fileScanner = new Scanner(inputFile);

		List<User> userList = new ArrayList<User>();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] res = line.split(", ", 0);
			for (String myStr : res) {
				System.out.println(myStr);
			}
			if (res[6].equals("admin")) {
				Admin newUser = new Admin(res[0], res[1], res[2], Integer.parseInt(res[3]), res[4], res[5]);
				userList.add(newUser);
			} else {
				Customer newUser = new Customer(res[0], res[1], res[2], Integer.parseInt(res[3]), res[4], res[5]);
				userList.add(newUser);
			}

		}
		return userList;
	}

	// reads from the product textfile

	public static List<Product> ProductReadFromFile() {
		File inputFile = new File("Stock.txt");
		Scanner fileScanner;
		try {
			fileScanner = new Scanner(inputFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		List<Product> productList = new ArrayList<Product>();
		while (fileScanner.hasNextLine()) {
			String line = fileScanner.nextLine();
			String[] res = line.split(", ", 0);
			for (String myStr : res) {
				System.out.println(myStr);
			}
			if (res[1].equals("mouse")) {
				Mouse newProduct = new Mouse(res[0], "mouse", MouseType.valueOf(res[2].toUpperCase()), res[3], res[4],
						Connectivity.valueOf(res[5].toUpperCase()), Integer.parseInt(res[6]), Float.parseFloat(res[7]),
						Float.parseFloat(res[8]), Float.parseFloat(res[9]));
				productList.add(newProduct);
			} else {
				Keyboard newProduct = new Keyboard(res[0], "keyboard", KeyboardType.valueOf(res[2].toUpperCase()),
						res[3], res[4], Connectivity.valueOf(res[5].toUpperCase()), Integer.parseInt(res[6]),
						Float.parseFloat(res[7]), Float.parseFloat(res[8]),
						KeyboardLayout.valueOf(res[9].toUpperCase()));
				productList.add(newProduct);
			}
		}
		return productList;
	}

}
