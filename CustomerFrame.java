package coursework2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class CustomerFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tbProducts;
	private JTable tbShopping;
	private DefaultTableModel dtmProductsC;
	private DefaultTableModel dtmShopping;
	private Customer selectedUser;
	private float totalSum;
	private String newStock;

	/**
	 * Create the frame.
	 */
	public CustomerFrame(Customer selectedUser) {
		this.selectedUser = selectedUser;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 887, 595);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("COMPUTER ACCESSORIES SHOP");
		lblNewLabel.setForeground(new Color(204, 51, 102));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(16, 17, 273, 42);
		contentPane.add(lblNewLabel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 71, 875, 496);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("View Products", null, panel, null);
		panel.setLayout(null);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 27, 666, 393);
		panel.add(scrollPane_1);

		tbProducts = new JTable();
		scrollPane_1.setViewportView(tbProducts);

		tbProducts.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// creates and fills the table with the text from the textfile
		dtmProductsC = new DefaultTableModel();
		dtmProductsC.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Type", "Brand", "Colour", "Connectivity",
				"Quantitity in Stock", "Original Price", "Retail Price", "Additional Info" });
		tbProducts.setModel(dtmProductsC);

		List<Product> shoppingBasket = new ArrayList<Product>();

		JButton btnNewButton = new JButton("Add To Basket");
		totalSum = 0;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// allows you to enter amount you want for each selected object
				int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the Quantity"));

				TableModel viewProducts = tbProducts.getModel();
				int[] indexs = tbProducts.getSelectedRows();
				Object[] row = new Object[9];
				DefaultTableModel dtmShopping = (DefaultTableModel) tbShopping.getModel();

				// adds the selected row to the shopping basket
				for (int i = 0; i < indexs.length; i++) {
					String quantitySelected = (String) viewProducts.getValueAt(indexs[i], 6);
					if (quantity > Integer.parseInt(quantitySelected)) {
						JOptionPane.showMessageDialog(null, "Input is more than quantity in stock!", "Error!",
								JOptionPane.ERROR_MESSAGE);
					} else {

						row[0] = viewProducts.getValueAt(indexs[i], 0);
						row[1] = viewProducts.getValueAt(indexs[i], 1);
						row[2] = viewProducts.getValueAt(indexs[i], 2);
						row[3] = viewProducts.getValueAt(indexs[i], 3);
						row[4] = viewProducts.getValueAt(indexs[i], 4);
						row[5] = viewProducts.getValueAt(indexs[i], 5);
						row[6] = quantity;
						row[7] = viewProducts.getValueAt(indexs[i], 8);
						row[8] = viewProducts.getValueAt(indexs[i], 9);
						dtmShopping.addRow(row);

						// calculates the total price
						int quantityNum = (int) quantity;
						String retailPrice = (String) viewProducts.getValueAt(indexs[i], 8);

						totalSum = (quantity * Float.parseFloat(retailPrice)) + totalSum;

						if (row[1].equals("mouse")) {
							Mouse prod = (Mouse) selectedUser.getProductBarcode((String) row[0]);
							prod.setQuantity(quantity);
							selectedUser.addToBasket(prod);

						} else {
							Keyboard prod = (Keyboard) selectedUser.getProductBarcode((String) row[0]);
							prod.setQuantity(quantity);
							selectedUser.addToBasket(prod);
						}

						try {
							dtmProductsC.getDataVector().removeAllElements();
							dtmProductsC.fireTableDataChanged();

							fillTableCustomer(dtmProductsC);
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

			}
		});
		btnNewButton.setBounds(711, 27, 117, 29);
		panel.add(btnNewButton);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Shopping Cart", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 23, 805, 348);
		panel_1.add(scrollPane);

		tbShopping = new JTable();
		scrollPane.setViewportView(tbShopping);

		tbShopping.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// for the shopping basket table
		dtmShopping = new DefaultTableModel();
		dtmShopping.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Type", "Brand", "Colour", "Connectivity",
				"Quantitity in Stock", "Retail Price", "Additional Info" });
		tbShopping.setModel(dtmShopping);

		JButton btnNewButton_1 = new JButton("Clear Basket");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dtmShopping.getDataVector().removeAllElements();
				dtmShopping.fireTableDataChanged();
				selectedUser.clearBasket();
				try {
					// clears the basket and also refills the table with the correct quantities
					dtmProductsC.getDataVector().removeAllElements();
					dtmProductsC.fireTableDataChanged();
					fillTableCustomer(dtmProductsC);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(22, 399, 117, 29);
		panel_1.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Credit Card");
		final CustomerFrame ctf = this;
		newStock = "";
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// brings you to the credit card page
				ctf.dispose();
				CreditCard ccf = new CreditCard(selectedUser.getAddress().toString(), totalSum);
				ccf.setVisible(true);

				// puts it in the format for how you enter it into the text file

				for (int row = 0; row < dtmProductsC.getRowCount(); row++) {
					for (int column = 0; column < dtmProductsC.getColumnCount(); column++) {
						newStock += dtmProductsC.getValueAt(row, column);
						if (column < dtmProductsC.getColumnCount() - 1) {
							newStock += ", ";
						}
					}
					if (row < dtmProductsC.getRowCount() - 1) {
						newStock += "\n";
					}
				}
				PrintWriter pw;
				try {
					// rewrites and removes everything from the text fill
					pw = new PrintWriter("Stock.txt");
					pw.close();
					// writes it all to the same text file
					File inputFile = new File("Stock.txt");
					FileWriter writeFile = new FileWriter(inputFile, true);
					writeFile.write(newStock);
					writeFile.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(newStock);
			}
		});
		btnNewButton_2.setBounds(702, 399, 117, 29);
		panel_1.add(btnNewButton_2);

		JLabel lblNewLabel_1 = new JLabel("OR");
		lblNewLabel_1.setBounds(682, 404, 18, 16);
		panel_1.add(lblNewLabel_1);

		JButton btnNewButton_3 = new JButton("PayPal");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// brings you to the paypal page

				ctf.dispose();
				PayPal ppf = new PayPal(selectedUser.getAddress().toString(), totalSum);
				ppf.setVisible(true);

				// puts it in the format for how you enter it into the text file

				for (int row = 0; row < dtmProductsC.getRowCount(); row++) {
					for (int column = 0; column < dtmProductsC.getColumnCount(); column++) {
						newStock += dtmProductsC.getValueAt(row, column);
						if (column < dtmProductsC.getColumnCount() - 1) {
							newStock += ", ";
						}
					}
					if (row < dtmProductsC.getRowCount() - 1) {
						newStock += "\n";
					}
				}
				PrintWriter pw;
				try {
					// rewrites and removes everything from the text fill

					pw = new PrintWriter("Stock.txt");
					pw.close();
					// writes it all to the same text file
					File inputFile = new File("Stock.txt");
					FileWriter writeFile = new FileWriter(inputFile, true);
					writeFile.write(newStock);
					writeFile.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println(newStock);
			}
		});
		btnNewButton_3.setBounds(564, 399, 117, 29);
		panel_1.add(btnNewButton_3);

		JLabel lblNewLabel_2 = new JLabel("Checkout With :");
		lblNewLabel_2.setBounds(463, 404, 100, 16);
		panel_1.add(lblNewLabel_2);

		JButton btnLogOut = new JButton("LOG OUT");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctf.dispose();
				Login frame;
				try {
					// brings it back to the login file
					frame = new Login();
					frame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnLogOut.setBounds(749, 26, 117, 29);
		contentPane.add(btnLogOut);

		try {
			// hides the original Cost column
			tbProducts.getColumnModel().getColumn(7).setMinWidth(0);
			tbProducts.getColumnModel().getColumn(7).setMaxWidth(0);
			tbProducts.getColumnModel().getColumn(7).setWidth(0);
			fillTableCustomer(dtmProductsC);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	// fills the table for the customer frame

	public void fillTableCustomer(DefaultTableModel dtmProductsC) throws FileNotFoundException {
		for (int i = 0; i < dtmProductsC.getRowCount(); i++) {
			dtmProductsC.removeRow(i);
		}

		List<Product> productList = selectedUser.getStock();

		PriceComparator priceComp = new PriceComparator();
		Collections.sort(productList, priceComp);

		for (Product prod : productList) {
			String[] row = new String[10];
			row[0] = prod.getBarcode();
			row[1] = prod.getType();
			if (prod.getType().equals("mouse")) {
				row[2] = ((Mouse) prod).getMouseType().name();
			} else {
				row[2] = ((Keyboard) prod).getKeyboardType().name();
			}
			row[3] = prod.getBrand();
			row[4] = prod.getColour();
			row[5] = prod.getConnectivity().name();
			row[6] = String.valueOf(prod.getQuantity());
			row[7] = String.valueOf(prod.getOriginalCost());
			row[8] = String.valueOf(prod.getRetailCost());
			if (prod.getType().equals("mouse")) {
				row[9] = String.valueOf(((Mouse) prod).getNumButtons());
			} else {
				row[9] = ((Keyboard) prod).getKeyboardLayout().name();
			}
			dtmProductsC.addRow(row);
		}

	}
}
