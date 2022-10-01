import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 * 
 */

/**
 * @author simranbaria
 * Date: Jun. 2022
 * Desc: List of customer records
 * Method List: public CustomerList() - Constructor
 * 				public boolean insert(CustomerRecord record) - Inserts a record
 * 				public boolean delete(CustomerRecord record) - Deletes a record
 * 				public boolean change(CustomerRecord oldRecord, CustomerRecord newRecord) - Replaces an old record with a new one
 * 				public void sort() - Sorts records alphabetically
 * 				public JTable display() - Formats all records into a table for displaying
 * 				public boolean save(String fileName) - Saves records to a file
 * 				public boolean open(String fileName) - Opens a file
 * 				public int search(String key) - Searches for a record
 * 				public static void main(String[] args) - Main method
 *
 */
public class CustomerList {

	/**
	 * Private data
	 */
	private ArrayList<CustomerRecord> list;

	/**
	 * Constructor
	 */
	public CustomerList() {
		list = new ArrayList<CustomerRecord>();
	}

	/**
	 * Method to insert a record
	 */
	public boolean insert(CustomerRecord record) {
		return list.add(record);
	}

	/**
	 * Method to delete a record
	 */
	public boolean delete(CustomerRecord record) {
		int index = search(record.toString());
		return list.remove(list.get(index));
	}

	/**
	 * Method to change a record
	 */
	public boolean change(CustomerRecord oldRecord, CustomerRecord newRecord) {
		int index = search(oldRecord.toString());
		if(index >= 0) {
			list.set(index, newRecord);
			return true;
		}
		return false;
	}

	/**
	 * Method to sort list
	 */
	public void sort() {
		List<String> stringList = new ArrayList<String>();

		for(int i = 0; i < list.size(); i++) stringList.add(list.get(i).toString());

		list.clear();
		Collections.sort(stringList);

		for(int i = 0; i < stringList.size(); i++) list.add(new CustomerRecord(stringList.get(i)));
	}

	/**
	 * Method to display list
	 */
	public JTable display() {
		// Create table
		JTable table = new JTable(list.size(), 9);
		
		// Insert data into table
		for(int i = 0; i < list.size(); i++) {
			CustomerRecord current = list.get(i);
			table.setValueAt(current.getName(), i, 0);
			table.setValueAt(current.getAddress(), i, 1);
			table.setValueAt(current.getPhone(), i, 2);
			table.setValueAt(current.getChequingNumber(), i, 3);
			table.setValueAt(current.getChequingBalance(), i, 4);
			table.setValueAt(current.getSavingsNumber(), i, 5);
			table.setValueAt(current.getSavingsBalance(), i, 6);
			table.setValueAt(current.getInvestmentNumber(), i, 7);
			table.setValueAt(current.getInvestmentBalance(), i, 8);
		}
		
		// Set row height
		table.setRowHeight(20);
		
		// Set column width
		for(int i = 0; i < 9; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(100);
		}
		
		// Make table non-editable
		table.setDefaultEditor(Object.class, null);

		return table;
	}

	/**
	 * Method to save to file
	 */
	public boolean save(String fileName) {
		try {
			// Create new file
			PrintWriter outputFile = new PrintWriter(new FileWriter(fileName));

			// Write to file
			for(int i = 0; i < list.size(); i++) {
				CustomerRecord current = list.get(i);
				outputFile.println(current.toString());
			}

			// Close file
			outputFile.close();
			return true;
		}
		catch(IOException error) {
			// Could not save file
			return false;
		}
	}

	/**
	 * Method to read file
	 */
	public boolean open(String fileName) {
		try {
			// Open file
			BufferedReader inputFile = new BufferedReader(new FileReader(fileName));

			// Get number of records
			int n = 0;
			while(inputFile.readLine() != null) n++;
			inputFile.close();

			// Clear list
			list.clear();

			// Read file
			inputFile = new BufferedReader(new FileReader(fileName));
			for(int i = 0; i < n; i++) insert(new CustomerRecord(inputFile.readLine()));

			// Close file
			inputFile.close();
			return true;
		}
		catch(IOException error) {
			// Could not read file
			return false;
		}
	}

	/**
	 * Method to search for record
	 */
	public int search(String key) {
		for(int i = 0; i < list.size(); i++) {
			if(key.equals(list.get(i).toString())) return i;
		}

		return -1;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		CustomerList list = new CustomerList();
		String[] options = {"Insert", "Delete", "Edit", "Sort", "Display", "Save", "Open", "Quit"};

		while(true) {
			String command = (String)JOptionPane.showInputDialog(null,
					"What would you like to test?", "Customer List Testing",
					JOptionPane.QUESTION_MESSAGE, null, options, options);

			switch(command) {
			case "Insert": {
				// Create record
				CustomerRecord record = new CustomerRecord(JOptionPane.showInputDialog(null,
						"Enter a record formatted with / in between information",
						"Jane/111 Street/999-999-9999/000000000000/0.00/000000000000/0.00/000000000000/0.00"));

				// Check if record can be inserted
				if(list.insert(record)) {
					JOptionPane.showMessageDialog(null, "Record inserted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Could not insert record");
				}

				break;
			}
			case "Delete": {
				// Create record
				CustomerRecord record = new CustomerRecord(JOptionPane.showInputDialog(null,
						"Enter a record formatted with / in between information",
						"Jane/111 Street/999-999-9999/000000000000/0.00/000000000000/0.00/000000000000/0.00"));

				// Check if record can be deleted
				if(list.delete(record)) {
					JOptionPane.showMessageDialog(null, "Record deleted");
				}
				else {
					JOptionPane.showMessageDialog(null, "Could not delete record");
				}

				break;
			}
			case "Edit": {
				// Create old record
				CustomerRecord oldRecord = new CustomerRecord(JOptionPane.showInputDialog(null,
						"Enter a record formatted with / in between information",
						"Jane/111 Street/999-999-9999/000000000000/0.00/000000000000/0.00/000000000000/0.00"));

				// Create new record
				CustomerRecord newRecord = new CustomerRecord(JOptionPane.showInputDialog(null,
						"Enter a record formatted with / in between information",
						"Jane/111 Street/999-999-9999/000000000000/0.00/000000000000/0.00/000000000000/0.00"));

				// Check if record can be changed
				if(list.change(oldRecord, newRecord)) {
					JOptionPane.showMessageDialog(null, "Record changed");
				}
				else {
					JOptionPane.showMessageDialog(null, "Could not change record");
				}

				break;
			}
			case "Sort": {
				// Sort list
				list.sort();
				JOptionPane.showMessageDialog(null, "List sorted");
				break;
			}
			case "Display": {
				// Display list
				JOptionPane.showMessageDialog(null, list.display());
				break;
			}
			case "Save": {
				// Name list
				String fileName = JOptionPane.showInputDialog(null, "Name the file");
				
				// Check if list can be saved
				if(list.save(fileName)) {
					JOptionPane.showMessageDialog(null, fileName + " saved");
				}
				else JOptionPane.showMessageDialog(null, "Could not save file");

				break;
			}
			case "Open": {
				// Get name
				String fileName = JOptionPane.showInputDialog(null, "Enter a file to open");
				
				// Check if file can be opened
				if(list.open(fileName)) {
					JOptionPane.showMessageDialog(null, fileName + " opened");
				}
				else JOptionPane.showMessageDialog(null, "Could not open file");

				break;
			}
			}
			// Quit
			if(command.equals("Quit")) break;
		}
	}

}
