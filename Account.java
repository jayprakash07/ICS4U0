import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author isja7
 * @author Jayprakash Pathak
 * Date: May. 2022
 * Desc: Account object for customers
 * Method List: public double getBalance() - Returns balance
 * 				public long getAccountNumber() - Returns account number
 * 				public long setAccountNumber() - Sets account number
 * 				public Customer getInfo() - Returns customer info
 * 				public Account() - Default constructor
 * 				public Account(Customer info) - Overloaded constructor
 * 				public boolean deposit(double funds) - Deposits funds
 * 				public boolean withdraw(double funds) - Withdraws funds
 * 				public String formatBalance(double funds) - Formats to two decimal places
 * 				public static void main(String[] args) - Main method
 *
 */
public class Account {

	/**
	 * Private data
	 */
	private double balance;
	private long accountNumber;
	private Customer info;

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the accountNumber
	 */
	public long getAccountNumber() {
		return accountNumber;
	}

	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	/**
	 * @return the info
	 */
	public Customer getInfo() {
		return info;
	}

	/**
	 * Default constructor
	 */
	public Account() {
		this.balance = 0;
		this.accountNumber = (long)(Math.random()*1000000*1000000);
		this.info = new Customer();
	}

	/**
	 * Overloaded constructor
	 */ 
	public Account(Customer info) {
		this.balance = 0;
		this.accountNumber = (long)(Math.random()*1000000*1000000); 
		this.info = info;
	}

	/**
	 * Method to deposit funds
	 */
	public boolean deposit(double funds) {
		if(funds >= 0) {
			this.balance += funds;
			this.balance = Double.parseDouble(formatBalance(this.balance));
			return true;
		}
		return false;
	}

	/**
	 * Method to withdraw funds
	 */
	public boolean withdraw(double funds) {
		if (this.balance >= funds) {
			this.balance -= funds;
			return true;
		} 
		return false;
	}

	/**
	 * Method to format to two decimal places 
	 */
	public String formatBalance(double funds) {
		DecimalFormat twoDigit = new DecimalFormat("0.00");
		return twoDigit.format(funds);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test default constructor
		Account account1 = new Account();
		
		// Test getters
		System.out.println(account1.getAccountNumber());
		System.out.println(account1.getBalance());
		account1.getInfo().setAddress("123 Road");
		account1.getInfo().setName("John");
		account1.getInfo().setPhone("111-111-1111");
		System.out.println(account1.getInfo().getAddress());
		System.out.println(account1.getInfo().getName());
		System.out.println(account1.getInfo().getPhone());
		
		// Test overloaded constructor
		Account account2 = new Account(new Customer("Jane", "111 Street", "999-999-9999"));
		System.out.println(account2.getAccountNumber());
		System.out.println(account2.getBalance());
		System.out.println(account2.getInfo().getAddress());
		System.out.println(account2.getInfo().getName());
		System.out.println(account2.getInfo().getPhone());

		// Test deposit method
		if(account1.deposit(900)) {
			System.out.println(account1.getBalance());
		}
		else System.out.println("Cannot deposit negative funds");
		if(account2.deposit(-1)) {
			System.out.println(account2.getBalance());
		}
		else System.out.println("Cannot deposit negative funds");
		
		// Test withdraw method
		if(account1.withdraw(60)) {
			System.out.println(account1.getBalance());
		}
		else System.out.println("Insufficent funds");
		if(account2.withdraw(1000)) {
			System.out.println(account2.getBalance());
		}
		else System.out.println("Insufficent funds");
		
		// Test decimal format method
		System.out.println(account1.formatBalance(account1.getBalance()));
		System.out.println(account2.formatBalance(account2.getBalance()));
		
		// Test setter
		account1.setAccountNumber(account2.getAccountNumber());
		System.out.println(account1.getAccountNumber());

	}

}
