import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTable;
import javax.imageio.*;

/**
 * 
 */

/**
 * @author simranbaria
 * @author Jayprakash Pathak
 * Date: Jun. 2022
 * Desc: Customer UI Window for Bank GUI
 * Method List: public CustomerUI(Customer customer, ChequingAccount chequing, SavingsAccount savings, InvestmentAccount investment) - Contructs frame
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public static void main(String[] args) - Main method
 *
 */
public class CustomerUI extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JLabel nameLabel, addressLabel, phoneLabel, typeLabel, numberLabel, balanceLabel;
	private JTable outputTable;
	private JPanel outputPanel, buttonPanel;
	private JButton depositButton, withdrawButton, saveButton, backButton, quitButton;
	private Customer customer;
	private ChequingAccount chequing;
	private SavingsAccount savings;
	private InvestmentAccount investment;

	/**
	 * 
	 */
	public CustomerUI(Customer customer, ChequingAccount chequing, SavingsAccount savings, InvestmentAccount investment) {
		// Create frame
		super("Bank Account | Bank Of Suzuki");
		setSize(500, 290);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Add Logo
		try {
			ImagePicture logo = new ImagePicture(new ImageIcon(ImageIO.read(getClass().getResource("Logo_V1_Small.png"))));
			logo.setBounds(235, 0, 250, 250);
			add(logo);
		}
		catch(IOException error) {

		}

		// Initialize information
		this.customer = customer;
		this.chequing = chequing;
		this.savings = savings;
		this.investment = investment;

		// Create customer info labels
		nameLabel = new JLabel("Name: " + this.customer.getName());
		nameLabel.setBounds(25, 25, 450, 20);
		add(nameLabel);
		addressLabel = new JLabel("Address: " + this.customer.getAddress());
		addressLabel.setBounds(25, 45, 450, 20);
		add(addressLabel);
		phoneLabel = new JLabel("Phone Number: " + this.customer.getPhone());
		phoneLabel.setBounds(25, 65, 450, 20);
		add(phoneLabel);

		// Create output labels
		typeLabel = new JLabel("Account Type");
		typeLabel.setBounds(25, 100, 150, 20);
		add(typeLabel);
		numberLabel = new JLabel("Account Number");
		numberLabel.setBounds(175, 100, 150, 20);
		add(numberLabel);
		balanceLabel = new JLabel("Account Balance");
		balanceLabel.setBounds(325, 100, 150, 20);
		add(balanceLabel);

		// Create output area
		outputPanel = new JPanel();
		outputPanel.setBounds(25, 120, 450, 65);
		outputTable = new JTable(3, 3);

		// Initialize output with inputed information
		outputTable.setValueAt("Chequing", 0, 0);
		outputTable.setValueAt("Savings", 1, 0);
		outputTable.setValueAt("Investment", 2, 0);
		outputTable.setValueAt(this.chequing.getAccountNumber(), 0, 1);
		outputTable.setValueAt(this.savings.getAccountNumber(), 1, 1);
		outputTable.setValueAt(this.investment.getAccountNumber(), 2, 1);
		outputTable.setValueAt(this.chequing.formatBalance(this.chequing.getBalance()), 0, 2);
		outputTable.setValueAt(this.savings.formatBalance(this.savings.getBalance()), 1, 2);
		outputTable.setValueAt(this.investment.formatBalance(this.investment.getBalance()), 2, 2);

		outputTable.setRowHeight(20);
		for(int i = 0; i < 3; i++) {
			outputTable.getColumnModel().getColumn(i).setPreferredWidth(150);
		}
		outputTable.setDefaultEditor(Object.class, null);
		outputPanel.add(outputTable);
		add(outputPanel);

		// Create buttons
		buttonPanel = new JPanel();
		buttonPanel.setBounds(25, 190, 450, 95);

		depositButton = new JButton("Deposit");
		depositButton.addActionListener(this);
		buttonPanel.add(depositButton);

		withdrawButton = new JButton("Withdraw");
		withdrawButton.addActionListener(this);
		buttonPanel.add(withdrawButton);

		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		buttonPanel.add(saveButton);

		backButton = new JButton("Log Out");
		backButton.addActionListener(this);
		buttonPanel.add(backButton);

		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		buttonPanel.add(quitButton);

		add(buttonPanel);
	}

	/**
	 * Method to listen to buttons
	 */
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
		case "Deposit": {
			// Get selected account
			int row = outputTable.getSelectedRow();

			// No account selected
			if(row < 0) JOptionPane.showMessageDialog(null, "Please select an account");
			// Account selected
			else {
				try {
					// Get funds
					double funds = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter funds to deposit"));

					switch(row) {
					// Chequing
					case 0: {
						if(chequing.deposit(funds)) {
							// Display funds
							outputTable.setValueAt(chequing.formatBalance(chequing.getBalance()), 0, 2);
							JOptionPane.showMessageDialog(null, "$" + chequing.formatBalance(funds) + " deposited into Chequing Account\nA service fee of $" +
									chequing.formatBalance(chequing.getServiceFee()) + " has been applied to your balance");
						}
						else JOptionPane.showMessageDialog(null, "Cannot deposit negative funds");

						break;
					}
					// Savings
					case 1: {
						if(savings.deposit(funds)) {
							// Display funds
							outputTable.setValueAt(savings.formatBalance(savings.getBalance()), 1, 2);
							JOptionPane.showMessageDialog(null, "$" + savings.formatBalance(funds) + " deposited into Savings Account");
						}
						else JOptionPane.showMessageDialog(null, "Cannot deposit negative funds");

						break;
					}
					// Investment
					case 2: {
						if(investment.deposit(funds)) {
							// Display funds
							outputTable.setValueAt(investment.formatBalance(investment.getBalance()), 2, 2);
							JOptionPane.showMessageDialog(null, "$" + investment.formatBalance(funds) + " deposited into Investment Account\nA charge of " + investment.getCharge() +
									"% has been applied to your balance");
						}
						else JOptionPane.showMessageDialog(null, "Cannot deposit funds less than " + investment.formatBalance(investment.getMinimumInvestment()));

						break;
					}
					}
				}
				catch(NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "No funds entered");
				}
				catch(NullPointerException error) {

				}
			}

			break;
		}
		case "Withdraw": {
			// Get selected account
			int row = outputTable.getSelectedRow();

			// No account selected
			if(row < 0) JOptionPane.showMessageDialog(null, "Please select an account");
			// Account selected
			else {
				try {
					// Get funds
					double funds = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter funds to withdraw"));

					switch(row) {
					// Chequing
					case 0: {
						if(chequing.withdraw(funds)) {
							// Display funds
							outputTable.setValueAt(chequing.formatBalance(chequing.getBalance()), 0, 2);
							JOptionPane.showMessageDialog(null, "$" + chequing.formatBalance(funds) + " withdrawn from Chequing Account\nA deduction fee of " + chequing.getDeductionFee() +
									"% has been applied to your balance");
						}
						else JOptionPane.showMessageDialog(null, "Insufficient funds");

						break;
					}
					// Savings
					case 1: {
						if(savings.withdraw(funds)) {
							// Display funds
							outputTable.setValueAt(savings.formatBalance(savings.getBalance()), 1, 2);
							if(savings.getBalance() < savings.getMinimumBalance()) JOptionPane.showMessageDialog(null, "$" + savings.formatBalance(funds) +
									" withdrawn from Savings Account\nA deduction fee of $" + savings.formatBalance(savings.getDeductionFee()) + " has been applied to your balance");
							else JOptionPane.showMessageDialog(null, "$" + savings.formatBalance(funds) + " withdrawn from Savings Account");
						}
						else JOptionPane.showMessageDialog(null, "Insufficient funds");

						break;
					}
					// Investment
					case 2: {
						int days = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter days since your last withdrawal"));
						if(investment.withdraw(funds, days)) {
							// Display funds
							outputTable.setValueAt(investment.formatBalance(investment.getBalance()), 2, 2);
							if(days >= investment.getDays()) JOptionPane.showMessageDialog(null, "$" + investment.formatBalance(funds) + " withdrawn from Investment Account\nAn interest of " +
									investment.getInterest() + "% has been applied to your balance");
							else JOptionPane.showMessageDialog(null, "$" + investment.formatBalance(funds) + " withdrawn from Investment Account\nA penalty of " +
									investment.getPenalty() + "% has been applied to your balance");
						}
						else JOptionPane.showMessageDialog(null, "Insufficient funds");

						break;
					}
					}
				}
				catch(NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "No funds entered");
				}
				catch(NullPointerException error) {

				}
			}

			break;
		}
		case "Save": {
			try {
				// Open file
				String fileName = customer.getName() + "-" + chequing.getAccountNumber() + "-" + savings.getAccountNumber() + "-" + investment.getAccountNumber();
				PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));

				// Print information
				outputFile.println(customer.getAddress());
				outputFile.println(customer.getPhone());
				outputFile.println(chequing.getBalance());
				outputFile.println(savings.getBalance());
				outputFile.print(investment.getBalance());

				// Close file
				outputFile.close();
				JOptionPane.showMessageDialog(null, "Saved");
			}
			catch(IOException error) {
				JOptionPane.showMessageDialog(null, "Error saving file");
			}

			break;
		}
		case "Log Out": {
			int confirm = JOptionPane.showConfirmDialog(null, "Save before exiting?");
			if(confirm == 0) saveButton.doClick();
			if(confirm != 2) {
				// Did not cancel
				setVisible(false);
				Main.back();
			}
			break;
		}
		case "Quit": {
			int confirm = JOptionPane.showConfirmDialog(null, "Save before exiting?");
			if(confirm == 0) saveButton.doClick();
			if(confirm != 2) {
				// Did not cancel
				setVisible(false);
				System.exit(0);
			}
			break;
		}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
