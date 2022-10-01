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
 * @author Jayprakash Pathak
 * Date: Jun. 2022
 * Desc: Employee Login Window for Bank GUI
 * Method List: public EmployeeLogin() - Constructs frame
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public static void main(String[] args)
 *
 */
public class EmployeeLogin extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JLabel idLabel, passwordLabel, pinLabel;
	private JTextField idInput;
	private JPasswordField passwordInput, pinInput;
	private JPanel buttonPanel;
	private JButton backButton, confirmButton;

	/**
	 * 
	 */
	public EmployeeLogin() {
		// Create frame
		super("Employee Login | Bank Of Suzuki");
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
		idLabel.setBounds(50, 80, 150, 20);
		add(idLabel);
		idInput = new JTextField();
		idInput.setBounds(50, 100, 150, 20);
		add(idInput);

		// Create password input
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(50, 120, 150, 20);
		add(passwordLabel);
		passwordInput = new JPasswordField();
		passwordInput.setBounds(50, 140, 150, 20);
		add(passwordInput);

		// Create pin input
		pinLabel = new JLabel("Pin:");
		pinLabel.setBounds(50, 160, 150, 20);
		add(pinLabel);
		pinInput = new JPasswordField();
		pinInput.setBounds(50, 180, 150, 20);
		add(pinInput);

		// Create buttons
		buttonPanel = new JPanel();
		buttonPanel.setBounds(25, 220, 200, 80);
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
				BufferedReader logins = new BufferedReader(new FileReader("EMPLOYEELOGINS"));
				int n = 0;
				while(logins.readLine() != null) n++;
				logins.close();

				// Convert password to string
				String password = "";
				char[] passwordChars = passwordInput.getPassword();
				for(int i = 0; i < passwordChars.length; i++) {
					password += passwordChars[i];
				}

				// Convert pin to string
				String pin = "";
				char[] pinChars = pinInput.getPassword();
				for(int i = 0; i < pinChars.length; i++) {
					pin += pinChars[i];
				}

				// Initialize login to false
				boolean loginSuccessful = false;

				// Get login information
				logins = new BufferedReader(new FileReader("EMPLOYEELOGINS"));
				for(int i = 0; i < n; i++) {
					String[] info = logins.readLine().split("/");
					if(idInput.getText().equals(info[0]) && password.equals(info[1]) && pin.equals(info[2])) {
						// Login successful
						loginSuccessful = true;
						logins.close();

						// Proceed to EmployeeUI
						setVisible(false);
						Main.openEmployeeUI();
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
