import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author simranbaria
 * @author Jayprakash Pathak
 *         Date: Jun. 2022
 *         Desc: Main class for Bank GUI
 *         Connects all windows together
 *         Method List: public Main() - Starts the application
 *         public static void startUpEvent(String button) - Takes user from
 *         Start Up Window to another window of their choosing
 *         public static void back() - Returns user to Start Up Window
 *         public static void openCustomerUI(String fileName) - Takes user from
 *         Customer Login or Sign Up to Customer UI
 *         public static void openEmployeeUI() - Takes user from Employee Login
 *         to Employee UI
 *         public static void main(String[] args) - Main method
 *
 */
public class Main {
	private static StartUp startUp;
	private static CustomerLogin customerLogin;
	private static CustomerSignUp customerSignUp;
	private static EmployeeLogin employeeLogin;
	private static CustomerUI customerUI;
	private static EmployeeUI employeeUI;

	/**
	 * Creates windows and starts application
	 */
	public Main() {
		startUp = new StartUp();
		startUp.setVisible(true);
	}

	/**
	 * Method for Start Up Window events
	 * Sets certain windows visible based on input
	 */
	public static void startUpEvent(String button) {
		switch (button) {
			case "Account Login": {
				// Proceed to Customer Login
				customerLogin = new CustomerLogin();
				customerLogin.setVisible(true);
				break;
			}
			case "Register New Account": {
				// Proceed to Customer Sign Up
				customerSignUp = new CustomerSignUp();
				customerSignUp.setVisible(true);
				break;
			}
			case "Staff Login": {
				// Proceed to Employee Login
				employeeLogin = new EmployeeLogin();
				employeeLogin.setVisible(true);
				break;
			}
		}
	}

	/**
	 * Method to return to Start Up Window
	 */
	public static void back() {
		startUp.setVisible(true);
	}

	/**
	 * Method for Customer Login and Sign Up to open Customer UI Window with file
	 * information
	 */
	public static void openCustomerUI(String fileName) {
		try {
			// Get info from file name
			String[] info = fileName.split("-");
			String name = info[0];
			long chequingNumber = Long.parseLong(info[1]);
			long savingsNumber = Long.parseLong(info[2]);
			long investmentNumber = Long.parseLong(info[3]);

			// Open file
			BufferedReader contents = new BufferedReader(new FileReader(fileName));

			// Get info from file contents
			String address = contents.readLine();
			String phone = contents.readLine();
			double chequingBalance = Double.parseDouble(contents.readLine());
			double savingsBalance = Double.parseDouble(contents.readLine());
			double investmentBalance = Double.parseDouble(contents.readLine());
			contents.close();

			// Create customer and accounts based on info
			Customer customer = new Customer(name, address, phone);
			ChequingAccount chequing = new ChequingAccount(customer, chequingBalance);
			chequing.setAccountNumber(chequingNumber);
			SavingsAccount savings = new SavingsAccount(customer, savingsBalance);
			savings.setAccountNumber(savingsNumber);
			InvestmentAccount investment = new InvestmentAccount(customer, investmentBalance);
			investment.setAccountNumber(investmentNumber);

			// Proceed to Customer UI
			customerUI = new CustomerUI(customer, chequing, savings, investment);
			customerUI.setVisible(true);
		} catch (IOException error) {
			JOptionPane.showMessageDialog(null, error);
		}
	}

	/**
	 * Method for Employee Login to open EmployeeUI
	 */
	public static void openEmployeeUI() {
		employeeUI = new EmployeeUI();
		employeeUI.setVisible(true);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
