package coursework2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JTable tbProductAdmin;
	private DefaultTableModel dtmProducts;
	private JTextField txtOriginal;
	private JTextField txtBarcode;
	private JTextField txtBrand;
	private JTextField txtColour;
	private JTextField txtQuantity;
	private JTextField txtRetail;
	private JComboBox cbConnectivity;
	private JComboBox cbDeviceName;
	private JTextField txtNumOfBtns;
	private JComboBox cbMouseType;
	private JComboBox cbKeyboardLayout;
	private JComboBox cbKeyboardType;
	private Admin selectedUser;

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	public AdminFrame(Admin selectedUser) throws FileNotFoundException {
		this.selectedUser = selectedUser;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 882, 592);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// for testing when I was checking if my textfile worked

		List<Product> productList = Database.ProductReadFromFile();
		String[] productType = new String[productList.size()];
		for (int i = 0; i < productList.size(); i++) {
			productType[i] = productList.get(i).getType();
		}

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(6, 68, 870, 496);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Product", null, panel, null);
		panel.setLayout(null);

		txtOriginal = new JTextField();
		txtOriginal.setBounds(164, 276, 130, 26);
		panel.add(txtOriginal);
		txtOriginal.setColumns(10);

		txtBarcode = new JTextField();
		txtBarcode.setBounds(164, 85, 130, 26);
		panel.add(txtBarcode);
		txtBarcode.setColumns(10);

		txtBrand = new JTextField();
		txtBrand.setBounds(164, 123, 130, 26);
		panel.add(txtBrand);
		txtBrand.setColumns(10);

		cbConnectivity = new JComboBox(Connectivity.values());
		cbConnectivity.setBounds(164, 199, 130, 27);
		panel.add(cbConnectivity);

		txtColour = new JTextField();
		txtColour.setBounds(164, 161, 130, 26);
		panel.add(txtColour);
		txtColour.setColumns(10);

		txtQuantity = new JTextField();
		txtQuantity.setBounds(164, 238, 130, 26);
		panel.add(txtQuantity);
		txtQuantity.setColumns(10);

		txtRetail = new JTextField();
		txtRetail.setBounds(164, 314, 130, 26);
		panel.add(txtRetail);
		txtRetail.setColumns(10);

		JLabel lblNewLabel = new JLabel("Barcode");
		lblNewLabel.setBounds(103, 90, 49, 16);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Brand");
		lblNewLabel_1.setBounds(117, 128, 35, 16);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Colour");
		lblNewLabel_2.setBounds(110, 165, 42, 16);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Connectivity");
		lblNewLabel_3.setBounds(73, 205, 79, 16);
		panel.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Quantity in stock");
		lblNewLabel_4.setBounds(43, 243, 109, 16);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Original Price");
		lblNewLabel_5.setBounds(68, 281, 84, 16);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Retail Price");
		lblNewLabel_6.setBounds(78, 319, 74, 16);
		panel.add(lblNewLabel_6);

		String[] DeviceName = { "mouse", "keyboard" };
		cbDeviceName = new JComboBox(DeviceName);
		cbDeviceName.setBounds(549, 86, 130, 27);
		panel.add(cbDeviceName);

		JLabel lblNewLabel_7 = new JLabel("Device Name");
		lblNewLabel_7.setBounds(453, 90, 84, 16);
		panel.add(lblNewLabel_7);

		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// all the validation for the text fields for adding a basket
				String deviceName = (String) cbDeviceName.getSelectedItem();
				if (txtBarcode.getText().isEmpty() || txtBrand.getText().isEmpty() || txtColour.getText().isEmpty()
						|| txtQuantity.getText().isEmpty() || txtOriginal.getText().isEmpty()
						|| txtRetail.getText().isEmpty()) {
					if (deviceName.equals("mouse")) {
						if (txtNumOfBtns.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error!",
									JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(null, "All fields must be filled!", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (txtQuantity.getText().trim().matches("[0-9]+") == false) {
					JOptionPane.showMessageDialog(null, "Quantity needs to be numeric!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (txtOriginal.getText().trim().matches("[0-9]+") == false) {
					JOptionPane.showMessageDialog(null, "Original Price needs to be numeric!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (txtRetail.getText().trim().matches("[0-9]+") == false) {
					JOptionPane.showMessageDialog(null, "Retail Price needs to be numeric!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (txtNumOfBtns.getText().trim().matches("[0-9]+") == false && deviceName.equals("mouse")) {
					JOptionPane.showMessageDialog(null, "Number of Buttons needs to be numeric!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if ((txtBarcode.getText().trim().length() != 6)
						|| (txtBarcode.getText().trim().matches("[0-9]+") == false)) {
					JOptionPane.showMessageDialog(null, "Barcode needs to be 6 digits long and only numbers!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (checkIfUnique() == false) {
					JOptionPane.showMessageDialog(null, "Barcode already exists!", "Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					// this adds to the text file
					File inputFile = new File("Stock.txt");
					FileWriter writeFile;
					try {
						writeFile = new FileWriter(inputFile, true);
						if (deviceName.equals("mouse")) {
							String newStock = txtBarcode.getText() + ", mouse, "
									+ cbMouseType.getSelectedItem().toString() + ", " + txtBrand.getText() + ", "
									+ txtColour.getText() + ", " + cbConnectivity.getSelectedItem().toString() + ", "
									+ Integer.parseInt(txtQuantity.getText()) + ", "
									+ Float.parseFloat(txtOriginal.getText()) + ", "
									+ Float.parseFloat(txtRetail.getText()) + ", "
									+ Float.parseFloat(txtNumOfBtns.getText());
							writeFile.write("\n" + newStock);
							writeFile.close();
						} else {
							String newStock = txtBarcode.getText() + ", keyboard, "
									+ cbKeyboardType.getSelectedItem().toString() + ", " + txtBrand.getText() + ", "
									+ txtColour.getText() + ", " + cbConnectivity.getSelectedItem().toString() + ", "
									+ Integer.parseInt(txtQuantity.getText()) + ", "
									+ Float.parseFloat(txtOriginal.getText()) + ", "
									+ Float.parseFloat(txtRetail.getText()) + ", "
									+ cbKeyboardLayout.getSelectedItem().toString();
							writeFile.write("\n" + newStock);
							writeFile.close();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(346, 375, 117, 29);
		panel.add(btnNewButton);

		// getting combo box values from the enums
		cbMouseType = new JComboBox(MouseType.values());
		cbMouseType.setBounds(549, 162, 130, 27);
		panel.add(cbMouseType);

		cbKeyboardLayout = new JComboBox(KeyboardLayout.values());
		cbKeyboardLayout.setBounds(549, 315, 130, 27);
		panel.add(cbKeyboardLayout);

		cbKeyboardType = new JComboBox(KeyboardType.values());
		cbKeyboardType.setBounds(549, 275, 130, 27);
		panel.add(cbKeyboardType);

		txtNumOfBtns = new JTextField();
		txtNumOfBtns.setBounds(549, 198, 130, 26);
		panel.add(txtNumOfBtns);
		txtNumOfBtns.setColumns(10);

		JLabel lblNewLabel_8 = new JLabel("For Mouse");
		lblNewLabel_8.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_8.setBounds(517, 128, 69, 16);
		panel.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("For Keyboard");
		lblNewLabel_9.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_9.setBounds(504, 243, 90, 16);
		panel.add(lblNewLabel_9);

		JLabel lblNewLabel_10 = new JLabel("Type");
		lblNewLabel_10.setBounds(507, 166, 30, 16);
		panel.add(lblNewLabel_10);

		JLabel lblNewLabel_11 = new JLabel("Number of Buttons");
		lblNewLabel_11.setBounds(418, 203, 119, 16);
		panel.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Type");
		lblNewLabel_12.setBounds(507, 281, 30, 16);
		panel.add(lblNewLabel_12);

		JLabel lblNewLabel_13 = new JLabel("Layout");
		lblNewLabel_13.setBounds(495, 319, 42, 16);
		panel.add(lblNewLabel_13);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("View All Products", null, panel_1, null);
		panel_1.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 50, 837, 394);
		panel_1.add(scrollPane);

		tbProductAdmin = new JTable();
		scrollPane.setViewportView(tbProductAdmin);

		// creates the table and fills in the data from the text file

		dtmProducts = new DefaultTableModel();
		dtmProducts.setColumnIdentifiers(new Object[] { "Barcode", "Name", "Type", "Brand", "Colour", "Connectivity",
				"Quantitity in Stock", "Original Cost", "Retail Price", "Additional Info" });
		tbProductAdmin.setModel(dtmProducts);

		tbProductAdmin.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JButton btnViewProducts = new JButton("View Products");
		btnViewProducts.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				try {
					// clears the table
					dtmProducts.getDataVector().removeAllElements();
					dtmProducts.fireTableDataChanged();

					fillTableAdmin(dtmProducts);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnViewProducts.setBounds(362, 9, 117, 29);
		panel_1.add(btnViewProducts);

		JLabel lblStock = new JLabel("STOCK");
		lblStock.setForeground(new Color(204, 51, 102));
		lblStock.setFont(new Font("Arial", Font.BOLD, 17));
		lblStock.setBounds(21, 23, 58, 42);
		contentPane.add(lblStock);

		JButton btnLogOut = new JButton("LOG OUT");
		final AdminFrame adf = this;
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				adf.dispose();
				Login frame;
				try {
					frame = new Login();
					frame.setVisible(true);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnLogOut.setBounds(748, 27, 117, 29);
		contentPane.add(btnLogOut);

	}

	// function to fill the admin table

	public void fillTableAdmin(DefaultTableModel dtmProducts) throws FileNotFoundException {
		for (int i = 0; i < dtmProducts.getRowCount(); i++) {
			dtmProducts.removeRow(i);
		}

		List<Product> productList = Database.ProductReadFromFile();

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
			row[7] = "£" + String.valueOf(prod.getOriginalCost());
			row[8] = "£" + String.valueOf(prod.getRetailCost());
			if (prod.getType().equals("mouse")) {
				row[9] = String.valueOf(((Mouse) prod).getNumButtons()) + " (num of btns)";
			} else {
				row[9] = ((Keyboard) prod).getKeyboardLayout().name() + " (layout)";
			}
			dtmProducts.addRow(row);
		}

	}

	public void WriteToFile() {
		File inputFile = new File("Stock.txt");
		FileWriter writeFile;
		try {
			writeFile = new FileWriter(inputFile, true);
			String deviceName = (String) cbDeviceName.getSelectedItem();
			if (deviceName.equals("mouse")) {
				String newStock = txtBarcode.getText() + ", mouse, " + cbMouseType.getSelectedItem().toString() + ", "
						+ txtBrand.getText() + ", " + txtColour.getText() + ", "
						+ cbConnectivity.getSelectedItem().toString() + ", " + Integer.parseInt(txtQuantity.getText())
						+ ", " + Float.parseFloat(txtOriginal.getText()) + ", " + Float.parseFloat(txtRetail.getText())
						+ ", " + Float.parseFloat(txtNumOfBtns.getText());
				writeFile.write(newStock);
			} else {
				String newStock = txtBarcode.getText() + ", keyboard, " + cbKeyboardType.getSelectedItem().toString()
						+ ", " + txtBrand.getText() + ", " + txtColour.getText() + ", "
						+ cbConnectivity.getSelectedItem().toString() + ", " + Integer.parseInt(txtQuantity.getText())
						+ ", " + Float.parseFloat(txtOriginal.getText()) + ", " + Float.parseFloat(txtRetail.getText())
						+ ", " + cbKeyboardLayout.getSelectedItem().toString();
				writeFile.write("\n" + newStock);
				writeFile.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkIfUnique() {

		List<Product> productList = Database.ProductReadFromFile();
		for (Product prod : productList) {
			if (prod.getBarcode().equals(txtBarcode.getText().trim())) {
				return false;
			}
		}
		return true;

	}
}
