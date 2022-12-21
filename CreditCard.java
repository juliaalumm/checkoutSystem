package coursework2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CreditCard extends JFrame {

	private JPanel contentPane;
	private JTextField txtCardNumber;
	private JTextField txtSecruityCode;
	private JTextField txtTotalPriceCC;
	private JTextField txtAddressCC;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btnNewButton;
	private String userAddress;
	private float userSum;

	/**
	 * Create the frame.
	 */
	public CreditCard(String newAddress, float finalSum) {

		this.userAddress = newAddress;
		this.userSum = finalSum;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 581, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Credit Card");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(184, 55, 183, 46);
		contentPane.add(lblNewLabel);

		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(262, 113, 130, 26);
		contentPane.add(txtCardNumber);
		txtCardNumber.setColumns(10);

		txtSecruityCode = new JTextField();
		txtSecruityCode.setBounds(262, 151, 130, 26);
		contentPane.add(txtSecruityCode);
		txtSecruityCode.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Card Number");
		lblNewLabel_1.setBounds(162, 118, 83, 16);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Secruity Code");
		lblNewLabel_2.setBounds(158, 156, 92, 16);
		contentPane.add(lblNewLabel_2);

		txtTotalPriceCC = new JTextField();
		txtTotalPriceCC.setBounds(262, 215, 130, 26);
		contentPane.add(txtTotalPriceCC);
		txtTotalPriceCC.setColumns(10);

		txtTotalPriceCC.setText("£" + String.format("%.2f", finalSum) + "");

		txtAddressCC = new JTextField();
		txtAddressCC.setBounds(262, 253, 183, 26);
		contentPane.add(txtAddressCC);
		txtAddressCC.setColumns(10);

		txtAddressCC.setText(newAddress);

		lblNewLabel_3 = new JLabel("Total Price:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(165, 220, 80, 16);
		contentPane.add(lblNewLabel_3);

		lblNewLabel_4 = new JLabel("Delivery Address:");
		lblNewLabel_4.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(125, 258, 125, 16);
		contentPane.add(lblNewLabel_4);

		btnNewButton = new JButton("Pay");
		final CreditCard ccf = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// validation for the text fields
				if (txtCardNumber.getText().isEmpty() || txtSecruityCode.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields must be filled out!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (txtSecruityCode.getText().trim().matches("[0-9]+") == false
						|| txtSecruityCode.getText().trim().length() != 3) {
					JOptionPane.showMessageDialog(null, "Secruity Code needs to be all numeric and only 3 digits long!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				} else if (txtCardNumber.getText().trim().matches("[0-9]+") == false
						|| txtCardNumber.getText().trim().length() != 6) {
					JOptionPane.showMessageDialog(null, "Card Number needs to be all numeric and only 6 digits long!",
							"Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					// returns back to login page after youve checked in
					ccf.dispose();
					Login frame;
					try {
						frame = new Login();
						frame.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setBounds(213, 302, 117, 29);
		contentPane.add(btnNewButton);
	}

}
