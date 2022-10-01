import java.text.DecimalFormat;

/**
 * 
 */

/**
 * @author simranbaria
 * Date: May. 2022
 * Desc: Record containing customer and account information
 * Method List: public String getName() - Returns name
 * 				public String getAddress() - Returns address
 * 				public String getPhone() - Returns phone number
 * 				public String getChequingNumber() - Returns chequing account number
 * 				public String getSavingsNumber() - Returns sacgins account number
 * 				public String getInvestmentNumber() - Returns investment account number
 * 				public String getChequingBalance() - Returns chequing account balance
 * 				public String getSavingsBalance() - Returns savings account balance
 * 				public String getInvestmentBalance() - Returns investment account balance
 * 				public CustomerRecord() - Default constructor
 * 				public CustomerRecord(String record) - Overloaded constructor
 * 				public String toString() - Converts record to string
 * 				public void toRecord(String record) - Converts string to record
 * 				public static void main(String[] args) - Main method
 *
 */
public class CustomerRecord {

	/**
	 * Private data
	 */
	private String name, address, phone, chequingNumber, savingsNumber, investmentNumber,
	chequingBalance, savingsBalance, investmentBalance;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @return the chequingNumber
	 */
	public String getChequingNumber() {
		return chequingNumber;
	}

	/**
	 * @return the savingsNumber
	 */
	public String getSavingsNumber() {
		return savingsNumber;
	}

	/**
	 * @return the investmentNumber
	 */
	public String getInvestmentNumber() {
		return investmentNumber;
	}

	/**
	 * @return the chequingBalance
	 */
	public String getChequingBalance() {
		return chequingBalance;
	}

	/**
	 * @return the savingsBalance
	 */
	public String getSavingsBalance() {
		return savingsBalance;
	}

	/**
	 * @return the investmentBalance
	 */
	public String getInvestmentBalance() {
		return investmentBalance;
	}

	/**
	 * Constructor
	 */
	public CustomerRecord(String record) {
		toRecord(record);
	}

	/**
	 * Method to convert to string
	 */
	public String toString() {
		return name + "/" + address + "/" + phone + "/" + chequingNumber + "/" +
				chequingBalance + "/" + savingsNumber + "/" + savingsBalance + "/" +
				investmentNumber + "/" + investmentBalance;
	}

	/**
	 * Method to convert to record
	 */
	public void toRecord(String record) {
		DecimalFormat twoDigit = new DecimalFormat("0.00");
		String info[] = record.split("/");
		name = info[0];
		address = info[1];
		phone = info[2];
		chequingNumber = info[3];
		chequingBalance = twoDigit.format(Double.parseDouble(info[4]));
		savingsNumber = info[5];
		savingsBalance = twoDigit.format(Double.parseDouble(info[6]));
		investmentNumber = info[7];
		investmentBalance = twoDigit.format(Double.parseDouble(info[8]));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test constructor
		String info = "Jane/111 Street/999-999-9999/000000000000/0.00/000000000000/0.00/000000000000/0.00";
		CustomerRecord record = new CustomerRecord(info);
		System.out.println(record.toString());

		// Test getters
		System.out.println(record.getName());
		System.out.println(record.getAddress());
		System.out.println(record.getPhone());
		System.out.println(record.getChequingNumber());
		System.out.println(record.getChequingBalance());
		System.out.println(record.getSavingsNumber());
		System.out.println(record.getSavingsBalance());
		System.out.println(record.getInvestmentNumber());
		System.out.println(record.getInvestmentBalance());
	}

}
