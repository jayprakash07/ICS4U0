import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.imageio.*;

/**
 * 
 */

/**
 * @author simranbaria
 * @author Jayprakash Pathak
 * Date: Jun. 2022
 * Desc: Start Up window for Bank GUI
 * Method List: public StartUp() - Constructs frame
 * 				public void actionPerformed(ActionEvent event) - Listens to buttons
 * 				public static void main(String[] args) - Main method
 *
 */
public class StartUp extends JFrame implements ActionListener {

	/**
	 * Private data
	 */
	private JButton customerLoginButton, customerSignUpButton, employeeLoginButton, quitButton;

	/**
	 * 
	 */
	public StartUp() {
		// Create frame
		super("Welcome | Bank Of Suzuki");
		setSize(650, 650);
		setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		// Add logo
		try {
			ImagePicture logo = new ImagePicture(new ImageIcon(ImageIO.read(getClass().getResource("Logo_V1.png"))));
			logo.setBounds(100, 0, 650, 650);
			add(logo);
		}
		catch(IOException error) {

		}

		// Create buttons
		customerLoginButton = new JButton("Account Login");
		customerLoginButton.addActionListener(this);
		customerLoginButton.setBounds(100, 165, 400, 100);
		add(customerLoginButton);

		customerSignUpButton = new JButton("Register New Account");
		customerSignUpButton.addActionListener(this);
		customerSignUpButton.setBounds(100, 270, 400, 100);
		add(customerSignUpButton);

		employeeLoginButton = new JButton("Staff Login");
		employeeLoginButton.addActionListener(this);
		employeeLoginButton.setBounds(100, 375, 400, 100);
		add(employeeLoginButton);

		quitButton = new JButton("Quit");
		quitButton.addActionListener(this);
		quitButton.setBounds(100, 480, 400, 100);
		add(quitButton);
	}

	/**
	 * Method to listen to buttons
	 */
	public void actionPerformed(ActionEvent event) {
		setVisible(false);

		// Quit
		if(event.getSource() == quitButton) System.exit(0);

		// Proceed to other window
		else Main.startUpEvent(event.getActionCommand());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
