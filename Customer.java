/**
 * 
 */

/**
 * @author simranbaria
 * @author isja7
 * @author Jayprakash Pathak
 * Date: May. 2022
 * Desc: Customer object containing name, address, and phone number
 * Method List: public String getName() - Returns name
 * 				public void setName(String name) - Sets name
 * 				public String getAddress() - Returns address
 * 				public void setAddress(String address) - Sets address
 * 				public String getPhone() - Returns phone number
 * 				public void setPhone(String phone) - Sets phone number
 * 				public Customer() - Default constructor
 * 				public Customer(String name, String address, String phone) - Overloaded constructor
 * 				public static void main(String[] args) - Main method
 *
 */
public class Customer {
	
	/**
	 * Private data
	 */
	private String name, address, phone;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Default constructor
	 */
	public Customer() {
		name = "";
		address = "";
		phone = "";
	}

	/**
	 * Overloaded constructor
	 */
	public Customer(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Test default constructor
		Customer customer1 = new Customer();
		
		// Test setters
		customer1.setAddress("111 Street");
		customer1.setName("Jane");
		customer1.setPhone("999-999-9999");
		
		// Test getters
		System.out.println(customer1.getAddress());
		System.out.println(customer1.getName());
		System.out.println(customer1.getPhone());
		
		// Test overloaded constructor
		Customer customer2 = new Customer("John", "123 Road", "111-111-1111");
		System.out.println(customer2.getAddress());
		System.out.println(customer2.getName());
		System.out.println(customer2.getPhone());

	}

}
