package coursework2;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Login extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private JComboBox cbUser;
	private float totalSum;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws FileNotFoundException
	 */
	public Login() throws FileNotFoundException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// makes a list of all of the users
		List<User> userList = Database.UserReadFromFile();
		String[] username = new String[userList.size()];
		for (int i = 0; i < userList.size(); i++) {
			username[i] = userList.get(i).getUsername();
		}

		cbUser = new JComboBox(username);
		cbUser.setBounds(136, 125, 160, 27);
		contentPane.add(cbUser);

		JButton btnLogIn = new JButton("LOGIN");
		final Login frame = this;
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cbUser.getSelectedIndex();
				User selectedUser = userList.get(index);
				System.out.println(selectedUser.getAddress());
				PayPal ppf = new PayPal(selectedUser.getAddress().toString(), totalSum);
				CreditCard ccf = new CreditCard(selectedUser.getAddress().toString(), totalSum);

				frame.dispose();

				// goes to a different frame depending on whether its an admin or customer
				if (selectedUser.getType().equals("admin")) {
					AdminFrame adf;
					try {
						adf = new AdminFrame((Admin) selectedUser);
						adf.setVisible(true);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					CustomerFrame ctf;
					ctf = new CustomerFrame((Customer) selectedUser);
					ctf.setVisible(true);
				}

			}
		});

		btnLogIn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 13));
		btnLogIn.setBounds(161, 164, 117, 29);
		contentPane.add(btnLogIn);

		JLabel lblNewLabel = new JLabel("COMPUTER ACCESSORIES SHOP");
		lblNewLabel.setForeground(new Color(204, 51, 102));
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(84, 71, 273, 42);
		contentPane.add(lblNewLabel);
	}

}
