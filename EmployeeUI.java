import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * 
 */

/**
 * @author simranbaria
 * @author Jayprakash Pathak
 * Date: Jun. 2022
 * Desc: Employee UI Window for Bank GUI
 * Method List: public EmployeeUI() - Constructs frame
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public static void main(String[] args) - Main method
 *
 */
public class EmployeeUI extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JLabel nameLabel, addressLabel, phoneLabel, chequingNumberLabel, chequingBalancelabel, savingsNumberLabel, savingsBalanceLabel, investmentNumberLabel, investmentBalanceLabel;
	private JScrollPane scroller;
	private JPanel outputPanel, buttonPanel;
	private JTable outputTable;
	private JButton addButton, deleteButton, editButton, saveButton, openButton, backButton, quitButton;
	private CustomerList list;

	/**
	 * 
	 */
	public EmployeeUI() {
		// Create frame
		super("Account List | Bank Of Suzuki");
		setSize(1000, 450);
		setLayout(null);

		// Create labels
		nameLabel = new JLabel("Name");
		nameLabel.setBounds(50, 25, 100, 20);
		add(nameLabel);
		addressLabel = new JLabel("Address");
		addressLabel.setBounds(150, 25, 100, 20);
		add(addressLabel);
		phoneLabel = new JLabel("Phone");
		phoneLabel.setBounds(250, 25, 100, 20);
		add(phoneLabel);
		chequingNumberLabel = new JLabel("Chequing #");
		chequingNumberLabel.setBounds(350, 25, 100, 20);
		add(chequingNumberLabel);
		chequingBalancelabel = new JLabel("Chequing $");
		chequingBalancelabel.setBounds(450, 25, 100, 20);
		add(chequingBalancelabel);
		savingsNumberLabel = new JLabel("Savings #");
		savingsNumberLabel.setBounds(550, 25, 100, 20);
		add(savingsNumberLabel);
		savingsBalanceLabel = new JLabel("Savings $");
		savingsBalanceLabel.setBounds(650, 25, 100, 20);
		add(savingsBalanceLabel);
		investmentNumberLabel = new JLabel("Investment #");
		investmentNumberLabel.setBounds(750, 25, 100, 20);
		add(investmentNumberLabel);
		investmentBalanceLabel = new JLabel("Investment $");
		investmentBalanceLabel.setBounds(850, 25, 100, 20);
		add(investmentBalanceLabel);

		// Create list
		list = new CustomerList();

		// Create output area
		outputPanel = new JPanel();
		outputPanel.setBounds(50, 45, 900, 300);
		outputTable = list.display();
		outputTable.setTableHeader(null);
		scroller = new JScrollPane(outputTable);
		scroller.setPreferredSize(new Dimension(900, 300));
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		outputPanel.add(scroller);
		add(outputPanel);

		// Create buttons
		buttonPanel = new JPanel();
		buttonPanel.setBounds(50, 350, 900, 95);
		addButton = new JButton("Add");
		addButton.addActionListener(this);
		buttonPanel.add(addButton);
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		buttonPanel.add(deleteButton);
		editButton = new JButton("Edit");
		editButton.addActionListener(this);
		buttonPanel.add(editButton);
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		buttonPanel.add(saveButton);
		openButton = new JButton("Open");
		openButton.addActionListener(this);
		buttonPanel.add(openButton);
		backButton = new JButton("Log Out");
		backButton.addActionListener(this);
		buttonPanel.add(backButton);
		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		buttonPanel.add(quitButton);
		add(buttonPanel);

		setResizable(false);
	}

	/**
	 * Method to listen to buttons
	 */
	public void actionPerformed(ActionEvent event) {
		switch(event.getActionCommand()) {
		case "Add": {
			// Get record
			JTextField name = new JTextField();
			JTextField address = new JTextField();
			JTextField phone = new JTextField();
			JTextField chequingNumber = new JTextField();
			JTextField chequingBalance = new JTextField();
			JTextField savingsNumber = new JTextField();
			JTextField savingsBalance = new JTextField();
			JTextField investmentNumber = new JTextField();
			JTextField investmentBalance = new JTextField();
			Object[] input = {"Name:", name, "Address:", address, "Phone:", phone, "Chequing Account Number:", chequingNumber, "Chequing Account Balance:", chequingBalance, "Savings Account Number:", savingsNumber,
					"Savings Account Balance:", savingsBalance, "Investment Account Number:", investmentNumber, "Investment Account Balance:", investmentBalance};

			try {
				int confirm = JOptionPane.showConfirmDialog(null, input, "Creating Record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

				if(confirm == 0) {
					// Check if input is empty
					if(name.getText().isEmpty() || address.getText().isEmpty() || phone.getText().isEmpty() || chequingNumber.getText().isEmpty() || chequingBalance.getText().isEmpty() || savingsNumber.getText().isEmpty() ||
							savingsBalance.getText().isEmpty() || investmentNumber.getText().isEmpty() || investmentBalance.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "Please fill out all fields");
					}
					else {
						// Create record
						String record = name.getText() + "/" + address.getText() + "/" + phone.getText() + "/" + chequingNumber.getText() + "/" + chequingBalance.getText() + "/" + savingsNumber.getText() + "/" +
								savingsBalance.getText() + "/" + investmentNumber.getText() + "/" + investmentBalance.getText();

						if(list.insert(new CustomerRecord(record))) {
							// Display
							list.sort();
							outputTable.setModel(list.display().getModel());
							JOptionPane.showMessageDialog(null, "Record inserted");
						}
						else JOptionPane.showMessageDialog(null, "Could not insert record");
					}
				}
			}
			catch(NumberFormatException error) {
				JOptionPane.showMessageDialog(null, "Invalid input");
			}
			
			break;
		}
		case "Delete": {
			// Get selected record
			int row = outputTable.getSelectedRow();

			// No record selected
			if(row < 0) JOptionPane.showMessageDialog(null, "Please select a record");
			// Record selected
			else {
				// Create record
				String record = "";
				for(int i = 0; i < 9; i++) {
					record += outputTable.getValueAt(row, i) + "/";
				}

				// Remove last /
				record = record.substring(0, record.length() - 1);

				if(list.delete(new CustomerRecord(record))) {
					// Display
					list.sort();
					outputTable.setModel(list.display().getModel());
					JOptionPane.showMessageDialog(null, "Record deleted");
				}
				else JOptionPane.showMessageDialog(null, "Could not insert record");
			}

			break;
		}
		case "Edit": {
			// Get selected record
			int row = outputTable.getSelectedRow();

			// No record selected
			if(row < 0) JOptionPane.showMessageDialog(null, "Please select a record");
			// Record selected
			else {
				// Create record
				String selectedRecord = "";
				for(int i = 0; i < 9; i++) {
					selectedRecord += outputTable.getValueAt(row, i) + "/";
				}

				// Remove last /
				selectedRecord = selectedRecord.substring(0, selectedRecord.length() - 1);

				CustomerRecord oldRecord = new CustomerRecord(selectedRecord);

				// Get new record
				JTextField name = new JTextField(oldRecord.getName());
				JTextField address = new JTextField(oldRecord.getAddress());
				JTextField phone = new JTextField(oldRecord.getPhone());
				JTextField chequingNumber = new JTextField(oldRecord.getChequingNumber());
				JTextField chequingBalance = new JTextField(oldRecord.getChequingBalance());
				JTextField savingsNumber = new JTextField(oldRecord.getSavingsNumber());
				JTextField savingsBalance = new JTextField(oldRecord.getSavingsBalance());
				JTextField investmentNumber = new JTextField(oldRecord.getInvestmentNumber());
				JTextField investmentBalance = new JTextField(oldRecord.getInvestmentBalance());
				Object[] input = {"Name:", name, "Address:", address, "Phone:", phone, "Chequing Account Number:", chequingNumber, "Chequing Account Balance:", chequingBalance, "Savings Account Number:", savingsNumber,
						"Savings Account Balance:", savingsBalance, "Investment Account Number:", investmentNumber, "Investment Account Balance:", investmentBalance};

				try {
					int confirm = JOptionPane.showConfirmDialog(null, input, "Editing Record", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
					
					if(confirm == 0) {
						// Check if input is empty
						if(name.getText().isEmpty() || address.getText().isEmpty() || phone.getText().isEmpty() || chequingNumber.getText().isEmpty() || chequingBalance.getText().isEmpty() || savingsNumber.getText().isEmpty() ||
								savingsBalance.getText().isEmpty() || investmentNumber.getText().isEmpty() || investmentBalance.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please fill out all fields");
						}
						else {
							// Create new record
							String record = name.getText() + "/" + address.getText() + "/" + phone.getText() + "/" + chequingNumber.getText() + "/" + chequingBalance.getText() + "/" + savingsNumber.getText() + "/" +
									savingsBalance.getText() + "/" + investmentNumber.getText() + "/" + investmentBalance.getText();

							if(list.change(oldRecord, new CustomerRecord(record))) {
								// Display
								list.sort();
								outputTable.setModel(list.display().getModel());
								JOptionPane.showMessageDialog(null, "Record changed");
							}
							else {
								JOptionPane.showMessageDialog(null, "Could not change record");
							}
						}
					}
				}
				catch(NumberFormatException error) {
					JOptionPane.showMessageDialog(null, "Invalid input");
				}
			}

			break;
		}
		case "Save": {
			try {
				// Create file
				String fileName = JOptionPane.showInputDialog(null, "Name the file");
				if(list.save(fileName)) JOptionPane.showMessageDialog(null, fileName + " saved");
				else JOptionPane.showMessageDialog(null, "Could not save file");
			}
			catch(NullPointerException error) {

			}
			
			break;
		}
		case "Open": {
			int confirm = JOptionPane.showConfirmDialog(null, "Save current list before opening?");
			
			// Save
			if(confirm == 0) saveButton.doClick();
			
			if(confirm != 2) {
				// Did not cancel
				try {
					String fileName = JOptionPane.showInputDialog("Enter a file to open");
					
					if(list.open(fileName)) {
						// Display
						list.sort();
						outputTable.setModel(list.display().getModel());
						JOptionPane.showMessageDialog(null, fileName + " opened");
					}
					else JOptionPane.showMessageDialog(null, "Could not open file");
				}
				catch(NullPointerException error) {

				}
			}
			
			break;
		}
		case "Log Out": {
			int confirm = JOptionPane.showConfirmDialog(null, "Save before exiting?");
			
			// Save
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
			
			// Save
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
