/**
 * 
 */

/**
 * @author isja7
 * Date: May. 2022
 * Desc: Savings Account
 * 		 Inherits from Account
 * 		 Default deduction fee of $6.25 per withdraw if balance is less than $10,000.00
 * Method List: public double getDeductionFee() - Returns deduction fee
 * 				public void setDeductionFee(double deductionFee) - Sets deduction fee
 * 				public double getMinimumBalance() - Returns minimum balance
 * 				public void setMinimumBalance(double minimumBalance) - Sets minimum balance
 * 				public SavingsAccount(Customer info, double initialBalance) - Constructor
 * 				public boolean withdraw(double funds) - Withdraws funds and applies a deduction fee based on minimum balance
 * 				public static void main(String[] args) - Main method
 *
 */
public class SavingsAccount extends Account {

	/**
	 * Private data
	 */
	private double deductionFee, minimumBalance;

	/**
	 * @return the deductionFee
	 */
	public double getDeductionFee() {
		return deductionFee;
	}

	/**
	 * @param deductionFee the deductionFee to set
	 */
	public void setDeductionFee(double deductionFee) {
		this.deductionFee = deductionFee;
	}

	/**
	 * @return the minimumBalance
	 */
	public double getMinimumBalance() {
		return minimumBalance;
	}

	/**
	 * @param minimumBalance the minimumBalance to set
	 */
	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	/**
	 * Constructor
	 */
	public SavingsAccount(Customer info, double initialBalance) {
		super(info);
		super.deposit(initialBalance);
		deductionFee = 6.25;
		minimumBalance = 10000;
	}

	/**
	 * Method to withdraw funds
	 * Deducts a fee from the balance
	 */
	public boolean withdraw(double funds) {
		// Check if deduction fee should be applied
		if(getBalance() < minimumBalance) {
			return super.withdraw(funds + deductionFee);
		}
		else return super.withdraw(funds);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test constructor
		Customer customer = new Customer("Jane", "111 Street", "999-999-9999");
		SavingsAccount account = new SavingsAccount(customer, 0);
		
		// Test getters and setters
		account.setDeductionFee(5);
		account.setMinimumBalance(5000);
		System.out.println(account.getInfo().getName());
		System.out.println(account.getInfo().getAddress());
		System.out.println(account.getInfo().getPhone());
		System.out.println(account.getBalance());
		System.out.println(account.getAccountNumber());
		System.out.println(account.getDeductionFee());
		System.out.println(account.getMinimumBalance());
		
		// Test deposit method
		if(account.deposit(1000)) System.out.println(account.formatBalance(account.getBalance()));
		else System.out.println("Cannot deposit negative funds");
		if(account.deposit(-1)) System.out.println(account.formatBalance(account.getBalance()));
		else System.out.println("Cannot deposit negative funds");
		
		// Test withdraw method
		if(account.withdraw(500)) System.out.println(account.formatBalance(account.getBalance()));
		else System.out.println("Insufficient funds");
		if(account.withdraw(1000)) System.out.println(account.formatBalance(account.getBalance()));
		else System.out.println("Insufficient funds");
		account.deposit(10000);
		if(account.withdraw(5000)) System.out.println(account.formatBalance(account.getBalance()));
		else System.out.println("Insufficient funds");

	}

}
