package coursework2;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PayPal extends JFrame {

	private JPanel contentPane;
	private JTextField txtEmail;
	private JTextField txtTotalPricePP;
	private JTextField txtAddressPP;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private String userAddress;
	private float userSum;

	/**
	 * Create the frame.
	 */
	public PayPal(String newAddress, float finalSum) {

		try {
			List<User> userList = Database.UserReadFromFile();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		// transfers over the total sum and the new address across different classes
		this.userAddress = newAddress;
		this.userSum = finalSum;

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 536, 382);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("PayPal");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 30));
		lblNewLabel.setBounds(202, 52, 104, 44);
		contentPane.add(lblNewLabel);

		txtEmail = new JTextField();
		txtEmail.setBounds(239, 120, 130, 26);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Enter Email:");
		lblNewLabel_1.setBounds(153, 125, 74, 16);
		contentPane.add(lblNewLabel_1);

		txtTotalPricePP = new JTextField();
		txtTotalPricePP.setBounds(239, 191, 130, 26);
		contentPane.add(txtTotalPricePP);
		txtTotalPricePP.setColumns(10);

		txtTotalPricePP.setText("£" + String.format("%.2f", finalSum) + "");

		txtAddressPP = new JTextField();
		txtAddressPP.setBounds(239, 229, 184, 26);
		contentPane.add(txtAddressPP);
		txtAddressPP.setColumns(10);

		txtAddressPP.setText(userAddress);

		JLabel lblNewLabel_2 = new JLabel("Total Price:");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(153, 195, 80, 16);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Delivery Address:");
		lblNewLabel_3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(108, 233, 125, 16);
		contentPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Pay");
		final PayPal ppf = this;
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = (String) txtEmail.getText();
				// validation for the email
				if (txtEmail.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "All fields need to be filled!", "Error!",
							JOptionPane.ERROR_MESSAGE);
				} else if (!email.matches(EMAIL_PATTERN)) {
					JOptionPane.showMessageDialog(null, "Needs to be in email format! \n E.G: email@email.com",
							"Error!", JOptionPane.ERROR_MESSAGE);
				} else {
					ppf.dispose();
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
		btnNewButton.setBounds(202, 286, 117, 29);
		contentPane.add(btnNewButton);
	}
}
