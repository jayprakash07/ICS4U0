import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.imageio.*;

/**
 * 
 */

/**
 * @author simranbaria
 * Date: Jun. 2022
 * Desc: Customer Login Window for Bank GUI
 * Method List: public CustomerLogin() - Constructs frame
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public static void main(String[] args) - Main method
 *
 */
public class CustomerLogin extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JLabel idLabel, passwordLabel;
	private JTextField idInput;
	private JPasswordField passwordInput;
	private JPanel buttonPanel;
	private JButton backButton, confirmButton;

	/**
	 * 
	 */
	public CustomerLogin() {
		// Create frame
		super("Login | Bank Of Suzuki");
		setSize(300, 300);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Add logo
		try {
			ImagePicture logo = new ImagePicture(new ImageIcon(ImageIO.read(getClass().getResource("Logo_V1_Small.png"))));
			logo.setBounds(0, 0, 250, 250);
			add(logo);
		}
		catch(IOException error) {

		}

		// Create id input
		idLabel = new JLabel("ID:");
		idLabel.setBounds(50, 100, 150, 20);
		add(idLabel);
		idInput = new JTextField();
		idInput.setBounds(50, 120, 150, 20);
		add(idInput);

		// Create password input
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(50, 140, 150, 20);
		add(passwordLabel);
		passwordInput = new JPasswordField();
		passwordInput.setBounds(50, 160, 150, 20);
		add(passwordInput);

		// Create buttons
		buttonPanel = new JPanel();
		buttonPanel.setBounds(25, 200, 200, 80);
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		buttonPanel.add(backButton);
		confirmButton = new JButton("Log In");
		confirmButton.addActionListener(this);
		buttonPanel.add(confirmButton);
		add(buttonPanel);
	}

	/**
	 * Method to listen to buttons
	 */
	public void actionPerformed(ActionEvent event) {
		try {
			// Try login
			if(event.getSource() == confirmButton) {
				// Get number of logins
				BufferedReader logins = new BufferedReader(new FileReader("CUSTOMERLOGINS"));
				int n = 0;
				while(logins.readLine() != null) n++;
				logins.close();

				// Convert password to string
				String password = "";
				char[] passwordChars = passwordInput.getPassword();
				for(int i = 0; i < passwordChars.length; i++) {
					password += passwordChars[i];
				}

				// Initialize login to false
				boolean loginSuccessful = false;

				// Get login information
				logins = new BufferedReader(new FileReader("CUSTOMERLOGINS"));

				// Loop through information and check if login exists
				for(int i = 0; i < n; i++) {
					String[] info = logins.readLine().split("/");
					if(idInput.getText().equals(info[0]) && password.equals(info[1])) {
						// Login successful
						loginSuccessful = true;
						String fileName = info[2];
						logins.close();

						// Proceed to Customer UI
						setVisible(false);
						Main.openCustomerUI(fileName);
						break;
					}
				}

				// Login failed
				if(!loginSuccessful) JOptionPane.showMessageDialog(null, "Login Failed");
				logins.close();
			}
			// Return to start up window
			else {
				setVisible(false);
				Main.back();
			}
		}
		catch(IOException error) {
			JOptionPane.showMessageDialog(null, "Error loading login information");
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
