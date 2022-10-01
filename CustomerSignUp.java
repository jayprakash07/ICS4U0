import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
 * Desc: Customer Sign Up Window for Bank GUI
 * Method List: public CustomerSignUp() - Constructs frame
 * 				public boolean idAvailable(String id) throws IOException - Checks if entered ID is available
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public void actionPerformed(ActionEvent event) - Main method
 *
 */
public class CustomerSignUp extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JLabel idLabel, passwordLabel, nameLabel, addressLabel, phoneLabel, chequingLabel, savingsLabel, investemntLabel;
	private JTextField idInput, nameInput, addressInput, phoneInput, chequingInput, savingsInput, investmentInput;
	private JPasswordField passwordInput;
	private JPanel buttonPanel;
	private JButton backButton, confirmButton;

	/**
	 * 
	 */
	public CustomerSignUp() {
		// Create frame
		super("Registration | Bank Of Suzuki");
		setSize(300, 510);
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
		idLabel.setBounds(50, 80, 200, 20);
		add(idLabel);
		idInput = new JTextField();
		idInput.setBounds(50, 100, 200, 20);
		add(idInput);

		// Create password input
		passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(50, 120, 200, 20);
		add(passwordLabel);
		passwordInput = new JPasswordField();
		passwordInput.setBounds(50, 140, 200, 20);
		add(passwordInput);

		// Create name input
		nameLabel = new JLabel("Name:");
		nameLabel.setBounds(50, 160, 200, 20);
		add(nameLabel);
		nameInput = new JTextField();
		nameInput.setBounds(50, 180, 200, 20);
		add(nameInput);

		// Create address input
		addressLabel = new JLabel("Address:");
		addressLabel.setBounds(50, 200, 200, 20);
		add(addressLabel);
		addressInput = new JTextField();
		addressInput.setBounds(50, 220, 200, 20);
		add(addressInput);

		// Create phone input
		phoneLabel = new JLabel("Phone Number:");
		phoneLabel.setBounds(50, 240, 200, 20);
		add(phoneLabel);
		phoneInput = new JTextField();
		phoneInput.setBounds(50, 260, 200, 20);
		add(phoneInput);

		// Create chequing input
		chequingLabel = new JLabel("Chequing Account Balance:");
		chequingLabel.setBounds(50, 280, 200, 20);
		add(chequingLabel);
		chequingInput = new JTextField();
		chequingInput.setBounds(50, 300, 200, 20);
		add(chequingInput);

		// Create savings input
		savingsLabel = new JLabel("Savings Account Balance:");
		savingsLabel.setBounds(50, 320, 200, 20);
		add(savingsLabel);
		savingsInput = new JTextField();
		savingsInput.setBounds(50, 340, 200, 20);
		add(savingsInput);

		// Create investment input
		investemntLabel = new JLabel("Investment Account Balance:");
		investemntLabel.setBounds(50, 360, 200, 20);
		add(investemntLabel);
		investmentInput = new JTextField();
		investmentInput.setBounds(50, 380, 200, 20);
		add(investmentInput);

		// Create buttons
		buttonPanel = new JPanel();
		buttonPanel.setBounds(50, 410, 200, 80);
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		buttonPanel.add(backButton);
		confirmButton = new JButton("Register");
		confirmButton.addActionListener(this);
		buttonPanel.add(confirmButton);
		add(buttonPanel);
	}

	/**
	 * Method to check if ID is available
	 */
	public boolean idAvailable(String id) throws IOException {
		// Get number of logins
		BufferedReader logins = new BufferedReader(new FileReader("CUSTOMERLOGINS"));
		int n = 0;
		while(logins.readLine() != null) n++;
		logins.close();

		// Get login information
		logins = new BufferedReader(new FileReader("CUSTOMERLOGINS"));
		for(int i = 0; i < n; i++) {
			String[] info = logins.readLine().split("/");
			// Compare ID
			if(id.equals(info[0])) {
				logins.close();

				// ID is taken
				return false;
			}
		}

		// ID is available
		return true;
	}

	/**
	 * Method to listen to buttons
	 */
	public void actionPerformed(ActionEvent event) {
		try {
			// Try sign up
			if(event.getSource() == confirmButton) {
				String id = idInput.getText();

				// Check if id is available
				if(idAvailable(id)) {
					// Convert password to string
					String password = "";
					char[] passwordChars = passwordInput.getPassword();
					for(int i = 0; i < passwordChars.length; i++) {
						password += passwordChars[i];
					}

					try {
						// Get information
						String name = nameInput.getText();
						String address = addressInput.getText();
						String phone = phoneInput.getText();
						double chequingBalance = Double.parseDouble(chequingInput.getText());
						double savingsBalance = Double.parseDouble(savingsInput.getText());
						double investmentBalance = Double.parseDouble(investmentInput.getText());

						// Create accounts
						Customer customer = new Customer(name, address, phone);
						ChequingAccount chequing = new ChequingAccount(customer, chequingBalance);
						SavingsAccount savings = new SavingsAccount(customer, savingsBalance);
						InvestmentAccount investment = new InvestmentAccount(customer, investmentBalance);

						// Create customer file
						String fileName = name + "-" + chequing.getAccountNumber() + "-" + savings.getAccountNumber() + "-" + investment.getAccountNumber();
						PrintWriter customerFile = new PrintWriter(new FileWriter(fileName));
						customerFile.println(address);
						customerFile.println(phone);
						customerFile.println(chequing.getBalance());
						customerFile.println(savings.getBalance());
						customerFile.print(investment.getBalance());
						customerFile.close();

						// Add information to logins file
						BufferedWriter logins = new BufferedWriter(new FileWriter("CUSTOMERLOGINS", true));
						logins.newLine();
						logins.append(id + "/" + password + "/" + fileName);
						logins.close();

						// Proceed to Customer UI
						setVisible(false);
						Main.openCustomerUI(fileName);
					}
					catch(NumberFormatException error) {
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
					}
				}
				// ID is taken
				else JOptionPane.showMessageDialog(null, "ID " + id + " is taken");
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
